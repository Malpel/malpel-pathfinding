package pathfinding.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {

    Deque deque;
    Node start = new Node(2, 2);
    Node n1 = new Node(2, 5);

    @Before
    public void init() {
        deque = new Deque();
    }

    @Test
    public void queueingWorks() {
        assertTrue(deque.isEmpty());

        deque.enqueue(start);

        assertFalse(deque.isEmpty());
    }

    @Test
    public void dequeueingWorks() {
        deque.enqueue(start);
        deque.enqueue(n1);

        assertEquals(start, deque.dequeue());
        assertFalse(deque.isEmpty());
    }

    @Test
    public void dequeueingEmptiesQueue() {
        deque.enqueue(start);
        deque.dequeue();
        assertTrue(deque.isEmpty());
    }
}
