package pathfinding.domain;

/**
 * NodeMap is a representation of the map as nodes.
 */
public class NodeMap {

    private int width;
    private int height;
    private Node[][] arr;

    /**
     * NodeMap is a representation of the map as nodes.
     * Blocks are represented as nulls.
     * @param width
     * The width of the map as an integer.
     * @param height
     * The height of the map as an integer.
     */
    public NodeMap(int width, int height) {
        arr = new Node[height][width];
        this.width = width;
        this.height = height;
    }

    /**
     * Adds a node into the map.
     * @param y
     * The y coordinate as an integer.
     * @param x
     * The x coordinate as an integer.
     * @param node
     * The new Node to be added.
     */
    public void addNode(int y, int x, Node node) {
        arr[y][x] = node;
    }

    /**
     * Returns a node from the map.
     * @param y
     * The y coordinate as an integer.
     * @param x
     * The x coordinate as an integer.
     * @return
     * The Node at the coordinates (can be null),
     * or null, if the coordinates are out of bounds.
     */
    public Node getNode(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return null;
        }

        return arr[y][x];
    }

    /**
     * Tells if a Node is at the coordinates.
     * @param y
     * The y coordinate as an integer.
     * @param x
     * The x coordinate as an integer.
     * @return
     * True, if there is a Node at the coordinates,
     * otherwise null.
     */
    public boolean isAccessible(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return false;
        }

        return arr[y][x] != null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Resets all nodes.
     */
    public void resetNodes() {
        for (Node[] nodes : arr) {
            for (Node n : nodes) {
                if (n != null) {
                    n.reset();
                }
            }
        }
    }
}
