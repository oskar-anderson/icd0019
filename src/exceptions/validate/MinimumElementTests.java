package exceptions.validate;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MinimumElementTests {

    @Test
    public void findsMinimumFromArrayOfNumbers() {

        assertThat(MinimumElement.minimumElement(new Integer[] { 1, 3 }), is(1));

        assertThat(MinimumElement.minimumElement(new Integer[] { 1, 0 }), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void handlesEmptyArray() {

        MinimumElement.minimumElement(new Integer[] { });

    }

    @Test(expected = IllegalArgumentException.class)
    public void handlesNullInput() {

        MinimumElement.minimumElement(null);

    }

}
