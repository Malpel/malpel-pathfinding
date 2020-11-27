package pathfinding.algorithms;

import pathfinding.domain.MinHeap;
import pathfinding.domain.Node;
import pathfinding.domain.List;

/**
 * Implementation of the A* search algorithm.
 */
public class Astar extends Pathfinder {

    /**
     * Implementation of the A* search algorithm.
     */
    public Astar() {
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
    @Override
    public List search(Node start, Node goal) {
        MinHeap queue = new MinHeap();
        start.setPathLength(0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!node.isVisited()) {
                node.setVisited(true);

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return getPath(node, start);
                }

                for (int i = 0; i < node.getNeighbors().size(); i++) {
                    Node neighbor = node.getNeighbors().get(i);

                    if (neighbor != null && !neighbor.isVisited()) {
                        handlePathLength(node, neighbor);
                        neighbor.heuristic(goal);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private void handlePathLength(Node node, Node neighbor) {
        double alt = shortestDistance(node, neighbor) + node.getPathLength();

        if (alt < neighbor.getPathLength()) {
            neighbor.setPathLength(alt);
            neighbor.setPrevious(node);
        }
    }

    public double shortestDistance(Node first, Node second) {
        double distanceFromY = first.getY() - second.getY();
        double distanceFromX = first.getX() - second.getX();

        return Math.sqrt((distanceFromY * distanceFromY) + (distanceFromX * distanceFromX));

    }
}
