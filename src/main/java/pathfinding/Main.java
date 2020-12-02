package pathfinding;

import pathfinding.algorithms.Astar;
import pathfinding.algorithms.BFS;
import pathfinding.algorithms.Dijkstra;
import pathfinding.algorithms.JPS;
import pathfinding.benchmarking.BenchmarkTest;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int mapSize = 1024;

        MapReader mapReader = new MapReader("/Paris_0_1024.map", mapSize);
        NodeMap nodeMap = mapReader.createNodeMap();

        //BenchmarkTest benchmarkTest = new BenchmarkTest();
        //benchmarkTest.benchmark(1000, new JPS(nodeMap, mapSize), nodeMap.getNode(33, 6), nodeMap.getNode(992, 994), nodeMap);

       // BFS bfs = new BFS();
        Astar astar = new Astar(mapSize);
        Dijkstra dijkstra = new Dijkstra(mapSize);
        JPS jps = new JPS(nodeMap, mapSize);

        // the format is (y, x), NOT (x, y)
        // 78, 59         243, 242      33, 6
        Node start = nodeMap.getNode(33, 6);
        // 84, 90         18, 6         992, 994
        Node goal = nodeMap.getNode(992, 994);


        dijkstra.search(start, goal);
        System.out.println("Dijkstra: ");
        System.out.println("Path length as a real length: " + goal.getPathLength());
        System.out.println("Path length as the amount of nodes traversed: " + dijkstra.pathLength(start, goal));
        nodeMap.resetNodes();
        System.out.println("");

        System.out.println("A*: ");
        astar.search(start, goal);
        System.out.println("Path length as a real length: " + goal.getPathLength());
        System.out.println("Path length as the amount of nodes traversed: " + astar.pathLength(start, goal));
        nodeMap.resetNodes();
        System.out.println("");

        System.out.println("JPS: ");
        jps.search(start, goal);
        System.out.println("Path length as a real length: " + goal.getPathLength());
        System.out.println("Path length as the amount of nodes traversed: " + jps.pathLength(start, goal));
        nodeMap.resetNodes();
        System.out.println("");

/*
        // draw the path on the map
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (nodeMap.getNode(i, j) == null) {
                    System.out.print("@");
                } else if (nodeMap.getNode(i, j).isOnThePath()){
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
*/


    }
}