package exceptions.price;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PriceCalculatorTest {

    @Test
    public void ifFileIsReadableThenPriceIsCalculated() {
        MyFileResource.setData(2); // simulates that file contains number 2

        Double price = new PriceCalculator().calculatePrice();

        assertThat(price.intValue(), is(144));
    }

    @Test
    public void ifFileIsNotReadableThenPriceIsNotCalculated() {
        MyFileResource.setData(-1); // simulates read error

        Double price = new PriceCalculator().calculatePrice();

        assertThat(price.intValue(), is(-1));
    }

    @Test
    public void ifFileIsNotReadableThenThrowException() {
        MyFileResource.makeItThrowOnRead(); // simulates exception on reading

        Double price = new PriceCalculator().calculatePrice();

        assertThat(price.intValue(), is(-1));
    }

}
