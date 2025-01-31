package deque;

import java.util.*;

public class LinkedListDeque<T> implements Iterable<T>, deque.Deque<T> {
    private class IntNode {
        private final T item;
        private IntNode next;
        private IntNode before;

        IntNode(T i, IntNode b, IntNode n) {
            item = i;
            next = n;
            before = b;
        }
    }

    private int size;
    private final IntNode sentinel;


    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.before = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (sentinel.next == sentinel) {
            sentinel.next = new IntNode(item, sentinel, sentinel);
            sentinel.before = sentinel.next;
        } else {
            sentinel.next = new IntNode(item, sentinel, sentinel.next);
            sentinel.next.next.before = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (sentinel.next == sentinel) {
            sentinel.next = new IntNode(item, sentinel, sentinel);
            sentinel.before = sentinel.next;
        } else {
            sentinel.before = new IntNode(item, sentinel.before, sentinel);
            sentinel.before.before.next = sentinel.before;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T remove = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.before = sentinel;
        size -= 1;
        return remove;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T remove = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        size -= 1;
        return remove;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            IntNode curr = sentinel.next;
            for (int i = index; i > 0; i--) {
                curr = curr.next;
            }
            return curr.item;
        }
    }

    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        } else {
            return helperGetRecursive(sentinel.next, index);
        }
    }

    private T helperGetRecursive(IntNode curr, int index) {
        // This is a helper function. So is private.
        if (index > 0) {
            return helperGetRecursive(curr.next, index - 1);
        } else {
            return curr.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CreatLLDIterator();
    }


    private class CreatLLDIterator implements Iterator<T> {
        private IntNode point = sentinel.next;

        @Override
        public boolean hasNext() {
            return point != sentinel;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnItem = point.item;
                point = point.next;
                return returnItem;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (size != ((Deque<T>) o).size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(((Deque<T>) o).get(i))) {
                return false;
            }
        }
        return true;
    }
}
