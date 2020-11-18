package pathfinding.algorithms;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class JPS extends Pathfinder {

    PriorityQueue<Node> queue;
    NodeMap map;

    public JPS(int mapSize, NodeMap nodeMap) {
        super(mapSize);
        // should be in search
        queue = new PriorityQueue<>();
        this.map = nodeMap;
    }

    public List<Node> search(Node start, Node goal) {
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

        int dy = clamp(y - previous.getY(), -1, 1);
        int dx = clamp(x - previous.getX(), -1, 1);

        if (dy != 0 && dx != 0) {
            if (jump(current, map.getNode(y + dy, x), goal) != null) {
                return current;
            }

            if (jump(current, map.getNode(y, x + dx), goal) != null) {
                return current;
            }
        } else if (dx == 0) {
            if (!map.isAccessible(y - dy, x + 1) && map.isAccessible(y, x + 1)) {
                return current;
            }

            if (!map.isAccessible(y - dy, x - 1) && map.isAccessible(y, x - 1)) {
                return current;
            }
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
