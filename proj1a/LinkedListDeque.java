public class LinkedListDeque<T> {

    private class TNode {

        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T t, TNode tprev, TNode next) {
            item = t;
            prev = tprev;
            next = next;
        }
    }

    private TNode sentinel;
    private int size;

    /* Create an empty linked list deque.*/
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /*Create deque.*/
    public LinkedListDeque(T x) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new TNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        } else {
            TNode temp = new TNode(item, sentinel.prev, sentinel);
            sentinel.prev.next = temp;
            sentinel.prev = temp;
            size += 1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode temp = sentinel.next;
        if (sentinel.next != null) {
            int i = 0;
            while (i < size - 1) {
                System.out.print(temp.item + " ");
                temp = sentinel.next.next;
                i++;
            }
            System.out.print(temp.item);
        }
    }

    public T removeFirst() {
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    public T removeLast() {
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            TNode temp = sentinel.next;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.item;
        }
    }

    public T getRecursive(int index, TNode t) {
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return t.next.item;
        }
        return getRecursive(index -1, t.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }
}
