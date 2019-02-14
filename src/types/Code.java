package types;

public class Code {

    public static void main(String[] args) {

        Integer[] numbers = new Integer[] {1, 3, -2, 9};

        // numbers = new Integer[] {};

        System.out.println(sum(numbers)); // 11
        System.out.println(average(numbers)); // 2.75
        System.out.println(minimumElement(numbers));
        System.out.println(asString(numbers));

        System.out.println(squareDigits("2"));
        System.out.println(squareDigits("a2b"));
        System.out.println(squareDigits("a22b"));
        System.out.println(squareDigits("a81b4"));
    }

    public static Integer sum(Integer[] numbers) {
        Integer sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static Double average(Integer[] numbers) {
        Double sum = 0.0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    public static Integer minimumElement(Integer[] integers) {
        if (integers.length == 0) {
            return null;
        }

        Integer min = integers[0];
        for (Integer each : integers) {
            if (each < min) {
                min = each;
            }
        }
        return min;
    }

    public static String asString(Integer[] elements) {
        String number_string = "";
        int index = 0;
        for (Integer element : elements) {
            if (index != elements.length - 1) {
                number_string += element + ", ";
            }    else {
                number_string += element;
            }
            index += 1;
        }
        return number_string;
    }

    public static String squareDigits(String s) {
        System.out.println();

        String result = "";
        int index = 0;
        while (index != s.length()) {
            System.out.println(s.charAt(index));
            if (Character.isDigit(s.charAt(index))) {
                int number = Character.getNumericValue(s.charAt(index));
                result += Math.round(Math.pow(number, 2));
            } else {
                result += s.charAt(index);
            }
            index += 1;
        }
        System.out.println("result: " + result);
        return result;
    }

}
