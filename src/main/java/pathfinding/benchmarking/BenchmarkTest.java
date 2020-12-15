package pathfinding.benchmarking;
import pathfinding.algorithms.Astar;
import pathfinding.algorithms.Dijkstra;
import pathfinding.algorithms.JPS;
import pathfinding.algorithms.Pathfinder;
import pathfinding.domain.MinHeap;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;
import pathfinding.io.MapReader;

import java.io.IOException;
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

        smallStart = small.getNode(243, 242);
        smallGoal = small.getNode(18, 6);

        mediumStart = medium.getNode(64, 45);
        mediumGoal = medium.getNode(511, 484);

        bigStart = big.getNode(33, 6);
        bigGoal = big.getNode(992, 994);
    }

    public void benchmarkAlgorithms(int runs) {
        System.out.println("Map size: 256");
        //System.out.println("Dijkstra's algorithm:");
        benchmarkAlgorithm(runs, new Dijkstra(), small, smallStart, smallGoal);
        benchmarkAlgorithm(runs, new Astar(), small, smallStart, smallGoal);
        benchmarkAlgorithm(runs, new JPS(small), small, smallStart, smallGoal);
/*
        benchmarkAlgorithm(runs, new Dijkstra(), medium, mediumStart, mediumGoal);
        benchmarkAlgorithm(runs, new Astar(), medium, mediumStart, mediumGoal);
        benchmarkAlgorithm(runs, new JPS(medium), medium, mediumStart, mediumGoal);

        benchmarkAlgorithm(runs, new Dijkstra(), big, bigStart, bigGoal);
        benchmarkAlgorithm(runs, new Astar(), big, bigStart, bigGoal);
        benchmarkAlgorithm(runs, new JPS(big), big, bigStart, bigGoal);

 */
    }

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

            nodeMap.resetNodes();
        }

        double s = 0;
        for (long time : times) {
            s += time;
        }

        System.out.println(pathfinder.toString());
        System.out.println("average: " + (s / times.length) / 1000000.0 + "ms");
        Arrays.sort(times);
        System.out.println("median: " + (times[times.length / 2] / 1000000.0) + "ms");
        System.out.println("");
    }

    //CHECKSTYLE:OFF
    public void benchmarkDatastructures() {
        int n = 100000;
        int runs = 1000;

        long[] mhAddTimes = new long[runs];
        long[] pqAddTimes = new long[runs];

        long[] mhPollTimes = new long[runs];
        long[] pqPollTimes = new long[runs];

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
        System.out.println("MinHeap add() median: " + mhAddTimes[mhAddTimes.length / 2] + "ns");

        Arrays.sort(pqAddTimes);
        System.out.println("Java's PriorityQueue<> add() median: " + pqAddTimes[pqAddTimes.length / 2] + "ns");

        Arrays.sort(mhPollTimes);
        System.out.println("MinHeap poll() median: " + mhPollTimes[mhPollTimes.length / 2]  + "ns");

        Arrays.sort(pqAddTimes);
        System.out.println("Java's PriorityQueue<> poll() median: " + pqPollTimes[pqPollTimes.length / 2]  + "ns");
    }
    //CHECKSTYLE:ON
}
