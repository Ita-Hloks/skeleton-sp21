package deque;

import java.util.Comparator;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxArrayDequeTest {

    @Test
    public void ckMax() {
        // naturalOrder
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(naturalOrder);

        deque.addLast(5);
        deque.addLast(10);
        deque.addLast(3);
        assertEquals(10, (double) deque.max(), 0.0);

        // absoluteOrder
        Comparator<Integer> absoluteOrder = Comparator.comparingInt(Math::abs);
        deque = new MaxArrayDeque<>(absoluteOrder);

        deque.addLast(-5);
        deque.addLast(-10);
        deque.addLast(3);
        assertEquals(-10, (double) deque.max(), 0.0);

        // Dynamic Input
        deque.addLast(20);
        deque.addLast(-30);
        assertEquals(20, (double) deque.max(Comparator.naturalOrder()), 0.0);
    }
}
