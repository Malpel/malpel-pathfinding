package pathfinding.benchmarking;
import pathfinding.algorithms.Astar;
import pathfinding.algorithms.Dijkstra;
import pathfinding.algorithms.JPS;
import pathfinding.algorithms.Pathfinder;
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

    private NodeMap small;
    private NodeMap medium;
    private NodeMap big;
    private final Node smallStart;
    private final Node smallGoal;
    private final Node mediumStart;
    private final Node mediumGoal;
    private final Node bigStart;
    private final Node bigGoal;

    public BenchmarkTest() {

        try {
            MapReader mapReader = new MapReader();
            small = mapReader.createNodeMap("/Paris_0_256.map", 256);
            medium = mapReader.createNodeMap("/Paris_0_512.map", 512);
            big = mapReader.createNodeMap("/Paris_0_1024.map", 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * These paths use the longest path from
         * the MovingAi Paris_0 map scenarios.
         */
        smallStart = small.getNode(243, 242);
        smallGoal = small.getNode(18, 6);

        mediumStart = medium.getNode(64, 45);
        mediumGoal = medium.getNode(511, 484);

        bigStart = big.getNode(33, 6);
        bigGoal = big.getNode(992, 994);
    }

    public void benchmarkAlgorithms(int runs) {
        benchmarkAlgorithm(runs, new Dijkstra(), small, smallStart, smallGoal);
        benchmarkAlgorithm(runs, new Astar(), small, smallStart, smallGoal);
        benchmarkAlgorithm(runs, new JPS(small), small, smallStart, smallGoal);

        benchmarkAlgorithm(runs, new Dijkstra(), medium, mediumStart, mediumGoal);
        benchmarkAlgorithm(runs, new Astar(), medium, mediumStart, mediumGoal);
        benchmarkAlgorithm(runs, new JPS(medium), medium, mediumStart, mediumGoal);

        benchmarkAlgorithm(runs, new Dijkstra(), big, bigStart, bigGoal);
        benchmarkAlgorithm(runs, new Astar(), big, bigStart, bigGoal);
        benchmarkAlgorithm(runs, new JPS(big), big, bigStart, bigGoal);

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
    private void benchmarkAlgorithm(int runs, Pathfinder pathfinder, NodeMap nodeMap, Node start, Node goal) {
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

        System.out.println("Map size: " + nodeMap.getWidth());
        System.out.println(pathfinder.toString());
        System.out.println("average: " + (s / times.length) / 1000000.0 + "ms");
        Arrays.sort(times);
        System.out.println("median: " + (times[times.length / 2] / 1000000.0) + "ms");
        System.out.println("");
    }

    public void benchmarkDataStructures() {
        int n = 100000;
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

        Arrays.sort(mhPollTimes);
        System.out.println("pathfinding.domain.MinHeap poll() median: " + mhPollTimes[mhPollTimes.length / 2]  + "ns");

        Arrays.sort(pqAddTimes);
        System.out.println("java.util.PriorityQueue poll() median: " + pqPollTimes[pqPollTimes.length / 2]  + "ns");
    }

    /**
     * This is fairly redundant, since this project's implementation of the list
     * data structure only has the constant time add() function. Nevertheless, its test
     * is still included.
     */
    private void benchmarkList(int runs, int n) {
        long[] myListAddTimes = new long[runs];
        long[] javaListAddTimes = new long[runs];

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
        }

        System.out.println();
        Arrays.sort(myListAddTimes);
        System.out.println("pathfinding.domain.List add() median: " + myListAddTimes[myListAddTimes.length / 2] + "ns");

        Arrays.sort(javaListAddTimes);
        System.out.println("java.util.ArrayList add() median: " + javaListAddTimes[javaListAddTimes.length / 2] + "ns");
    }
    //CHECKSTYLE:ON
}
