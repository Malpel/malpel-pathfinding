package pathfinding.benchmarking;
;
import pathfinding.algorithms.Pathfinder;
import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;

import java.util.Arrays;

public class BenchmarkTest {

    // maybe jsut do a static method
    public BenchmarkTest() {

    }

    public void benchmark(int runs, Pathfinder pathfinder, Node start, Node goal, NodeMap nodeMap) {
        long[] times = new long[runs];
        long t;

        for (int i = 0; i < runs; i++) {
            t = System.nanoTime();
            pathfinder.search(start, goal);
            t = System.nanoTime() - t;
            times[i] = t;

            nodeMap.resetNodes();
        }

        //Arrays.sort(times);

        double s = 0;
        for (long time : times) {
            System.out.println(time);
            s += time;
        }
        System.out.println((s / times.length) / 1_000_000 + " milliseconds on average");
    }
}
