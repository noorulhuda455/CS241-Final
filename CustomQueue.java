import java.util.LinkedList;

public class CustomQueue<T> {
    private LinkedList<T> queue;

    public CustomQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.addLast(item);
    }

    public T dequeue() {
        if (!isEmpty()) {
            return queue.removeFirst();
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return queue.getFirst();
        }
        return null;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
