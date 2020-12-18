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
}
