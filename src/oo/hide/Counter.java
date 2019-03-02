package oo.hide;

public class Counter {

    private Integer start;
    private Integer step;

    public Counter(Integer start, Integer step) {
        this.start = start;
        this.step = step;
    }

    public Integer nextValue() {
        int result = start;

        start += step;

        return result;
    }
}
