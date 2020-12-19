package pathfinding.benchmarking;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;

/**
 * A helper class for benchmarking.
 */
public class BenchmarkMap {

    private NodeMap nodeMap;
    private Node start;
    private Node goal;
    private String name;
    private double pathLength;

    /**
     * A helper class for benchmarking.
     * @param nodeMap
     * The map as a NodeMap.
     * @param a
     * The x coordinate of the start.
     * @param b
     * The y coordinate of the start.
     * @param c
     * The x coordinate of the goal.
     * @param d
     * The y coordinate of the goal.
     * @param name
     * Name of the map.
     * @param pathLength
     * The length of the path being tested.
     */
    public BenchmarkMap(NodeMap nodeMap, int a, int b, int c, int d, String name, double pathLength) {
        this.nodeMap = nodeMap;
        start = nodeMap.getNode(a, b);
        goal = nodeMap.getNode(c, d);
        this.name = name;
        this.pathLength = pathLength;
    }

    public NodeMap getNodeMap() {
        return nodeMap;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public double getPathLength() {
        return pathLength;
    }
}
