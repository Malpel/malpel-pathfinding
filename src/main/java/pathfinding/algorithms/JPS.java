package pathfinding.algorithms;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of the Jump Point search algorithm.
 */
public class JPS extends Pathfinder {

    PriorityQueue<Node> queue;
    NodeMap map;

    /**
     * Implementation of the Jump Point search algorithm.
     * @param nodeMap
     * The map as a NodeMap.
     */
    public JPS(NodeMap nodeMap) {
        this.map = nodeMap;
    }

    /**
     * Performs the actual search.
     * @param start
     * The starting point of the path as a Node.
     * @param goal
     * The goal of the path as a Node.
     * @return
     * The shortest path as a list of nodes if a path exists,
     * otherwise null.
     */
    public List<Node> search(Node start, Node goal) {
        queue = new PriorityQueue<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!node.isVisited()) {
                node.setVisited(true);

                if (node == goal) {
                    return getPath(node, start);
                }

                List<Node> successors = identifySuccessors(node, start, goal);

                for (Node s : successors) {
                    s.heuristic(goal);
                    queue.add(s);
                }

            }
        }

        return null;
    }

    /**
     * Selects the nodes that can be jumped to.
     * @param node
     * The current node.
     * @param start
     * The start of the path.
     * @param goal
     * The goal of the path.
     * @return
     * List of successor nodes.
     */
    private List<Node> identifySuccessors(Node node, Node start, Node goal) {
        List<Node> successors = new ArrayList<>();
        List<Node> neighbors = pruneNeighbors(node);

        for (Node n : neighbors) {
            Node jumpPoint = jump(node, n, goal);

            if (jumpPoint != null) {
                successors.add(jumpPoint);
            }
        }

        return successors;
    }

    // not splitting this into smaller parts
    /**
     * Finds the available jump points on the path.
     * @param previous
     * The previous node.
     * @param current
     * The node in examination.
     * @param goal
     * The goal of the path.
     * @return
     * A jump point, or null if no jump point possible.
     */
    //CHECKSTYLE:OFF
    private Node jump(Node previous, Node current, Node goal) {

        if (current == null || current.isVisited()) {
            return null;
        }

        if (!map.isAccessible(current.getY(), current.getX())) {
            return null;
        }

        current.setPrevious(previous);

        if (current.getY() == goal.getY() && current.getX() == goal.getX()) {
            return current;
        }

        int y = current.getY();
        int x = current.getX();

        // direction for y- and x-axes.
        int dy = clamp(y - previous.getY(), -1, 1);
        int dx = clamp(x - previous.getX(), -1, 1);

        // diagonal
        if (dy != 0 && dx != 0) {
            if (jump(current, map.getNode(y + dy, x), goal) != null) {
                return current;
            }

            if (jump(current, map.getNode(y, x + dx), goal) != null) {
                return current;
            }
            // vertical
        } else if (dx == 0) {
            if (!map.isAccessible(y - dy, x + 1) && map.isAccessible(y, x + 1)) {
                return current;
            }

            if (!map.isAccessible(y - dy, x - 1) && map.isAccessible(y, x - 1)) {
                return current;
            }
            // horizontal
        } else {
            if (!map.isAccessible(y + 1, x - dx) && map.isAccessible(y + 1, x)) {
                return current;
            }

            if (!map.isAccessible(y - 1, x - dx) && map.isAccessible(y - 1, x)) {
                return current;
            }
        }


        return jump(current, map.getNode(y + dy, x + dx), goal);
    }
    //CHECKSTYLE:ON

    /**
     * Prunes the neighbors list down to neighbors in the direction of movement.
     * Takes into consideration both natural and forced neighbors.
     * @param node
     * Node under examination.
     * @return
     * A list of neighbors in the direction of movement.
     */
    private List<Node> pruneNeighbors(Node node) {
        List<Node> prunedNeighbors = new ArrayList<>();

        // starting node, all nodes "acceptable"
        if (node.getPrevious() == null) {
            return node.getNeighbors();
        }

        int dy = clamp(node.getY() - node.getPrevious().getY(), -1, 1);
        int dx = clamp(node.getX() - node.getPrevious().getX(), -1, 1);

        if (dy != 0 && dx != 0) {
            getNeighborsForDiagonal(node.getY(), node.getX(), dy, dx, prunedNeighbors);
        } else {
            getNeighborsForCardinal(node.getY(), node.getX(), dy, dx, prunedNeighbors);
        }


        return prunedNeighbors;
    }

    /**
     * Finds the neighbors when going diagonally.
     * @param y
     * The y coordinate of the node as an integer.
     * @param x
     * The x coordinate of the node as an integer.
     * @param dy
     * The direction on the y-axis.
     * @param dx
     * The direction on the x-axis.
     * @param list
     * The to which neighbors will added.
     */
    private void getNeighborsForDiagonal(int y, int x, int dy, int dx, List<Node> list) {
        // natural neighbors
        if (map.isAccessible(y + dy, x)) {
            list.add(map.getNode(y + dy, x));
        }

        if (map.isAccessible(y, x + dx)) {
            list.add(map.getNode(y, x + dx));
        }

        if (map.isAccessible(y + dy, x + dx) && (map.isAccessible(y + dy, x) || map.isAccessible(y, x + dx))) {
            list.add(map.getNode(y + dy, x + dx));
        }

        // forced neighbors
        if (!map.isAccessible(y + dy, x) && map.isAccessible(y, x - dx) && map.isAccessible(y + dy, x - dx)) {
            list.add(map.getNode(x - dx, y + dy));
        }

        if (!map.isAccessible(y - dy, x) && map.isAccessible(y, x + dx) && map.isAccessible(y - dy, x + dx)) {
            list.add(map.getNode(x + dx, y - dy));
        }
    }

    /**
     * Finds the neighbors when going in a cardinal direction.
     * @param y
     * The y coordinate of the node as an integer.
     * @param x
     * The x coordinate of the node as an integer.
     * @param dy
     * The direction on the y-axis.
     * @param dx
     * The direction on the x-axis.
     * @param list
     * The to which neighbors will added.
     */
    private void getNeighborsForCardinal(int y, int x, int dy, int dx, List<Node> list) {
        if (dy == 0) {
            // natural
            if (map.isAccessible(y, x + dx)) {
                list.add(map.getNode(y,  x + dx));
            }

            // forced
            if (!map.isAccessible(y + 1, x) && map.isAccessible(y, x + dx) && map.isAccessible(y + 1, x + dx)) {
                list.add(map.getNode(y + 1, x + dx));
            }

            if (!map.isAccessible(y - 1, x) && map.isAccessible(y, x + dx) && map.isAccessible(y - 1, x + dx)) {
                list.add(map.getNode(y - 1, x + dx));
            }
        } else {

            // natural
            if (map.isAccessible(y + dy, x)) {
                list.add(map.getNode(y + dy, x));
            }

            // forced
            if (!map.isAccessible(y, x + 1) && map.isAccessible(y + dy, x) && map.isAccessible(y + dy, x + 1)) {
                list.add(map.getNode(y + dy, x + 1));
            }

            if (!map.isAccessible(y, x - 1) && map.isAccessible(y + dy, x) && map.isAccessible(y + dy, x - 1)) {
                list.add(map.getNode(y + dy, x - 1));
            }
        }
    }

    // move this
    private int clamp(int a, int min, int max) {
        if (a < min) {
            return min;
        } else if (a > max) {
            return max;
        }

        return a;
    }

}
