public class Node {

    private Node[] neighbors;
    private boolean isABlock;

    public Node() {

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
}
