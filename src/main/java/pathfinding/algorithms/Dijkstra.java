package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.PriorityQueue;

public class Dijkstra {

    private PriorityQueue<Node> queue;
    private boolean[][] visited;

    public Dijkstra() {
        queue = new PriorityQueue<>();
        visited = new boolean[256][256]; // size should be a parameter
    }

    public Node search(Node start, Node goal) {
        start.setPathLength(0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!visited[node.getY()][node.getX()]) {
                visited[node.getY()][node.getX()] = true;

                if (node.getY() == goal.getY() && node.getX() == goal.getX()) {
                    return node;
                }

                for (Node neighbor : node.getNeighbors()) {
                    if (neighbor != null) {
                        int alt = node.getPathLength() + 1;

                        if (alt < neighbor.getPathLength()) {
                            neighbor.setPathLength(alt);
                            neighbor.setPrevious(node);
                            queue.add(neighbor);
                        }
                    }

                }
            }
        }


        return start;
    }

}
