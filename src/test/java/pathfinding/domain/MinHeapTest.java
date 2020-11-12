package pathfinding.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeapTest {

    MinHeap minHeap;
    Node start = new Node(2, 2);
    Node n1 = new Node(2, 5);
    Node n2 = new Node(4, 5);
    Node n3 = new Node(6, 6);
    Node n4 = new Node(7, 8);
    Node goal = new Node(9, 9);

    @Before
    public void init() {
        start.heuristic(goal);
        n1.heuristic(goal);
        n2.heuristic(goal);
        n3.heuristic(goal);
        n4.heuristic(goal);

        minHeap = new MinHeap();
    }

    @Test
    public void addingToEmptyHeapWorks() {
        minHeap.add(start);

        assertFalse(minHeap.isEmpty());
        assertEquals(start, minHeap.peek());
        assertEquals(1, minHeap.size());
    }

    @Test
    public void addingToPopulatedHeapWorks() {
        Node[] arr = { null, start, n1, n2 };
        minHeap = new MinHeap(arr);

        minHeap.add(n3);
        assertEquals(4, minHeap.size());
    }

    @Test
    public void heapMinimumIsCorrectAfterAddition() {
        Node[] arr = { null, n1, start };
        minHeap = new MinHeap(arr);

        minHeap.add(n3);
        assertEquals(n3, minHeap.peek());

        minHeap.add(n2);
        assertNotEquals(n2, minHeap.peek());

        minHeap.add(n4);
        assertEquals(n4, minHeap.peek());
    }

    @Test
    public void pollingAffectsSize() {
        Node[] arr = { null, n1, start };
        minHeap = new MinHeap(arr);

        minHeap.poll();
        assertEquals(1, minHeap.size());
    }

    @Test
    public void heapMinimumIsCorrectAfterPolling() {
        Node[] arr = { null, n4, n2, n1, start };
        minHeap = new MinHeap(arr);

        minHeap.poll();
        assertEquals(n2, minHeap.peek());
    }

    @Test
    public void heapMinimumIsCorrectAfterPollingVersion2() {
        Node[] arr = { null, n4, n1, n3, start, n2 };
        minHeap = new MinHeap(arr);

        minHeap.poll();
        assertEquals(n3, minHeap.peek());
    }

    @Test
    public void heapMinimumIsCorrectAfterPollingVersion3() {
        Node[] arr = { null, n4, n3, n1, start, n2 };
        minHeap = new MinHeap(arr);

        minHeap.poll();
        assertEquals(n3, minHeap.peek());
    }

    @Test
    public void heapMinimumIsCorrectAfterPollingVersion4() {
        Node[] arr = { null, n4, n2, n3, n1, start };
        minHeap = new MinHeap(arr);

        minHeap.poll();
        assertEquals(n3, minHeap.peek());
    }

    @Test
    public void heapIsEmptyAfterPollingTheLastItem() {
        Node[] arr = { null, start };
        minHeap = new MinHeap(arr);

        minHeap.poll();

        assertEquals(0, minHeap.size());
        assertTrue(minHeap.isEmpty());
    }
}
