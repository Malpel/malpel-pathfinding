package pathfinding.benchmarking;
import pathfinding.algorithms.*;
import pathfinding.domain.List;
import pathfinding.domain.MinHeap;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BenchmarkTest {

    ArrayList<BenchmarkMap> maps = new ArrayList<>();


    public BenchmarkTest() {

        // this is stupid
        try {
            MapReader mapReader = new MapReader();
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Berlin_0_256.map", 256),
                    6, 22, 255, 253, "Berlin_0_256", 371.62950897));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Berlin_0_512.map", 512),
                    36, 32, 511, 510, "Berlin_0_512", 746.80317382));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Berlin_0_1024.map", 1024),
                    3, 19, 1002, 1005, "Berlin_0_1024", 1539.80230712));

            maps.add(new BenchmarkMap(mapReader.createNodeMap("London_0_256.map", 256),
                    221, 32, 1, 122, "London_0_256", 399.67113952));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("London_0_512.map", 512),
                    2, 227, 458, 54, "London_0_512", 831.07020111));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("London_0_1024.map", 1024),
                    943, 257, 37, 411, "London_0_1024", 1603.75562591));

            maps.add(new BenchmarkMap(mapReader.createNodeMap("Moscow_1_256.map", 256),
                    144, 11, 7, 255, "Moscow_1_256", 439.96046142));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Moscow_1_512.map", 512),
                    273, 15, 494, 501, "Moscow_1_512", 787.51385192));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Moscow_1_1024.map", 1024),
                    43, 896, 1023, 1, "Moscow_1_1024", 1546.84898833));

            maps.add(new BenchmarkMap(mapReader.createNodeMap("NewYork_0_256.map", 256),
                    252, 252, 31, 7, "NewYork_0_256", 362.90158691));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("NewYork_0_512.map", 512),
                    443, 509, 9, 0, "NewYork_0_512", 714.54328918));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("NewYork_0_1024.map", 1024),
                    22, 97, 1022, 996, "NewYork_0_1024", 1467.27539519));

            maps.add(new BenchmarkMap(mapReader.createNodeMap("Paris_0_256.map", 256),
                    243, 242, 18, 6, "Paris_0_256", 390.30360718));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Paris_0_512.map", 512),
                    64, 45, 511, 484, "Paris_0_512", 723.61940765));
            maps.add(new BenchmarkMap(mapReader.createNodeMap("Paris_0_1024.map", 1024),
                    992, 994, 33, 6, "Paris_0_1024", 1527.81955108));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void benchmarkAlgorithms(int runs) {

        for (BenchmarkMap map : maps) {
            System.out.println("Map: " + map.getName());
            System.out.println("Path length: " + map.getPathLength());
            System.out.println();
            benchmarkAlgorithm(runs, new Dijkstra(), map.getNodeMap(), map.getStart(), map.getGoal(), map.getPathLength());
            benchmarkAlgorithm(runs, new Astar(), map.getNodeMap(), map.getStart(), map.getGoal(), map.getPathLength());
            benchmarkAlgorithm(runs, new JPS(map.getNodeMap()), map.getNodeMap(), map.getStart(), map.getGoal(), map.getPathLength());
        }

    }

    /**
     * 
     * Tests the given algorithms performance on the given map.
     * @param runs
     * The amount of repeated runs as an integer.
     * @param pathfinder
     * The pathfinding algorithm to be tested.
     * @param nodeMap
     * The map on which the test should be run.
     * @param start
     * The start Node of the path.
     * @param goal
     * The goal Node of the path.
     */
    private void benchmarkAlgorithm(int runs, Pathfinder pathfinder, NodeMap nodeMap, Node start, Node goal, double pathLength) {
        long[] times = new long[runs];
        long t;

        for (int i = 0; i <= runs; i++) {
            t = System.nanoTime();
            pathfinder.search(start, goal);
            t = System.nanoTime() - t;

            if (i > 0) {
                times[i - 1] = t;
            }

            // unfortunate, but necessary
            // increases the running time enormously
            nodeMap.resetNodes();
        }

        double s = 0;
        for (long time : times) {
            s += time;
        }

        System.out.println(pathfinder.toString());
        System.out.println("Map size: " + nodeMap.getWidth() + "x" + nodeMap.getHeight());
        System.out.println("average: " + (s / times.length) / 1000000.0 + "ms");
        Arrays.sort(times);
        System.out.println("median: " + (times[times.length / 2] / 1000000.0) + "ms");
        System.out.println("");
    }

    public void benchmarkDataStructures() {
        int n = 10000;
        int runs = 1000;

        benchmarkMinHeap(runs, n);
        benchmarkList(runs, n);
    }

    //CHECKSTYLE:OFF

    /**
     * Tests the performance of the pathfinding.domain.MinHeap
     * and java.util.PriorityQueue data structures.
     * @param runs
     * The amount of repeated runs the test does as an integer.
     * @param n
     * The amount of elements to be added to the data structures.
     */
    private void benchmarkMinHeap(int runs, int n) {
        long[] mhAddTimes = new long[runs];
        long[] pqAddTimes = new long[runs];

        long[] mhPollTimes = new long[runs];
        long[] pqPollTimes = new long[runs];

        /*
         * This part tests the performance of the add() function.
         * Like its name implies, MinHeap is an implementation of
         * the binary minimum heap data structure, so the complexity
         * of the add() operation is O(log N).
         *
         */

        for (int i = 0; i < runs; i++) {
            long tAcc = 0;
            MinHeap minHeap = new MinHeap();
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                minHeap.add(new Node(i, j));
                tAcc += System.nanoTime() - t;
            }
            mhAddTimes[i] = tAcc / n;

            tAcc = 0;
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                priorityQueue.add(new Node(i, j));
                tAcc += System.nanoTime() - t;
            }
            pqAddTimes[i] = tAcc / n;

            /*
             * This part tests the performance of the poll() function.
             * Like its name implies, MinHeap is an implementation of
             * the binary minimum heap data structure, so the complexity
             * of the poll() operation is O(log N).
             *
             */

            tAcc = 0;
            while (!minHeap.isEmpty()) {
                long t = System.nanoTime();
                minHeap.poll();
                tAcc += System.nanoTime() - t;
            }
            mhPollTimes[i] = tAcc / n;

            tAcc = 0;
            while (!priorityQueue.isEmpty()) {
                long t = System.nanoTime();
                priorityQueue.poll();
                tAcc += System.nanoTime() - t;
            }
            pqPollTimes[i] = tAcc / n;
        }

        Arrays.sort(mhAddTimes);
        System.out.println("pathfinding.domain.MinHeap add() median: " + mhAddTimes[mhAddTimes.length / 2] + "ns");

        Arrays.sort(pqAddTimes);
        System.out.println("java.util.PriorityQueue add() median: " + pqAddTimes[pqAddTimes.length / 2] + "ns");

        System.out.println();
        Arrays.sort(mhPollTimes);
        System.out.println("pathfinding.domain.MinHeap poll() median: " + mhPollTimes[mhPollTimes.length / 2]  + "ns");

        Arrays.sort(pqAddTimes);
        System.out.println("java.util.PriorityQueue poll() median: " + pqPollTimes[pqPollTimes.length / 2]  + "ns");
    }

    /**
     * This is fairly redundant, since this project's implementation of the list
     * data structure only has the constant time add(Node) and get(int) functions. Nevertheless, its test
     * is still included.
     */
    private void benchmarkList(int runs, int n) {
        long[] myListAddTimes = new long[runs];
        long[] javaListAddTimes = new long[runs];
        long[] myListGetTimes = new long[runs];
        long[] javaListGetTimes = new long[runs];

        for (int i = 0; i < runs; i++) {
            long tAcc = 0;
            List myList = new List();
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                myList.add(new Node(i, j));
                tAcc += System.nanoTime() - t;
            }
            myListAddTimes[i] = tAcc / n;

            tAcc = 0;
            ArrayList<Node> javaList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                javaList.add(new Node(i, j));
                tAcc += System.nanoTime() - t;
            }
            javaListAddTimes[i] = tAcc / n;

            tAcc = 0;
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                myList.get(j);
                tAcc += System.nanoTime() - t;
            }
            myListGetTimes[i] = tAcc / n;

            tAcc = 0;
            for (int j = 0; j < n; j++) {
                long t = System.nanoTime();
                javaList.get(j);
                tAcc += System.nanoTime() - t;
            }
            javaListGetTimes[i] = tAcc / n;
        }

        System.out.println();
        Arrays.sort(myListAddTimes);
        System.out.println("pathfinding.domain.List add() median: " + myListAddTimes[myListAddTimes.length / 2] + "ns");

        Arrays.sort(javaListAddTimes);
        System.out.println("java.util.ArrayList add() median: " + javaListAddTimes[javaListAddTimes.length / 2] + "ns");

        System.out.println();
        Arrays.sort(myListGetTimes);
        System.out.println("pathfinding.domain.List get(int) median: " + myListGetTimes[myListAddTimes.length / 2] + "ns");

        Arrays.sort(javaListGetTimes);
        System.out.println("java.util.ArrayList get(int) median: " + javaListGetTimes[javaListAddTimes.length / 2] + "ns");
    }
    //CHECKSTYLE:ON
}
