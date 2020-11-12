package pathfinding.domain;

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

    /**
     * Implementation of a binary minimum heap.
     */
    public MinHeap() {
        arr = new Node[25];
        endPointer = 1;
        size = 0;
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

        checkSize();
    }

    /**
     * Add a new node into the minimum heap.
     * @param node
     * The node to be added.
     */
    public void add(Node node) {
        arr[endPointer] = node;

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

        while (arr[child].compareTo(arr[parent]) > 0) {
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

            if (breakCondition(parent, leftChild, rightChild)) {
                break;
            }

            if ((arr[leftChild] != null && arr[rightChild] != null) && (arr[leftChild].compareTo(arr[parent]) > 0 && arr[rightChild].compareTo(arr[parent]) > 0)) {
                if (arr[rightChild].compareTo(arr[leftChild]) > 0) {
                    swap(parent, rightChild);
                    parent = rightChild;
                } else {
                    swap(parent, leftChild);
                    parent = leftChild;
                }

            } else if (arr[leftChild] != null && arr[leftChild].compareTo(arr[parent]) > 0) {
                swap(parent, leftChild);
                parent = leftChild;
            } else if (arr[rightChild] != null && arr[rightChild].compareTo(arr[parent]) > 0) {
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
        } else if (arr[leftChild].compareTo(arr[parent]) <= 0 && arr[rightChild] == null) {
            return true;
        }

        return arr[leftChild].compareTo(arr[parent]) <= 0 && arr[rightChild].compareTo(arr[parent]) <= 0;
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
    // try this with own implementation instead of System.arraycopy
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
}
