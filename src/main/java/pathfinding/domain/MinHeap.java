package pathfinding.domain;


/**
 * Implementation of a minimum heap.
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

    /**
     * Implementation of a minimum heap.
     */
    public MinHeap() {
        arr = new Node[11];
        endPointer = 1;
        size = 0;
    }

    /**
     * Implementation of a minimum heap.
     * @param arr
     * A ready made heap in array form.
     */
    public MinHeap(Node[] arr) {
        this.arr = arr;
        endPointer = arr.length;
        size = arr.length - 1;
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

        swapOrderPoll(1);
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

        while (parent > 0 && compare(arr[child], arr[parent]) < 0) {
            swap(child, parent);
            child = parent;
            parent = parent / 2;
        }
    }

    /**
     * Orders the heap after polling/removal.
     */
    private void swapOrderPoll(int i) {
        int parent = i;
        int leftChild = parent * 2;
        int rightChild = (parent * 2) + 1;

        if (leftChild < endPointer && compare(arr[leftChild], arr[parent]) < 0) {
            parent = leftChild;
        }

        if (rightChild < endPointer && compare(arr[rightChild], arr[parent]) < 0) {
            parent = rightChild;
        }

        if (parent != i) {
            swap(parent, i);
            swapOrderPoll(parent);
        }
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


    public int compare(Node node, Node t1) {
        if ((node.getDistanceFromGoal() + node.getPathLength()) > (t1.getDistanceFromGoal() + t1.getPathLength())) {
            return 1;
        } else if ((node.getDistanceFromGoal() + node.getPathLength()) == (t1.getDistanceFromGoal() + t1.getPathLength())) {
            return 0;
        }
        return -1;
    }
}
