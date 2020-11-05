package pathfinding.domain;

public class Node implements Comparable<Node> {

    private Node[] neighbors;
    private final int x;
    private final int y;
    private Node previous;
    private double distanceFromGoal;
    private int pathLength;

    public Node(int y, int x) {
        this.x = x;
        this.y = y;
        previous = null;
        pathLength = Integer.MAX_VALUE;
    }

    public Node[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Node[] neighbors) {
        this.neighbors = neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void heuristic(Node goal) {
        int distanceFromY = goal.getY() - this.y;
        int distanceFromX = goal.getX() - this.x;

        distanceFromGoal = Math.sqrt((distanceFromY * distanceFromY) + (distanceFromX * distanceFromX));
    }

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public String toString() {
        return "Node{" + "y=" + y + ", x=" + x + '}';
    }

    @Override
    public int compareTo(Node node) {
        return this.distanceFromGoal < node.distanceFromGoal ? 1 : 0;
    }
}
