package algorithms;

import domain.Node;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {

    private Queue<Node> queue;
    private boolean[][] visited;

    public BFS() {
        queue = new ArrayDeque<>();
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
                        queue.add(neighbor);
                    }

                }
            }
        }

        return start;
    }
}
