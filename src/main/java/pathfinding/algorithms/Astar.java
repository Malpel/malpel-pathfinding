package pathfinding.algorithms;

import pathfinding.domain.MinHeap;
import pathfinding.domain.Node;
import pathfinding.domain.List;
import pathfinding.util.MathUtils;

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
                    neighbor.heuristic(goal);
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
}
