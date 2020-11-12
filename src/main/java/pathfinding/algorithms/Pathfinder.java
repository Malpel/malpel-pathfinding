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
     *  A superclass for the pathfinding algorithms. Pathfinder takes care of the boolean[][] visited array,
     *  as well as constructing the path.
     * @param mapSize
     * The map size as an integer.
     */
    public Pathfinder(int mapSize) {
        visited = new boolean[mapSize][mapSize];
        this.mapSize = mapSize;
    }

    /**
     * Checks that neither the start nor the goal node is a null, invalid node.
     * @param start
     * The starting point of the path as a node.
     * @param goal
     * The end of the path as a node.
     * @return
     * True if both start and goal are valid, otherwise false.
     */
    protected boolean nonNullNodes(Node start, Node goal) {
        return start != null && goal != null;
    }

    /**
     * Constructs the path in reverse order as a list.
     * @param goal
     * The goal of the path.
     * @param start
     * The start of the path.
     * @return
     * A ist of the nodes on the path in reverse order.
     */
    protected List<Node> getPath(Node goal, Node start) {
        List<Node> path = new ArrayList<>();
        path.add(goal);

        Node node = goal.getPrevious();

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
