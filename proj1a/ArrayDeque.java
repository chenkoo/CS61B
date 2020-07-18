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

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        items[nextFirst] = item;
        nextFirst = items.length - 1;
        size = 1;
    }

    private void resize() {
        if (size == items.length){
            T[] itemsNew = (T[]) new Object[size * 2];
            System.arraycopy(itemsNew, 0, items, 0, size);
            items = itemsNew;
            nextFirst = items.length - 1;
            nextLast = size;
        } else {
            T[] itemsNew = (T[]) new Object[size/2];
            if (nextFirst == items.length - 1) {
                System.arraycopy(itemsNew, 0, items, 0, size);
                items = itemsNew;
                nextFirst = items.length - 1;
                nextLast = size;
            } else if (nextLast == 0 || nextFirst < nextLast) {
                System.arraycopy(itemsNew, 0, items, nextFirst + 1, size);
                items = itemsNew;
                nextFirst = items.length - 1;
                nextLast = size;
            } else {
                System.arraycopy(itemsNew, 0, items, 0, nextLast);
                System.arraycopy(itemsNew, nextFirst - size/2 + 1, items, nextFirst + 1,
                        size - nextLast);
                items = itemsNew;
                nextFirst -= size/2;
            }
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        if (size == items.length) {
            resize();
        } else {
            nextFirst -= 1;
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        if (size == items.length) {
            resize();
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
        if (! isEmpty()) {
            int i = 0;
            while (i < size - 1) {
                System.out.print(items[i] + " ");
                i++;
            }
            System.out.print(items[i]);
        }
    }

    public T removeFirst() {
        T firstitem = null;
        if (nextFirst + 1 == items.length){
            firstitem = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            firstitem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        if (items.length >= 16 && size/items.length < 0.25) {
            resize();
        }
        return firstitem;
    }

    public T removeLast() {
        T lastitem = null;
        if (nextLast == 0){
            lastitem = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            lastitem = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast -= 1;
        }
        size -= 1;
        if (items.length >= 16 && size/items.length < 0.25) {
            resize();
        }
        return lastitem;
    }

    public T get(int index) {
        return items[index];
    }
}
