package pathfinding.algorithms;

import pathfinding.domain.List;
import pathfinding.domain.Node;


/**
 * A superclass for the pathfinding algorithms.
 */
public abstract class Pathfinder {

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
     * Needed as a helper in certain places.
     */
    protected List getPath(Node goal, Node start) {
        List path = new List();
        goal.setOnThePath(true);

        Node node = goal;

        while (node != start) {
            path.add(node);
            node.setOnThePath(true);
            node = node.getPrevious();
        }

        path.add(start);
        start.setOnThePath(true);

        return path;
    }

    public abstract List search(Node start, Node goal);

    // need this, because currently I can't get the path length as nodes for JPS any other way
    public int pathLength(Node start, Node goal) {
        Node node = goal;
        int count = 1;

        while (node != start) {

            if (node.getY() != node.getPrevious().getY() && node.getX() != node.getPrevious().getX()) {
                count += Math.abs(node.getY() - node.getPrevious().getY());
            } else if (node.getX() != node.getPrevious().getX()) {
                count += Math.abs(node.getX() - node.getPrevious().getX());
            } else {
                count += Math.abs(node.getY() - node.getPrevious().getY());
            }

            node.setOnThePath(true);

            node = node.getPrevious();
        }

        return count;
    }
}
