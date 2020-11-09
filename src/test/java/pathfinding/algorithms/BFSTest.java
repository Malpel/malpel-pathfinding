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

public class BFSTest {

    BFS bfs;
    static Node[][] nodeMap;
    Node start;
    Node goal;

    @BeforeClass
    public static void readMap() {
        MapReader mapReader = new MapReader("/home/malpel/Projects/malpel-pathfinding/src/test/resources/Paris_0_256.map", 256);

        try {
            nodeMap = mapReader.createArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
         bfs = new BFS(256);
    }

    @Test
    public void findsShortPath() {
        start = nodeMap[0][0];
        goal = nodeMap[7][8];

        List<Node> correctPath = new ArrayList<>();

        correctPath.add(nodeMap[7][8]);
        correctPath.add(nodeMap[6][7]);
        correctPath.add(nodeMap[5][6]);
        correctPath.add(nodeMap[4][5]);
        correctPath.add(nodeMap[3][4]);
        correctPath.add(nodeMap[2][3]);
        correctPath.add(nodeMap[1][2]);
        correctPath.add(nodeMap[0][1]);
        correctPath.add(nodeMap[0][0]);

        List<Node> bfsPath = bfs.search(start, goal);

        assertEquals(correctPath, bfsPath);
    }

    @Test
    public void noPathIfGoalUnreachable() {
        start = nodeMap[123][33];
        goal = nodeMap[123][38];

        List<Node> bfsPath = bfs.search(start, goal);

        assertNull(bfsPath);
    }
}
