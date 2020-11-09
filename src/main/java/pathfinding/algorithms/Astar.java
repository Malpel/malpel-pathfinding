package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.List;
import java.util.PriorityQueue;

public class Astar extends Pathfinder {

    private PriorityQueue<Node> queue;

    public Astar(int mapSize) {
        super(mapSize);
        queue = new PriorityQueue<>();
    }

    /**
     * The A* star search algorithm.
     * @param start
     * The starting point as a node.
     * @param goal
     * The goal as a node.
     * @return
     * Returns the start node if no path can be found, else returns the end node.
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
