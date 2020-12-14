package pathfinding.ui;

import pathfinding.algorithms.Astar;
import pathfinding.algorithms.Dijkstra;
import pathfinding.algorithms.JPS;
import pathfinding.algorithms.Pathfinder;
import pathfinding.benchmarking.BenchmarkTest;
import pathfinding.domain.List;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUi {

    private final Scanner scanner;
    private final int mapSize = 256;
    private NodeMap nodeMap;
    private Node start;
    private Node goal;
    private Pathfinder pathfinder;
    private NodeMap benchmarkMap;

    public ConsoleUi(Scanner scanner) {
        this.scanner = scanner;
    }

    public void runUi() {
        label:
        while (true) {
            options();

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println();
                    selectMap();
                    setPathEndPoints();
                    selectAlgorithm();

                    List path = pathfinder.search(start, goal);

                    if (path == null) {
                        System.out.println();
                        System.out.println("No possible path between these points.");
                    } else {
                        printPath(nodeMap);
                        System.out.println();
                        System.out.println("Length of the shortest path: " + pathfinder.pathLength(start, goal));
                    }

                    break;
                case "2":
                    System.out.println();

                    BenchmarkTest benchmarkTest = new BenchmarkTest();
                    benchmarkTest.benchmarkAlgorithms(1000);

                    break;
                case "3":
                    System.out.println();
                    System.out.println("Exiting...");
                    break label;
            }

            System.out.println();
        }
    }

    private void options() {
        System.out.println("Select an option: ");
        System.out.println("1) Find shortest paths");
        System.out.println("2) Run benchmarking tests.");
        System.out.println("3) Exit program.");
        System.out.print("Enter a number (1-3): ");
    }

    private void selectMap() {
        // could be moved
        MapReader mapReader;
        String filename;

        label:
        while (true) {
            System.out.println("Select a map: ");
            System.out.println("1) Berlin");
            System.out.println("2) London");
            System.out.println("3) Moscow");
            System.out.println("4) New York");
            System.out.println("5) Paris");
            System.out.print("Enter a number (1-5): ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    filename = "/Berlin_0_256.map";
                    break label;
                case "2":
                    filename = "/London_0_256.map";
                    break label;
                case "3":
                    filename = "/Moscow_1_256.map";
                    break label;
                case "4":
                    filename = "/NewYork_0_256.map";
                    break label;
                case "5":
                    filename = "/Paris_0_256.map";
                    break label;
                default:
                    System.out.println();
                    System.out.println("Please enter a valid command.");
                    System.out.println();
                    break;
            }
        }

        System.out.println();

        mapReader = new MapReader();

        try {
            nodeMap = mapReader.createNodeMap(filename, mapSize);

        } catch (IOException e) {
            System.out.println("Couldn't open file.");
            e.printStackTrace();
        }
    }

    private void setPathEndPoints() {
        while (true) {
            System.out.print("Give start point x,y: ");
            String startString = scanner.nextLine();

            System.out.print("Give goal point x,y: ");
            String goalString = scanner.nextLine();

            String[] startCoords = startString.split(",");
            String[] goalCoords = goalString.split(",");

            boolean validStart = nodeMap.isAccessible(Integer.parseInt(startCoords[1]), Integer.parseInt(startCoords[0]));
            boolean validGoal = nodeMap.isAccessible(Integer.parseInt(goalCoords[1]), Integer.parseInt(goalCoords[0]));

            start = nodeMap.getNode(Integer.parseInt(startCoords[1]), Integer.parseInt(startCoords[0]));
            goal = nodeMap.getNode(Integer.parseInt(goalCoords[1]), Integer.parseInt(goalCoords[0]));

            if (!validStart || !validGoal) {
                System.out.println("No possible path between these points");
            } else {
                break;
            }
        }

        System.out.println();
    }

    private void selectAlgorithm() {
        System.out.println("Select algorithm: ");
        System.out.println("1) Dijkstra");
        System.out.println("2) A*");
        System.out.println("3) Jump point search");

        String input = scanner.nextLine();

        label:
        while (true) {
            switch (input) {
                case "1":
                    pathfinder = new Dijkstra();
                    break label;
                case "2":
                    pathfinder = new Astar();
                    break label;
                case "3":
                    pathfinder = new JPS(nodeMap);
                    break label;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }

    }

    private void printPath(NodeMap nodeMap) {
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

            System.out.println();
        }
    }
}
