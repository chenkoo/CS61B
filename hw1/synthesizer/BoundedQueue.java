package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    int capacity(); //return the size of buffer
    int fillCount();    //return number of items currently in the buffer
    void enqueue(T x);  //add item x to the end
    T dequeue();    //delete and return item from the front
    T peek();   //return (but not delete front the front)

    //return true if BoundedQueue is empty
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    //return true if BoundedQueue is full
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }

    Iterator<T> iterator();

}
