package pathfinding.algorithms;

import pathfinding.domain.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A superclass for the pathfinding algorithms.
 */
public class Pathfinder {

    protected boolean[][] visited;
    private final int mapSize;

    /**
     * Pathfinder takes care of the boolean[][] visited array, as well as
     * constructing the path.
     * @param mapSize
     * The map size as an integer.
     */
    public Pathfinder(int mapSize) {
        visited = new boolean[mapSize][mapSize];
        this.mapSize = mapSize;
    }

    protected boolean nonNullNodes(Node start, Node goal) {
        return start != null && goal != null;
    }

    /**
     * Constructs the path in reverse order as a list.
     * @param end
     * The goal of the path.
     * @param start
     * The start of the path.
     * @return
     * Returns a list of the nodes on the path in reverse order.
     */
    protected List<Node> getPath(Node end, Node start) {
        List<Node> path = new ArrayList<>();
        path.add(end);

        Node node = end.getPrevious();

        while (node != start) {
            path.add(node);
            node = node.getPrevious();
        }

        path.add(start);

        return path;
    }

    protected void resetVisited() {
        visited = new boolean[mapSize][mapSize];
    }
}
