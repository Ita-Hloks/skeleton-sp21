package deque;

import java.util.*;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] array;
    private int size;
    private int front;
    private int back;

    /* Notice.
     * 1. The front and back shouldn`t be negative, we only use the positive number
     * 2. the array maybe not progression
     *      (for the basic java array, e.g. | 1 | 2 |  |  | 3 | 4 | ) but is progression for ours
     * 3. When front equal 0, and you use addFirst, you should put it to end (array.length - 1)
     *      and front = array.length - 1
     * 4. 'back' is always point to the null.
     * 5. you should always remember that we find the value via the front and back!!
     * 6. I use the 'tail' to help calculate, e.g. | 1 | 2 |  |  |  | 6 |
     *      the tail == 1, which calculate by len(6) - front(5)
     * */

    public ArrayDeque() {
        // The ArrayDeque length must be 8.
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void revise(int situation) {
        T[] newArray;
        if (situation == 0) {
            newArray = (T[]) new Object[(int) (array.length * 0.25)];
        } else {
            newArray = (T[]) new Object[(int) (array.length * 1.25)];
        }
        int prevFront = front;
        // back don`t need any fix
        if (front - back > 0) {
            int tail = array.length - front;
            front = newArray.length - tail;
            System.arraycopy(array, 0, newArray, 0, back);
            System.arraycopy(array, prevFront, newArray, front, tail);
        } else {
            System.arraycopy(array, front, newArray, front, size);
        }
        // finish!

        array = newArray;
    }

    @Override
    public void addFirst(T x) {
        judgeToRevise();
        front = (front - 1 + array.length) % array.length;
        array[front] = x;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        judgeToRevise();
        array[back] = x;
        back = (back + 1) % array.length;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        judgeToRevise();
        T x = array[front];
        front = (front + 1) % array.length;
        size -= 1;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        judgeToRevise();
        back = (back - 1 + array.length) % array.length;
        T x = array[back];
        size -= 1;
        return x;
    }

    @Override
    public T get(int i) {
        int tail = array.length - front;
        if (i < 0 || i > size) {
            return null;
        } else if (i < array.length - front) {
            return array[i + front];
        } else {
            return array[i - tail];
        }
    }

    @Override
    public void printDeque() {
        int tail = array.length - front;
        for (int i = 0; i < size; i++) {
            if (tail > i) {
                System.out.print(array[front] + " ");
            } else {
                System.out.print(array[i - tail] + " ");
            }
        }
        System.out.println();
    }

    private void judgeToRevise() {
        if (size >= 16 && size < array.length * 0.25) {
            revise(0);
        } else if (size + 1 >= array.length) {
            // we need have the blank box to avoid front = back
            revise(1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CreateArIterator();
    }

    private class CreateArIterator implements Iterator<T> {
        private int currentIndex = front;
        private int remaining = size;

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = array[currentIndex];
            currentIndex = (currentIndex + 1) % array.length;
            remaining -= 1;
            return item;
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
