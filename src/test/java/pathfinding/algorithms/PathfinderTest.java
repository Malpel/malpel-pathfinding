package pathfinding.algorithms;

import org.junit.BeforeClass;
import org.junit.Test;
import pathfinding.domain.Node;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathfinderTest {

    static Pathfinder pathfinder;

    @BeforeClass
    public static void createPathfinder() {
        pathfinder = new Pathfinder(256);
    }

    @Test
    public void nullNodesReturnFalse() {
        Node nullNode = null;
        Node notNullNode = new Node(2, 5);

        assertFalse(pathfinder.nonNullNodes(nullNode, notNullNode));
        assertFalse(pathfinder.nonNullNodes(notNullNode, nullNode));
        assertFalse(pathfinder.nonNullNodes(nullNode, nullNode));
    }

    @Test
    public void realNodesReturnTrue() {
        Node real1 = new Node(54, 23);
        Node real2 = new Node(101, 231);

        assertTrue(pathfinder.nonNullNodes(real1, real2));
    }

    @Test
    public void visitedIsReset() {
        pathfinder.visited[34][34] = true;
        pathfinder.resetVisited();

        assertFalse(pathfinder.visited[34][34]);
    }
}
