package pathfinding.domain;

// I got lazy
public class Deque {

    private final List list;
    private int frontPointer;

    public Deque() {
        list = new List();
        frontPointer = 0;
    }

    public void enqueue(Node node) {
        list.add(node);
    }

    public Node dequeue() {
        Node returnee = list.get(frontPointer);
        frontPointer++;
        return returnee;
    }

    public boolean isEmpty() {
        return list.get(frontPointer) == null;
    }

}
