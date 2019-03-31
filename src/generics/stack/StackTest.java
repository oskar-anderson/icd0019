package generics.stack;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackTest {

    @Test
    public void elementsArePoppedInReverseOrder() {
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");

        String first = stack.pop();
        String second = stack.pop();

        assertThat(first, is("2"));
        assertThat(second, is("1"));
    }

}
