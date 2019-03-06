package exceptions.validate;

public class MinimumElement {

    public static Integer minimumElement(Integer[] integers) {
        if (integers == null || integers.length == 0) {
            throw new IllegalArgumentException();
        }

        Integer minimumElement = integers[0];

        for (Integer current : integers) {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

}
