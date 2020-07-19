public class LinkedListDeque<T> implements Deque<T> {

    private class TNode {

        private T item;
        private TNode prev;
        private TNode next;

        public TNode(T t, TNode tprev, TNode tnext) {
            item = t;
            prev = tprev;
            next = tnext;
        }
    }

    private TNode sentinel;
    private int size;

    /**
     * Create an empty LLdeque.
     */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Add an item to the first position of LLdeque.
     * @param item item to be added.
     */
    @Override
    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Add an item at the end of LLdeque.
     * @param item item to be added.
     */
    @Override
    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * If an LLdeque is empty.
     * @return true if it is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the size of LLdeque.
     * @return the size of LLdeque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Print out the LLdeque.
     */
    @Override
    public void printDeque() {
        TNode temp = sentinel.next;
        if (sentinel.next != null) {
            int i = 0;
            while (i < size - 1) {
                System.out.print(temp.item + " ");
                temp = temp.next;
                i++;
            }
            System.out.print(temp.item);
        }
    }

    /**
     * Remove the first item in LLdeque.
     * @return the removed item.
     */
    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            T item = sentinel.next.item;
            sentinel.next.item = null;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }

    /**
     * Remove the last item in LLdeque.
     * @return the removed item.
     */
    @Override
    public T removeLast() {
        if (!isEmpty()) {
            T item = sentinel.prev.item;
            sentinel.prev.item = null;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }

    /**
     * Get the indexth item in LLdeque iteratively.
     * @param index the index of item to get.
     * @return the indexth item.
     */
    @Override
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

    /**
     * A helper function of getRecursive(int index).
     * @param index A helper function of getRecursive(int index).
     * @param t A helper function of getRecursive(int index).
     * @return A helper function of getRecursive(int index).
     */
    private T getRecursive(int index, TNode t) {
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return t.next.item;
        }
        return getRecursive(index - 1, t.next);
    }

    /**
     * Get the indexth item in LLdeque recursively.
     * @param index the index of item to get.
     * @return the indexth item.
     */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }
}
