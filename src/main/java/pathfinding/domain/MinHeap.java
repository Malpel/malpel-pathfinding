package pathfinding.domain;

import java.util.Comparator;

/**
 * Implementation of a binary minimum heap.
 */
public class MinHeap {

    private Node[] arr;
    /**
     * EndPointer points to the end, past the last node in the queue,
     * not to the last node itself.
     */
    private int endPointer;
    private boolean isEmpty;
    private int size;
    private final Comparator<Node> comparator;

    /**
     * Implementation of a binary minimum heap.
     */
    public MinHeap() {
        arr = new Node[10];
        endPointer = 1;
        size = 0;
        comparator = new AstarComparator();
    }

    public MinHeap(Comparator<Node> comparator) {
        arr = new Node[10];
        endPointer = 1;
        size = 0;
        this.comparator = comparator;
    }

    /**
     * Implementation of a binary minimum heap.
     * @param arr
     * A ready made heap in array form.
     */
    public MinHeap(Node[] arr) {
        this.arr = arr;
        endPointer = arr.length;
        size = arr.length - 1;
        comparator = new AstarComparator();
        checkSize();
    }

    /**
     * Add a new node into the minimum heap.
     * @param node
     * The node to be added.
     */
    public void add(Node node) {
        arr[endPointer] = node;
        isEmpty = false;

        if (endPointer > 1) {
            swapOrderAdd();
        }

        endPointer++;
        size++;
        checkSize();
    }

    /**
     * Peek returns the minimum element of the heap.
     * @return
     * The minimum Node element of the heap.
     */
    public Node peek() {
        return arr[1];
    }

    /**
     * Poll returns the minimum element of the heap and removes it from the heap.
     * @return
     * The minimum Node element of the heap.
     */
    public Node poll() {
        Node minNode = arr[1];
        endPointer -= 1;

        arr[1] = arr[endPointer];
        arr[endPointer] = null;

        swapOrderPoll();
        size--;
        checkSize();

        return minNode;
    }

    /**
     * Orders the heap after addition.
     */
    private void swapOrderAdd() {
        int parent = endPointer / 2;
        int child = endPointer;

        while (comparator.compare(arr[child], arr[parent]) <= 0) {
            swap(child, parent);
            child = parent;
            parent = parent / 2;

            if (parent < 1) {
                break;
            }
        }
    }

    /**
     * Orders the heap after polling/removal.
     */
    private void swapOrderPoll() {
        int parent = 1;

        while (true) {
            int leftChild = parent * 2;
            int rightChild = (parent * 2) + 1;

            if ((leftChild >= arr.length && rightChild >= arr.length) || breakCondition(parent, leftChild, rightChild)) {
                break;
            }

            if (comparator.compare(arr[leftChild], arr[parent]) <= 0 && comparator.compare(arr[rightChild], arr[parent]) <= 0) {
                if (comparator.compare(arr[rightChild], arr[leftChild]) <= 0) {
                    swap(parent, rightChild);
                    parent = rightChild;
                } else {
                    swap(parent, leftChild);
                    parent = leftChild;
                }

            } else if (comparator.compare(arr[leftChild], arr[parent]) <= 0) {
                swap(parent, leftChild);
                parent = leftChild;
            } else if (comparator.compare(arr[rightChild], arr[parent]) <= 0) {
                swap(parent, rightChild);
                parent = rightChild;
            }
        }
    }

    /**
     * Used for breaking the while loop in swapOrderPoll().
     * @param parent
     * The current node in processing.
     * @param leftChild
     * The left child node of the parent.
     * @param rightChild
     * The right child node of the parent.
     * @return
     * True, if both children null or if neither child is an improvement, otherwise false.
     */
    private boolean breakCondition(int parent, int leftChild, int rightChild) {
        if (arr[leftChild] == null && arr[rightChild] == null)  {
            return true;
        } else if (comparator.compare(arr[leftChild], arr[parent]) > 0 && arr[rightChild] == null) {
            return true;
        }

        return comparator.compare(arr[leftChild], arr[parent]) > 0 && comparator.compare(arr[rightChild], arr[parent]) > 0;
    }

    /**
     * Helper function for ordering the heap.
     * @param parent
     * The node currently in processing.
     * @param child
     * The child node of the parent.
     */
    private void swap(int parent, int child) {
        Node swapHelper = arr[child];
        arr[child] = arr[parent];
        arr[parent] = swapHelper;
    }

    /**
     * Resizes the array by calling extendArray() if needed
     * or sets the size to zero if the heap is empty.
     */
    private void checkSize() {
        if (size == arr.length - 1) {
            extendArray();
        } else if (endPointer == 1 && arr[endPointer] == null) {
            isEmpty = true;
            size = 0;
        }
    }

    /**
     * Creates a bigger array and copies the elements of the old array into it.
     */
    private void extendArray() {
        Node[] newArray = new Node[arr.length * 2];
        System.arraycopy(arr, 1, newArray, 1, arr.length - 1);
        arr = newArray;
    }


    public boolean isEmpty() {
        return isEmpty;
    }

    public int size() {
        return size;
    }

    static class AstarComparator implements Comparator<Node> {

        @Override
        public int compare(Node node, Node t1) {
            if (node == null || t1 == null) {
                return 1;
            }

            if (node.getDistanceFromGoal() > t1.getDistanceFromGoal()) {
                return 1;
            } else if (node.getDistanceFromGoal() == t1.getDistanceFromGoal()) {
                return 0;
            }
            return -1;
        }
    }
}
