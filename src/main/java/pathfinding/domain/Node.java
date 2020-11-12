package pathfinding.domain;

/**
 * Node is a representation of a point or a coordinate on the text map. Only legitimate
 * points get turned into nodes - blocks or hindrances will be null.
 */
public class Node implements Comparable<Node> {

    private Node[] neighbors;
    private final int x;
    private final int y;
    /**
     * Used to build a path in reverse.
     */
    private Node previous;
    /**
     * Used for A* algorithm.
     */
    private double distanceFromGoal;
    /**
     * Used for Dijkstra's algorithm.
     */
    private double pathLength;

    /**
     * Node is a representation of a point or a coordinate on the text map. Only legitimate
     * points get turned into nodes - blocks or hindrances will be null.
     * @param y
     * The y coordinate as an integer.
     * @param x
     * the x coordinate as an integer.
     */
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

    /**
     * Calculates the distance from the goal node for the use as a heuristic in A* search.
     * @param goal
     * The goal node of the path.
     */
    public void heuristic(Node goal) {
        int distanceFromY = goal.getY() - this.y;
        int distanceFromX = goal.getX() - this.x;

        distanceFromGoal = Math.sqrt((distanceFromY * distanceFromY) + (distanceFromX * distanceFromX));
    }

    public double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public String toString() {
        return "Node{" + "y=" + y + ", x=" + x + '}';
    }

    @Override
    public int compareTo(Node node) {
        return this.distanceFromGoal <= node.distanceFromGoal ? 1 : 0;
    }
}
