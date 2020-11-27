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
        MapReader mapReader = new MapReader("/Paris_0_256.map", 256);

        try {
            nodeMap = mapReader.createNodeMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        jps = new JPS(nodeMap);

        for (int i = 0; i < nodeMap.getWidth(); i++) {
            for (int j = 0; j < nodeMap.getWidth(); j++) {
                if (nodeMap.isAccessible(i, j)) {
                    nodeMap.getNode(i, j).reset();
                }
            }
        }
    }

    @Test
    public void findsShortPath() {
        start = nodeMap.getNode(0, 0);
        goal = nodeMap.getNode(7, 8);

        jps.search(start, goal);

        assertEquals(10, jps.pathLength(start, goal));
    }

    @Test
    public void longerPathIsCorrectLength() {
        start = nodeMap.getNode(78, 59);
        goal = nodeMap.getNode(84, 90);

        jps.search(start, goal);

        assertEquals(44, jps.pathLength(start, goal));
    }

    @Test
    public void evenLongerPathIsCorrectLength() {
        start = nodeMap.getNode(243, 242);
        goal = nodeMap.getNode(18, 6);

        jps.search(start, goal);

        assertEquals(326, jps.pathLength(start, goal));
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap.getNode(123, 33);
        goal = nodeMap.getNode(123, 38);

        List jpsPath = jps.search(start, goal);

        assertNull(jpsPath);
    }
}
