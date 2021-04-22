import java.util.Iterator;

/**
 * CallStack creates a stack that contains generic type elements.
 * @param <T> Generic type that CallStack takes.
 */
class CallStack<T> implements Iterable<T> {
    // You'll want some instance variables here
    /**
     * This variable holds the top of the stack.
     * @variable Top of the stack.
     */
    private Node<T> top;
    /**
     * This tracks the size of the stack.
     * @variable Holds size of the stack.
     */
    private int size = 0;

    /**
     * Constructor for callStack.
     */

    public CallStack() {
        //setup what you need
        this.top = null;
    }

    /**
     * Pushes an item onto the stack.
     * @param item Element that is being put on the stack.
     */

    public void push(T item) {
        //push an item onto the stack
        //you may assume the item is not null
        //O(1)
        Node<T> temp = new Node<T>(item);
        if (top == null) {
            top = temp;
            size++;
            return;
        }
        top.setPrev(temp);
        temp.setNext(top);
        top = top.getPrev();
        size++;
    }

    /**
     * Returns the top element of the stack and removes it from the stack.
     * @return Top element of the stack.
     */

    public T pop() {
        //pop an item off the stack
        //if there are no items on the stack, return null
        //O(1)
        //replace this dummy return statement
        T value;
        if (top == null) {
            return null;
        }
        if (size == 1) {
            value = top.getValue();
            top = null;
        } else {
            value = top.getValue();
            top = top.getNext();//Deletes the first node
            top.setPrev(null);
        }
        size--;
        return value;
    }

    /**
     * Returns the first element on the stack.
     * @return Top element of the stack.
     */

    public T peek() {
        //return the top of the stack (but don't remove it)
        //if there are no items on the stack, return null
        //O(1)
        //replace this dummy return statement
        if (!isEmpty()) {
            return top.getValue();
        }
        size = 0;
        return null;
    }

    /**
     * Creates a string representation of the entire stack.
     * @return Returns a string representation of the stack.
     */

    public String toString() {
        //Create a string of the stack where each item
        //is separated by a space. The top of the stack
        //should be shown to the right and the bottom of
        //the stack on the left.

        //Hint: Reuse the provided code from another class
        //instead of writing this yourself!
        String x = "";
        if (top != null) {
            x = Node.listToStringBackward(top);
        }
        return x;

    }

    /**
     * Deletes the entire stack and sets the size back to 0.
     */

    public void clear() {
        //remove everything from the stack
        //O(1)
        top = null;
        size = 0;
    }

    /**
     * Shows the current size of the stack.
     * @return Current size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Checks to see if the stack is empty.
     * @return True if the stack is empty, otherwise it will return false.
     */
    public boolean isEmpty() {
        //return whether or not the stack is empty
        //O(1)
        //replace this dummy return statement
        return top == null;
    }

    /**
     * Returns an array representation of the stack.
     * @return Array representation of the stack.
     */
    @SuppressWarnings("unchecked")

    public Object[] toArray() {
        //Return an array representation of the stack.
        //The top of the stack should be element 0
        //in the array.
        //O(n)
        //replace this dummy return statement
        T[] array;
        Node<T> temp = top;
        if (top == null || size == 0) {
            array = (T[]) new Object[0];
            return array;
        } else {
            array = (T[]) new Object[size];
        }
        for (int i = 0; i < size; i++) {
            array[i] = temp.getValue();
            temp = temp.getNext();
        }
        return array;
    }

    /**
     * Returns a iterator object.
     * @return Iterator object.
     */
    public Iterator<T> iterator() {
        //Return an iterator that traverses from
        //the top of the stack to the bottom of
        //the stack.

        //The iterator's hasNext() and next() methods
        //must both be O(1)

        //next() should throw a NullPointerException
        //if you try to use next when there are no
        //more items

        //replace this dummy return statement

        Iterator<T> iter = new Iterator<T>() {
            Node<T> newNode = top;

            @Override
            public boolean hasNext() {
                return newNode != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NullPointerException();

                T element = newNode.getValue();
                newNode = newNode.getNext();
                return element;
            }
        };
        return iter;

    }
}