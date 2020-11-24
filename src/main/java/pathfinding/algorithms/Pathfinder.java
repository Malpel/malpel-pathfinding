package pathfinding.algorithms;

import pathfinding.domain.List;
import pathfinding.domain.Node;


/**
 * A superclass for the pathfinding algorithms.
 */
public class Pathfinder {

    /**
     *  A superclass for the pathfinding algorithms. Pathfinder takes care of checking
     *  the starting and goal's validity (not null) as well as constructing the path.
     */
    public Pathfinder() {
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
    protected List getPath(Node goal, Node start) {
        List path = new List();
        path.add(goal);

        Node node = goal.getPrevious();

        while (node != start) {
            path.add(node);
            node = node.getPrevious();
        }

        path.add(start);

        return path;
    }
}
