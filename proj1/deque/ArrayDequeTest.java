package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void checkTheBasic() {
        System.out.println("The Test 1 checkTheBasic");
        // All check is under 16 index.
        ArrayDeque<Integer> l1 = new ArrayDeque<>();
        // Check First
        assertTrue(l1.isEmpty());
        assertEquals(0, l1.size());

        l1.addFirst(0);
        l1.addLast(1);
        l1.addLast(3);
        l1.addFirst(2);
        // index : 0 1 2 3 4 5 6 7
        // value : 1 3         2 0
        // Check to add the value to the right way
        assertEquals(4, l1.size());
        assertEquals("Should have the same value", 2, (double) l1.get(0), 0.0);
        assertEquals("Should have the same value", 0, (double) l1.get(1), 0.0);
        assertEquals("Should have the same value", 1, (double) l1.get(2), 0.0);
        assertEquals("Should have the same value", 3, (double) l1.get(3), 0.0);
        assertNull(l1.get(-1));

        l1.printDeque();

        // Check to remove the right value.
        assertEquals(2, (double) l1.removeFirst(), 0.0);
        assertEquals(3, (double) l1.removeLast(), 0.0);
        assertEquals(0, (double) l1.removeFirst(), 0.0);
        assertEquals(1, (double) l1.removeLast(), 0.0);
        assertEquals(0, l1.size());
        assertNull(l1.removeFirst());
        assertNull(l1.get(114514));
    }

    @Test
    public void checkTheRevise() {
        System.out.println("The Test 2 checkTheRevise");
        ArrayDeque<Integer> l1 = new ArrayDeque<>();
        // Check the long list.

        for (int i = 0; i < 100; i++) {
            l1.addLast(i);
        }
        l1.printDeque();
        for (int i = 0; i < 100; i++) {
            assertEquals(i, (double) l1.get(i), 0.0);
        }

        assertEquals(100, l1.size());
    }

    @Test
    public void checkSequences() {
        System.out.println("The Test 3 checkSequences");
        ArrayDeque<Integer> l1 = new ArrayDeque<>();
        // Combine revise, the add, the remove
        int sample_n = 500;
        for (int i = 0; i < sample_n; i++) {
            l1.addLast(i);
            l1.addFirst(i);
        }
        l1.printDeque();
        assertEquals(sample_n * 2, l1.size());
        for (int i = 0; i < sample_n; i++) {
            assertEquals((double) sample_n - i - 1, l1.removeFirst(), 0.0);
            assertEquals((double) sample_n - i - 1, l1.removeLast(), 0.0);
        }

    }

    @Test
    public void checkTheTail() {
        System.out.println("The Test 4 checkTheTail");
        ArrayDeque<Integer> l1 = new ArrayDeque<>();
        int sample_n = 50;
        for (int i = 0; i < sample_n; i++) {
            l1.addLast(i);
            if (i % 3 == 0) {
                l1.removeFirst();
            }
        }
    }

    @Test
    public void ckIterator() {
        ArrayDeque<Integer> l1 = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            l1.addLast(i);
        }
        l1.iterator();
        for (int i : l1) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(" ------------------------ ");
        ArrayDeque<Integer> l2 = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            l2.addLast(i);
        }
        l2.removeFirst();
        l2.removeFirst();
        l2.removeFirst();

        Iterator<Integer> iterator = l2.iterator();
        int index = 0;
        int expt = 3;
        while (iterator.hasNext()) {
            int current = iterator.next();
            assertEquals("Mismatch at index " + index, expt, current);
            System.out.print(current + " ");
            index += 1;
            expt += 1;
        }
        // Shouldn`t have 0
    }
}
