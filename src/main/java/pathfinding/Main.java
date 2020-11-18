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

        BFS bfs = new BFS(mapSize);
        Astar astar = new Astar(mapSize);
        Dijkstra dijkstra = new Dijkstra(mapSize);
        JPS jps = new JPS(mapSize, nodeMap);

        Node start = nodeMap.getNode(0, 0);
        Node goal = nodeMap.getNode(7, 8);
/*
        List<Node> bfsPath = bfs.search(start, goal);

        for (Node node : bfsPath) {
            System.out.println(node);
        }
        List<Node> dijkstraPath = dijkstra.search(start, goal);

        for (Node node : dijkstraPath) {
            System.out.println(node);
        }
/*
/*
        List<Node> astarPath = astar.search(start, goal);

        for (Node node : astarPath) {
            System.out.println(node);
        }
*/

        List<Node> jpsPath = jps.search(start, goal);

        for (Node node : jpsPath) {
            System.out.println(node);
        }
    }

}