package inheritance.stack;

import java.util.Stack;

public class ExtendedStack extends Stack<Integer> {

    @Override
    public Integer push(Integer item) {
        return super.push(item);
    }


    public void pushAll(Integer... numbers) {
        for (Integer number : numbers) {
            System.out.println("pushed element:" + number);

            push(number);
        }
    }


    @Override
    public synchronized Integer pop() {
        Integer popped = super.pop();

        System.out.println("popped element:" + popped);

        return popped;
    }

}