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
    public List search(Node start, Node goal) {
        MinHeap queue = new MinHeap();
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
