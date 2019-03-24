package collections.cache;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private Map<Integer, Integer> map = new HashMap<>();

    public Integer fib(Integer n) {
        if (map.containsKey(n))
            return map.get(n);

        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int fibN = fib(n - 1) + fib(n - 2);

        map.put(n, fibN);

        return fibN;
    }

}
