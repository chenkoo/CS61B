public class ArrayDeque<T> {

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
        if (size == items.length - 1) {
            T[] itemsNew = (T[]) new Object[(size + 1) * 2];
            if (nextFirst == items.length - 1) {
                System.arraycopy(items, 0, itemsNew, 0, size);
                items = itemsNew;
                nextFirst = items.length - 1;
            } else if (nextFirst == 0) {
                System.arraycopy(items, 1, itemsNew, size + 2, size);
                items = itemsNew;
                nextFirst = size + 1;
            } else {
                System.arraycopy(items, 0, itemsNew, 0, nextLast);
                System.arraycopy(items, nextLast + 1, itemsNew,
                        size + nextLast + 2, size - nextLast);
                items = itemsNew;
                nextFirst = size + nextLast + 1;
            }
        } else {
            T[] itemsNew = (T[]) new Object[items.length / 2];
            if (nextFirst == items.length - 1) {
                System.arraycopy(items, 0, itemsNew, 0, size);
                items = itemsNew;
                nextFirst = items.length - 1;
            } else if (nextLast == 0) {
                System.arraycopy(items, nextFirst + 1, itemsNew,
                        nextFirst - items.length / 2 + 1, size);
                items = itemsNew;
                nextFirst -= items.length / 2;
            } else if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, itemsNew, 0, size);
                items = itemsNew;
                nextFirst = items.length - 1;
                nextLast = size + 1;
            } else {
                System.arraycopy(items, 0, itemsNew, 0, nextLast);
                System.arraycopy(items, nextFirst + 1, itemsNew,
                        nextFirst - items.length / 2 + 1, size - nextLast);
                items = itemsNew;
                nextFirst -= items.length / 2;
            }
        }
    }

    public void addFirst(T item) {
        if (size == items.length - 1) {
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

    public void addLast(T item) {
        if (size == items.length - 1) {
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
        if (!isEmpty()) {
            int i = 0;
            while (i < items.length - 1) {
                System.out.print(items[i] + " ");
                i++;
            }
            System.out.print(items[i]);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstitem = null;
        if (nextFirst + 1 == items.length) {
            firstitem = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            firstitem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        if (items.length >= 16 && size / items.length < 0.25) {
            resize();
        }
        return firstitem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastitem = null;
        if (size == 1) {
            return removeFirst();
        } else {
            if (nextLast == 0) {
                lastitem = items[items.length - 1];
                items[items.length - 1] = null;
                nextLast = items.length - 1;
            } else {
                lastitem = items[nextLast - 1];
                items[nextLast - 1] = null;
                nextLast -= 1;
            }
        }
        size -= 1;
        if (items.length >= 16 && size / items.length < 0.25) {
            resize();
        }
        return lastitem;
    }

    public T get(int index) {
        return items[index];
    }

    /**public static void main(String[] args) {
        ArrayDeque<Integer> d = new ArrayDeque<Integer>();
        d.addLast(0);
        d.removeFirst();
        d.addFirst(2);
        d.addFirst(3);
        d.get(1);
        d.removeLast();
        d.removeLast();
    }*/
}
