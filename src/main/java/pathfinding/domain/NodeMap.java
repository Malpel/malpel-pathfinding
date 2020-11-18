package pathfinding.domain;

public class NodeMap {

    private int width;
    private int height;
    private Node[][] arr;

    public NodeMap(int width, int height) {
        arr = new Node[height][width];
        this.width = width;
        this.height = height;
    }

    public void addNode(int y, int x, Node node) {
        arr[y][x] = node;
    }

    public Node getNode(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return null;
        }

        return arr[y][x];
    }

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
}
