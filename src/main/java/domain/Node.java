package domain;

public class Node {

    private Node[] neighbors;
    private boolean isABlock;
    private final int x;
    private final int y;
    private Node previous;

    public Node(int y, int x) {
        this.x = x;
        this.y = y;
        previous = null;
    }

    public Node[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Node[] neighbors) {
        this.neighbors = neighbors;
    }

    public boolean isABlock() {
        return isABlock;
    }

    public void setABlock(boolean ABlock) {
        isABlock = ABlock;
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

    @Override
    public String toString() {
        return "Node{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
