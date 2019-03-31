package inheritance.stack;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackTest {

    @Test
    public void extendedStackLogsMethodCalls() {
        ExtendedStack stack = new ExtendedStack();

        stack.push(1);
        stack.push(2);

        stack.pop();
    }


    @Test
    public void canAddMultipleElementsAtOnce() {
        ExtendedStack stack = new ExtendedStack();

        stack.pushAll(1, 2, 3);

        assertThat(stack.size(), is(3));
        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(2));
    }



}
