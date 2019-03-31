package generics.stack;

public class Stack<T> {

    private Object[] elements;
    private Integer size = 0;

    public Stack() {
        elements = new Object[100];
    }

    public void push(T element) {
        elements[size++] = element;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("stack is empty");
        }

        return (T) elements[--size];
    }

}
