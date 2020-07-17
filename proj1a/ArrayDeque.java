public class ArrayDeque<T> {

    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        items[0] = item;
        size = 1;
    }

    private void resize(int capacity) {
        T[] itemsNew = (T[]) new Object[capacity];
        System.arraycopy(itemsNew, 0, items, 0, size);
        items = itemsNew;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size != 0) {
            int i = 0;
            while (i < size - 1) {
                System.out.print(items[i] + " ");
                i++;
            }
            System.out.print(items[i]);
        }
    }

    public T removeFirst() {
        T firstitem = items[0];
        T[] itemsNew = (T[]) new Object[size - 1];
        System.arraycopy(itemsNew, 0, items, 1, size - 1);
        items = itemsNew;
        size -= 1;
        return firstitem;
    }

    public T removeLast() {
        T lastitem = items[size - 1];
        size -= 1;
        while (items.length >= 16 && size/items.length < 0.25) {
            resize(items.length/2);
        }
        return lastitem;
    }

    public T get(int index) {
        return items[index];
    }
}
