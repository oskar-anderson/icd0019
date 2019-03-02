package oo.hide;

public class Fibonacci {

    private int current = 0;
    private int next = 1;

    public Integer nextValue() {
        // 0, 1, 1, 2, 3,

        int result = current;

        current = next;
        next = current + result;

        // fib(n) = fib(n - 1) + fi(n - 2);

        return result;
    }

}
