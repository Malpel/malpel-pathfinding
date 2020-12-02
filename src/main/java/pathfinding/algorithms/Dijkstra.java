package pathfinding.algorithms;

import pathfinding.domain.MinHeap;
import pathfinding.domain.Node;
import pathfinding.domain.List;
import pathfinding.util.MathUtils;

import java.util.Comparator;

/**
 * Implementation of Dijkstra's shortest path algorithm using a priority queue.
 */

public class Dijkstra extends Pathfinder {

    /**
     * Implementation of Dijkstra's shortest path algorithm using a priority queue.
     */
    public Dijkstra(int mapSize) {
        super(mapSize);
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
    @Override
    public List search(Node start, Node goal) {
        MinHeap queue = new MinHeap(new DijkstraComparator());
        start.setPathLength(0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!visited[node.getY()][node.getX()]) {
                visited[node.getY()][node.getX()] = true;

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return getPath(node, start);
                }

                for (int i = 0; i < node.getNeighbors().size(); i++) {
                    Node neighbor = node.getNeighbors().get(i);
                    handlePathLength(node, neighbor, queue);
                }
            }
        }

        return null;
    }

    private void handlePathLength(Node node, Node neighbor, MinHeap queue) {
        double alt = MathUtils.shortestDistance(node, neighbor) + node.getPathLength();

        if (alt < neighbor.getPathLength()) {
            neighbor.setPathLength(alt);
            neighbor.setPrevious(node);
            queue.add(neighbor);
        }
    }

    /**
     * A custom comparator for the priority queue.
     * Compares path lengths.
     */
    static class DijkstraComparator implements Comparator<Node> {

        @Override
        public int compare(Node node, Node t1) {
            if (node == null || t1 == null) {
                return 1;
            }

            if (node.getPathLength() > t1.getPathLength()) {
                return 1;
            } else if (node.getPathLength() == t1.getPathLength()) {
                return 0;
            }
            return -1;
        }
    }
}
