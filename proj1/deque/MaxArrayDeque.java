package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        T maxElement = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (c.compare(current, maxElement) > 0) {
                maxElement = current;
            }
        }
        return maxElement;
    }
}
