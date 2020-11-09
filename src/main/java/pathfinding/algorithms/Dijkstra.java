package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra extends Pathfinder {

    private PriorityQueue<Node> queue;

    public Dijkstra(int mapSize) {
        super(mapSize);
        queue = new PriorityQueue<>(10, new DijkstraComparator());
    }

    public List<Node> search(Node start, Node goal) {
        start.setPathLength(0);
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

                        double alt = node.getPathLength();

                        if ((neighbor.getY() - 1 == node.getY() && neighbor.getX() - 1 == node.getX())
                        || (neighbor.getY() - 1 == node.getY() && neighbor.getX() + 1 == node.getX())
                        || (neighbor.getY() + 1 == node.getY() && neighbor.getX() + 1 == node.getX())
                        || (neighbor.getY() + 1 == node.getY() && neighbor.getX() - 1 == node.getX())) {
                            alt += 1.41;
                        } else {
                            alt += 1;
                        }

                        if (alt < neighbor.getPathLength()) {
                            neighbor.setPathLength(alt);
                            if (neighbor.getPrevious() == null) {
                                neighbor.setPrevious(node);
                            }

                            queue.add(neighbor);
                        }
                    }

                }
            }
        }

        return null;
    }

    class DijkstraComparator implements Comparator<Node> {

        @Override
        public int compare(Node node, Node t1) {
            return node.getPathLength() <= t1.getPathLength() ? 1 : 0;
        }
    }

}
