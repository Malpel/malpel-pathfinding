import algorithms.BFS;
import domain.Node;
import io.MapReader;

public class Main {

    public static void main(String[] args) throws Exception {

        MapReader mapReader = new MapReader("/home/malpel/Projects/malpel-pathfinding/src/main/resources/Paris_0_256.map");
        mapReader.createArrays();

        String[] stringMap = mapReader.getStringArray();
        Node[][] nodeMap = mapReader.getNodeArray();

        BFS bfs = new BFS();

        Node start = nodeMap[0][0];
        Node goal = nodeMap[7][8];
        Node end = bfs.search(start, goal);

        if (end.equals(start)) {
            System.out.println("No path.");
        }

        System.out.println("");
        System.out.println("-----------------");
        System.out.println("Path starting: ");
        System.out.println("-----------------");
        System.out.println("");

        System.out.println(end);

        Node previous = end.getPrevious();

        while (previous != start) {
            System.out.println(previous);
            previous = previous.getPrevious();
        }

        System.out.println(start);

    }

}