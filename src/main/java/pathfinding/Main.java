package pathfinding;

import pathfinding.algorithms.Astar;
import pathfinding.algorithms.BFS;
import pathfinding.domain.Node;
import pathfinding.io.MapReader;

public class Main {

    public static void main(String[] args) throws Exception {

        MapReader mapReader = new MapReader("/home/malpel/Projects/malpel-pathfinding/src/main/resources/Paris_0_256.map");
        mapReader.createArrays();

        Node[][] nodeMap = mapReader.getNodeArray();

        BFS bfs = new BFS();
        Astar astar = new Astar();

        Node start = nodeMap[0][0];
        Node goal = nodeMap[7][8];

        Node endBfs = bfs.search(start, goal);
        Node endAstar = astar.search(start, goal);

        if (endBfs.equals(start) || endAstar.equals(start)) {
            System.out.println("No path.");
        }

        System.out.println("");
        System.out.println("-----------------");
        System.out.println("BFS path starting: ");
        System.out.println("-----------------");
        System.out.println("");

        System.out.println(endBfs);

        Node previous = endBfs.getPrevious();

        while (previous != start) {
            System.out.println(previous);
            previous = previous.getPrevious();
        }

        System.out.println(start);

        System.out.println("");
        System.out.println("-----------------");
        System.out.println("A* path starting: ");
        System.out.println("-----------------");
        System.out.println("");

        System.out.println(endAstar);

        previous = endAstar.getPrevious();

        while (previous != start) {
            System.out.println(previous);
            previous = previous.getPrevious();
        }

        System.out.println(start);

    }

}