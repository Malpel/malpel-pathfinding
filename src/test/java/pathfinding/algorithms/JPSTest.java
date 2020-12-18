package pathfinding.algorithms;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;
import pathfinding.domain.List;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JPSTest {

    JPS jps;
    static NodeMap nodeMap;
    Node start;
    Node goal;

    @BeforeClass
    public static void readMap() {
        MapReader mapReader = new MapReader();

        try {
            nodeMap = mapReader.createNodeMap("/Paris_0_256.map", 256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        jps = new JPS(nodeMap);
        nodeMap.resetNodes();
    }

    @Test
    public void findsShortPath() {
        start = nodeMap.getNode(35, 35);
        goal = nodeMap.getNode(43, 39);

        jps.search(start, goal);

        assertEquals(10.82842712, goal.getPathLength(), 0.001);
    }

    @Test
    public void longerPathIsCorrectLength() {
        start = nodeMap.getNode(101, 106);
        goal = nodeMap.getNode(85, 155);

        jps.search(start, goal);

        assertEquals(55.62741699, goal.getPathLength(), 0.001);
    }

    @Test
    public void evenLongerPathIsCorrectLength() {
        start = nodeMap.getNode(7, 27);
        goal = nodeMap.getNode(249, 249);

        jps.search(start, goal);

        assertEquals(387.44574280, goal.getPathLength(), 0.001);
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap.getNode(123, 33);
        goal = nodeMap.getNode(123, 38);

        List jpsPath = jps.search(start, goal);

        assertNull(jpsPath);
        assertEquals(999999.999, goal.getPathLength(), 0.0);
    }
}
