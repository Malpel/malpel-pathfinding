package pathfinding.domain;

/**
 * Implementation of the list data structure.
 */
public class List {

    private Node[] arr;
    private int endPointer;

    /**
     * Implementation of the list data structure.
     */
    public List() {
        arr = new Node[10];
        endPointer = 0;
    }

    /**
     * Adds a node onto the list.
     * @param node
     * The Node to be added.
     */
    public void add(Node node) {
        checkSize();
        arr[endPointer] = node;
        endPointer++;
    }

    /**
     * Returns the Node at position i on the list.
     * @param i
     * The position of the Node.
     * @return
     * The Node at position i.
     */
    public Node get(int i) {
        return arr[i];
    }

    /**
     * Resizes the array by calling extendArray() if needed.
     */
    private void checkSize() {
        if (endPointer == arr.length) {
            extendArray();
        }
    }

    /**
     * Creates a bigger array and copies the elements of the old array into it.
     */
    private void extendArray() {
        Node[] newArray = new Node[arr.length * 2];
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        arr = newArray;
    }

    public boolean isEmpty() {
        return endPointer == 0;
    }

    public int size() {
        return endPointer;
    }
}
