package algorithms;

import domain.Node;

import java.util.PriorityQueue;

public class Astar {

    private PriorityQueue<Node> queue;
    private boolean[][] visited;

    public Astar() {
        queue = new PriorityQueue<>();
        visited = new boolean[256][256];
    }

    public Node search(Node start, Node goal) {
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
                        if (neighbor.getPrevious() == null) {
                            neighbor.setPrevious(node);
                        }

                        neighbor.heuristic(goal);
                        queue.add(neighbor);
                    }
                }
            }

        }

        return start;
    }
}
