package pathfinding.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    List list;
    Node start = new Node(2, 2);
    Node n1 = new Node(2, 5);
    Node n2 = new Node(4, 5);
    Node n3 = new Node(6, 6);
    Node n4 = new Node(7, 8);
    Node goal = new Node(9, 9);

    @Before
    public void init() {
        list = new List();
    }

    @Test
    public void addingToTheListWorks() {
        assertTrue(list.isEmpty());

        list.add(n1);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(n1, list.get(0));
    }

    @Test
    public void addingToABiggerListWorks() {
        list.add(start);
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(goal);
        list.add(new Node(12, 11));
        list.add(new Node(13, 14));
        list.add(new Node(14, 12));
        list.add(new Node(12, 14));

        assertEquals(10, list.size());

        Node eleventh = new Node(123, 123);
        list.add(eleventh);

        assertEquals(11, list.size());
        assertEquals(eleventh, list.get(10));
    }
}
