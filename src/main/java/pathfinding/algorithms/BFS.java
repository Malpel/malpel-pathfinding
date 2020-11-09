package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.ArrayDeque;
import java.util.List;

public class BFS extends Pathfinder {

    private ArrayDeque<Node> queue;

    public BFS(int mapSize) {
        super(mapSize);
        queue = new ArrayDeque<>();
    }

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

                        queue.add(neighbor);
                    }

                }
            }
        }

        return null;
    }
}
