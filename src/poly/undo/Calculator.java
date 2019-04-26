package poly.undo;

import java.util.Stack;
import java.util.function.Function;

public class Calculator {

    private double value;
    private Stack<Function<Double, Double>> history = new Stack<>();

    public void input(double value) {
        history = new Stack<>();
        history.push(input -> 0.0);

        this.value = value;
    }

    public void add(double addend) {
        history.push(input -> value - addend);
        value += addend;
    }

    public void multiply(double multiplier) {
        history.push(input -> value / multiplier);
        value *= multiplier;
    }

    public double getResult() {
        return value;
    }

    public void undo() {
        Function<Double, Double> function = history.pop();

        value = function.apply(value);

    }
}
