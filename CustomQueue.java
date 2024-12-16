import java.util.LinkedList;

// A generic implementation of a queue using a LinkedList
public class CustomQueue<T> {
    // Underlying data structure to store queue elements
    private LinkedList<T> queue;

    // Constructor to initialize the queue
    public CustomQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Adds an item to the end of the queue (enqueue operation).
     * @param item The item to be added to the queue.
     */
    public void enqueue(T item) {
        queue.addLast(item); // Adds the item to the end of the LinkedList
    }

    /**
     * Removes and returns the front item of the queue (dequeue operation).
     * @return The front item of the queue, or null if the queue is empty.
     */
    public T dequeue() {
        if (!isEmpty()) {
            return queue.removeFirst(); // Removes and returns the first element in the LinkedList
        }
        return null; // Returns null if the queue is empty
    }

    /**
     * Returns the front item of the queue without removing it.
     * @return The front item of the queue, or null if the queue is empty.
     */
    public T peek() {
        if (!isEmpty()) {
            return queue.getFirst(); // Retrieves the first element without removing it
        }
        return null; // Returns null if the queue is empty
    }

    /**
     * Checks if the queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty(); // Checks if the LinkedList has no elements
    }

    /**
     * Returns the number of items in the queue.
     * @return The size of the queue.
     */
    public int size() {
        return queue.size(); // Returns the number of elements in the LinkedList
    }
}
