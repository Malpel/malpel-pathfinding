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
        int mapSize = 256;

        MapReader mapReader = new MapReader("/Paris_0_256.map", mapSize);
        NodeMap nodeMap = mapReader.createNodeMap();

        BenchmarkTest benchmarkTest = new BenchmarkTest();
        benchmarkTest.benchmark(1000, new JPS(nodeMap), nodeMap.getNode(243, 242), nodeMap.getNode(18, 6), nodeMap);
/*
        BFS bfs = new BFS();
        Astar astar = new Astar();
        Dijkstra dijkstra = new Dijkstra();
        JPS jps = new JPS(nodeMap);

        // the format is (y, x), NOT (x, y)
        // 78, 59         243, 242      33, 6
        Node start = nodeMap.getNode(243, 242);
        // 84, 90         18, 6         992, 994
        Node goal = nodeMap.getNode(18, 6);

        // These cannot be run all in one go,
        // doesn't reset nodes in between.

        //bfs.search(start, goal);
        //dijkstra.search(start, goal);

        //astar.search(start, goal);
        jps.search(start, goal);

        System.out.println("Path length as a real length: " + goal.getPathLength());
        System.out.println("Path length as the amount of nodes traversed: " + jps.pathLength(start, goal));


 */
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