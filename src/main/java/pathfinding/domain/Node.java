package pathfinding.domain;


import pathfinding.util.MathUtils;

/**
 * Node is a representation of a point or a coordinate on the text map. Only legitimate
 * points get turned into nodes - blocks or hindrances will be null.
 */
public class Node implements Comparable<Node> {

    private List neighbors;
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

    private boolean visited;
    private boolean onThePath;

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
        pathLength = 999999.999;
        visited = false;
    }

    public List getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List neighbors) {
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
        distanceFromGoal = MathUtils.shortestDistance(this, goal);
    }

    public double getDistanceFromGoal() {
        return distanceFromGoal;
    }

    public double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Node{" + "y=" + y + ", x=" + x + '}';
    }

    @Override
    public int compareTo(Node node) {
        return Double.compare(this.distanceFromGoal, node.getDistanceFromGoal());
    }

    /**
     * Resets information that was modified in
     * a search back to their defaults.
     */
    public void reset() {
        pathLength = 999999.999;
        previous = null;
        visited = false;
        onThePath = false;
        visited = false;
    }

    public boolean isOnThePath() {
        return onThePath;
    }

    public void setOnThePath(boolean onThePath) {
        this.onThePath = onThePath;
    }
}
