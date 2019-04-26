package poly.fruit;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FruitTest {

    @Test
    public void computesTotalWeight() {

        List<? extends Weighable> fruits =
                Arrays.asList(new Apple(0.1), new Orange(120));

        assertThat(getTotalWeightInGrams(fruits), is(220));
    }

    private int getTotalWeightInGrams(List<? extends Weighable> list) {

        int sum = 0;

        for (Weighable each : list) {
            sum += each.getWeightInGrams();
        }

        return sum;
    }

}
