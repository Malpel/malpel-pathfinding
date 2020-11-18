package pathfinding.algorithms;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AstarTest {

    Astar astar;
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
        astar = new Astar(256);
    }

    @Test
    public void findsShortPath() {
        start = nodeMap.getNode(0, 0);
        goal = nodeMap.getNode(7, 8);

        List<Node> correctPath = new ArrayList<>();

        correctPath.add(nodeMap.getNode(7, 8));
        correctPath.add(nodeMap.getNode(6, 7));
        correctPath.add(nodeMap.getNode(5, 6));
        correctPath.add(nodeMap.getNode(4, 5));
        correctPath.add(nodeMap.getNode(3, 4));
        correctPath.add(nodeMap.getNode(2, 3));
        correctPath.add(nodeMap.getNode(1, 2));
        correctPath.add(nodeMap.getNode(0, 1));
        correctPath.add(nodeMap.getNode(0, 0));

        List<Node> astarPath = astar.search(start, goal);

        assertEquals(correctPath, astarPath);
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap.getNode(123, 33);
        goal = nodeMap.getNode(123, 38);

        List<Node> astarPath = astar.search(start, goal);

        assertNull(astarPath);
    }
}
