public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize() {
        T[] itemsNew;
        if (size == items.length) {
            itemsNew = (T[]) new Object[size * 2];
        } else {
            itemsNew = (T[]) new Object[items.length / 2];
        }
        int s = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            itemsNew[i] = items[s];
            s = (nextFirst + i + 2) % items.length;
        }
        items = itemsNew;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        items[nextLast] = item;
        size += 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (!isEmpty()) {
            int i = 0;
            while (i < items.length - 1) {
                System.out.print(items[i] + " ");
                i++;
            }
            System.out.print(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstitem = null;
        if (nextFirst == items.length - 1) {
            firstitem = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            firstitem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        if (items.length >= 16 && size < (items.length * 0.25)) {
            resize();
        }
        return firstitem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastitem = null;
        if (nextLast == 0) {
            lastitem = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            lastitem = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast -= 1;
        }
        size -= 1;
        if (items.length >= 16 && size < (items.length * 0.25)) {
            resize();
        }
        return lastitem;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            return items[(index + nextFirst + 1) % items.length];
        }
    }
}
