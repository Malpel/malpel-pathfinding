package pathfinding;

import pathfinding.algorithms.Astar;
import pathfinding.algorithms.BFS;
import pathfinding.algorithms.Dijkstra;
import pathfinding.domain.Node;
import pathfinding.io.MapReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        int mapSize = 256;

        MapReader mapReader = new MapReader("/Paris_0_256.map", mapSize);

        // NEED A COPY OF NODEMAP WHOSE NODES
        // DO NOT GET CHANGED OR NEED TO CHANGE WHERE OR HOW
        // INFORMATION OF PREVIOUS NODES IF HANDLED
        Node[][] nodeMap = mapReader.createArray();

        // reset previous nodes, pathLengths, distanceFromGoal
        // in the search() functions when the nodes are processed?
        BFS bfs = new BFS(mapSize);
        Astar astar = new Astar(mapSize);
        Dijkstra dijkstra = new Dijkstra(mapSize);

        Node start = nodeMap[0][0];
        Node goal = nodeMap[7][8];
/*
        List<Node> bfsPath = bfs.search(start, goal);

        for (Node node : bfsPath) {
            System.out.println(node);
        }

        List<Node> dijkstraPath = dijkstra.search(start, goal);

        for (Node node : dijkstraPath) {
            System.out.println(node);
        }
*/

        List<Node> astarPath = astar.search(start, goal);

        for (Node node : astarPath) {
            System.out.println(node);
        }

    }

}