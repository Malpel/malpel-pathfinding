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
     * @param mapSize
     * Map size as an integer, used for the boolean[][] visited array.
     */
    public Astar(int mapSize) {
        super(mapSize);
        queue = new PriorityQueue<>();
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
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!visited[node.getY()][node.getX()]) {
                visited[node.getY()][node.getX()] = true;

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
