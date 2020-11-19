package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of the A* search algorithm.
 */
public class Astar extends Pathfinder {

    private PriorityQueue<Node> queue;

    /**
     * Implementation of the A* search algorithm.
     */
    public Astar() {
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

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return getPath(node, start);
                }

                for (Node neighbor : node.getNeighbors()) {
                    if (neighbor != null) {
                        if (neighbor.getPrevious() == null) {
                            neighbor.setPrevious(node);
                        }

                        neighbor.heuristic(goal);
                        queue.add(neighbor);
                    }
                }
            }

        }

        return null;
    }
}
