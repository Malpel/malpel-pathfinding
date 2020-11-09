package pathfinding.algorithms;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pathfinding.domain.Node;
import pathfinding.io.MapReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DijkstraTest {

    Dijkstra dijkstra;
    static Node[][] nodeMap;
    Node start;
    Node goal;

    @BeforeClass
    public static void readMap() {
        MapReader mapReader = new MapReader("/Paris_0_256.map", 256);

        try {
            nodeMap = mapReader.createArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        dijkstra = new Dijkstra(256);
    }

    @Test
    public void findsShortPath() {
        start = nodeMap[0][0];
        goal = nodeMap[7][8];

        List<Node> correctPath = new ArrayList<>();

        correctPath.add(nodeMap[7][8]);
        correctPath.add(nodeMap[7][7]);
        correctPath.add(nodeMap[6][6]);
        correctPath.add(nodeMap[5][5]);
        correctPath.add(nodeMap[4][4]);
        correctPath.add(nodeMap[3][3]);
        correctPath.add(nodeMap[2][2]);
        correctPath.add(nodeMap[1][1]);
        correctPath.add(nodeMap[0][0]);

        List<Node> dijkstraPath = dijkstra.search(start, goal);

        assertEquals(correctPath, dijkstraPath);
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap[123][33];
        goal = nodeMap[123][38];

        List<Node> dijkstraPath = dijkstra.search(start, goal);

        assertNull(dijkstraPath);
    }
}
