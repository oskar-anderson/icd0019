package collections.set;

import org.junit.Test;

import java.util.*;


public class Birthday {

    @Test
    public void runCode() {

        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            counts.add(findFirstCollision());
        }

        int sum = 0;
        for (Integer count : counts) {
            sum += count;
        }

        System.out.println(sum / counts.size());

    }

    private int findFirstCollision() {
        Random r = new Random();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 365; i++) {
            int randomDayOfYear = r.nextInt(365);

            if (set.contains(randomDayOfYear)) {
                return i;
            } else {
                set.add(randomDayOfYear);
            }
        }

        throw new IllegalStateException("Should not be here");
    }

}
