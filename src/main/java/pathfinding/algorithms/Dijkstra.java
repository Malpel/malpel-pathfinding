package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of Dijkstra's shortest path algorithm using a priority queue.
 */

public class Dijkstra extends Pathfinder {

    private PriorityQueue<Node> queue;

    /**
     * Implementation of Dijkstra's shortest path algorithm using a priority queue.
     * @param mapSize
     * Map size as an integer, used for the boolean[][] visited array.
     */
    public Dijkstra(int mapSize) {
        super(mapSize);
        queue = new PriorityQueue<>(10, new DijkstraComparator());
    }

    /**
     * Performs the actual search.
     * @param start
     * The starting point of the path as a node.
     * @param goal
     * The end of the path as a node.
     * @return
     * The shortest path as a list of nodes if a path exists,
     * otherwise null.
     */
    public List<Node> search(Node start, Node goal) {
        start.setPathLength(0);
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

                        double alt = calculatePathLength(neighbor, node);

                        if (alt < neighbor.getPathLength()) {
                            neighbor.setPathLength(alt);
                            neighbor.setPrevious(node);
                            queue.add(neighbor);
                        }
                    }

                }
            }
        }

        return null;
    }

    /**
     * Calculates the path length to the neighboring node.
     * @param neighbor
     * A neighboring node of the current node.
     * @param node
     * The current node in processing.
     * @return
     * The path length to the neighboring node as a double.
     */
    private double calculatePathLength(Node neighbor, Node node) {
        double alt = node.getPathLength();

        if ((neighbor.getY() - 1 == node.getY() && neighbor.getX() - 1 == node.getX())
                || (neighbor.getY() - 1 == node.getY() && neighbor.getX() + 1 == node.getX())
                || (neighbor.getY() + 1 == node.getY() && neighbor.getX() + 1 == node.getX())
                || (neighbor.getY() + 1 == node.getY() && neighbor.getX() - 1 == node.getX())) {
            alt += 1.41;
        } else {
            alt += 1;
        }

        return alt;
    }

    /**
     * A custom comparator for the priority queue.
     * Compares path lengths.
     */
    static class DijkstraComparator implements Comparator<Node> {

        @Override
        public int compare(Node node, Node t1) {
            return node.getPathLength() <= t1.getPathLength() ? 1 : 0;
        }
    }

}
