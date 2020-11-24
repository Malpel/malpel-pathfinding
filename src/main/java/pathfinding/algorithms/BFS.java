package pathfinding.algorithms;

import pathfinding.domain.Deque;
import pathfinding.domain.Node;
import pathfinding.domain.List;

/**
 * Implementation of the breadth-first search algorithm.
 */
public class BFS extends Pathfinder {

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
    public List search(Node start, Node goal) {
        Deque queue = new Deque();
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            Node node = queue.dequeue();

            if (!node.isVisited()) {
                node.setVisited(true);

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return getPath(node, start);
                }

                for (int i = 0; i < node.getNeighbors().size(); i++) {
                    Node neighbor = node.getNeighbors().get(i);

                    if (neighbor != null && !neighbor.isVisited()) {
                        if (neighbor.getPrevious() == null) {
                            neighbor.setPrevious(node);
                        }

                        queue.enqueue(neighbor);
                    }
                }
            }
        }

        return null;
    }
}
