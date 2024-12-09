import java.util.LinkedList;

public class CustomStack<T> {
    private LinkedList<T> stack;

    public CustomStack() {
        stack = new LinkedList<>();
    }

    public void push(T item) {
        stack.addFirst(item);
    }

    public T pop() {
        if (!isEmpty()) {
            return stack.removeFirst();
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return stack.getFirst();
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
