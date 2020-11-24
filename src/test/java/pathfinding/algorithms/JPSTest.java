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
    }

    @Test
    public void findsShortPath() {
        start = nodeMap.getNode(0, 0);
        goal = nodeMap.getNode(7, 8);

        List correctPath = new List();

        correctPath.add(nodeMap.getNode(7, 8));
        correctPath.add(nodeMap.getNode(7, 7));
        correctPath.add(nodeMap.getNode(6, 6));
        correctPath.add(nodeMap.getNode(5, 5));
        correctPath.add(nodeMap.getNode(4, 4));
        correctPath.add(nodeMap.getNode(3, 3));
        correctPath.add(nodeMap.getNode(2, 2));
        correctPath.add(nodeMap.getNode(1, 1));
        correctPath.add(nodeMap.getNode(0, 0));

        List jpsPath = jps.search(start, goal);

        assertEquals(correctPath, jpsPath);
    }

    @Test
    public void longerPathIsCorrectLength() {
        start = nodeMap.getNode(78, 59);
        goal = nodeMap.getNode(84, 90);

        List jpsPath = jps.search(start, goal);

        assertEquals(43, jpsPath.size());
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap.getNode(123, 33);
        goal = nodeMap.getNode(123, 38);

        List jpsPath = jps.search(start, goal);

        assertNull(jpsPath);
    }
}
