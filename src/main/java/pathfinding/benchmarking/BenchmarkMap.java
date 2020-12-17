package pathfinding.benchmarking;

import pathfinding.domain.Node;
import pathfinding.domain.NodeMap;

public class BenchmarkMap {

    private NodeMap nodeMap;
    private Node start;
    private Node goal;
    private String name;

    public BenchmarkMap(NodeMap nodeMap, int a, int b, int c, int d, String name) {
        this.nodeMap = nodeMap;
        start = nodeMap.getNode(a, b);
        goal = nodeMap.getNode(c, d);
        this.name = name;
    }

    public NodeMap getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(NodeMap nodeMap) {
        this.nodeMap = nodeMap;
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
}
