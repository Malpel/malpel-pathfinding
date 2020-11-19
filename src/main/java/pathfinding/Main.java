package pathfinding;

import pathfinding.algorithms.Astar;
import pathfinding.algorithms.BFS;
import pathfinding.algorithms.Dijkstra;
import pathfinding.algorithms.JPS;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        int mapSize = 256;

        MapReader mapReader = new MapReader("/Paris_0_256.map", mapSize);

        NodeMap nodeMap = mapReader.createNodeMap();

        BFS bfs = new BFS();
        Astar astar = new Astar();
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS(nodeMap);

        // 78, 59
        Node start = nodeMap.getNode(78, 59);
        // 84, 90
        Node goal = nodeMap.getNode(84, 90);

        // 43 is the path length of the above
/*
        List<Node> bfsPath = bfs.search(start, goal);

        for (Node node : bfsPath) {
            System.out.println(node);
        }
        System.out.println(bfsPath.size());

        List<Node> dijkstraPath = dijkstra.search(start, goal);

        for (Node node : dijkstraPath) {
            System.out.println(node);
        }

        System.out.println(dijkstraPath.size());


*//*
        List<Node> astarPath = astar.search(start, goal);

        for (Node node : astarPath) {
            System.out.println(node);
        }

        System.out.println(astarPath.size());

*/

        List<Node> jpsPath = jps.search(start, goal);

        for (Node node : jpsPath) {
            System.out.println(node);
        }

        System.out.println(jpsPath.size());

    }
}