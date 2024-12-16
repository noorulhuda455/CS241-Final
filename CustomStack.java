import java.util.LinkedList;

// A generic implementation of a stack using a LinkedList
public class CustomStack<T> {
    // Underlying data structure to store stack elements
    private LinkedList<T> stack;

    // Constructor to initialize the stack
    public CustomStack() {
        stack = new LinkedList<>();
    }

    /**
     * Pushes an item onto the stack.
     * @param item The item to be added to the stack.
     */
    public void push(T item) {
        stack.addFirst(item); // Adds the item to the beginning of the LinkedList
    }

    /**
     * Removes and returns the top item of the stack.
     * @return The top item of the stack, or null if the stack is empty.
     */
    public T pop() {
        if (!isEmpty()) {
            return stack.removeFirst(); // Removes and returns the first element in the LinkedList
        }
        return null; // Returns null if the stack is empty
    }

    /**
     * Returns the top item of the stack without removing it.
     * @return The top item of the stack, or null if the stack is empty.
     */
    public T peek() {
        if (!isEmpty()) {
            return stack.getFirst(); // Retrieves the first element without removing it
        }
        return null; // Returns null if the stack is empty
    }

    /**
     * Checks if the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack.isEmpty(); // Checks if the LinkedList has no elements
    }

    /**
     * Returns the number of items in the stack.
     * @return The size of the stack.
     */
    public int size() {
        return stack.size(); // Returns the number of elements in the LinkedList
    }
}

