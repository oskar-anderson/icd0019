package generics.cart;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTests {

    @Test
    public void canGetCartTotal() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 10.0));
        cart.add(new Product("i2", 5.0));

        assertThat(cart.getTotal(), is(closeTo(15)));
    }

    @Test
    public void canStoreServicesToo() {

        ShoppingCart<Service> cart = new ShoppingCart<>();

        cart.add(new Service("s1", 3.0));
        cart.add(new Service("s2", 2.0));

        assertThat(cart.getTotal(), is(closeTo(5)));
    }

    @Test
    public void canRemoveItemsFromCart() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 10.0));

        cart.removeById("i1");

        assertThat(cart.getTotal(), is(closeTo(0)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void canAddMultipleItemsAtOnce() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.addAll(Arrays.asList(
                new Product("i1", 10.0),
                new Product("i2", 2.0)));

        assertThat(cart.getTotal(), is(closeTo(12)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void canIncreaseQuantityById() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 5.0));
        cart.add(new Product("i2", 10.0));

        cart.increaseQuantity("i2");

        assertThat(cart.getTotal(), is(closeTo(25)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void addingTheSameProductAgainIncreasesItsQuantity() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 5.0));
        cart.add(new Product("i1", 5.0));

        assertThat(cart.getTotal(), is(closeTo(10)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void canAddDiscountToWholeCart() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 10.0));

        cart.applyDiscountPercentage(10.0);

        assertThat(cart.getTotal(), is(closeTo(9)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void canAddMultipleDiscounts() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 10.0));

        cart.applyDiscountPercentage(10.0);

        cart.applyDiscountPercentage(10.0);

        assertThat(cart.getTotal(), is(closeTo(8.1)));
    }

    @Test // 4 points instead of 5 if this test fails
    public void canClearAllTheDiscounts() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("i1", 10.0));

        cart.applyDiscountPercentage(10.0);

        cart.applyDiscountPercentage(10.0);

        cart.cancelDiscounts();

        assertThat(cart.getTotal(), is(closeTo(10)));
    }



    private Matcher<Double> closeTo(double value) {
        double precision = 0.001;

        return Matchers.closeTo(value, precision);
    }
}
