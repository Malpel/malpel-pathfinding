package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Implementation of the breadth-first search algorithm.
 */
public class BFS extends Pathfinder {

    private ArrayDeque<Node> queue;

    /**
     * Implementation of the breadth-first search algorithm.
     */
    public BFS() {
        super();
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
        queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!node.isVisited()) {
                node.setVisited(true);

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return getPath(node, start);
                }

                for (Node neighbor : node.getNeighbors()) {
                    if (neighbor != null) {
                        if (neighbor.getPrevious() == null) {
                            neighbor.setPrevious(node);
                        }

                        queue.add(neighbor);
                    }

                }
            }
        }

        return null;
    }
}
