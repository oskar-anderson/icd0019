package junit;

import java.util.Arrays;

public class Code {

    public static boolean isSpecial(Integer number) {
        int remainder = number % 11;

        return remainder == 0 || remainder == 1;
    }

    public static Integer longestStreak(String input) {
        if (input.length() == 0) {
            return 0;
        }

        int currentStreak = 0;
        int longestStreak = 0;

        String[] characters = input.split("");

        String lastSymbol = characters[0];

        for (String currentSymbol : characters) {

            if (lastSymbol.equals(currentSymbol)) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            if (currentStreak > longestStreak) {
                longestStreak = currentStreak;
            }

            lastSymbol = currentSymbol;
        }
        return longestStreak;
    }

    public static boolean integerInArray(Integer[] input, Integer i) {
        boolean duplicate = false;

        for (Integer integerBeingChecked : input) {
            if (integerBeingChecked != null && integerBeingChecked.equals(i)) {
                duplicate = true;
                break;
            }
        }
        System.out.println("Is " + i + " in " + Arrays.toString(input) + ": " + duplicate);
        return duplicate;
    }

    public static Integer[] removeDuplicates(Integer[] input) {
        Integer[] uniqueArrayWithNulls = new Integer[input.length];
        int newArrayLength = 0;

        for (int i = 0; i < input.length; i++) {

            if (! integerInArray(uniqueArrayWithNulls, input[i])) {
                uniqueArrayWithNulls[i] = input[i];
                newArrayLength++;
            }
        }

        Integer[] uniqueArrayWithoutNulls = new Integer[newArrayLength];

        int index = 0;
        for (Integer element : uniqueArrayWithNulls) {
            if (element != null) {
                uniqueArrayWithoutNulls[index] = element;
                index++;
            }
        }
        // System.out.println(Arrays.toString(uniqueArrayWithoutNulls));
        return uniqueArrayWithoutNulls;
    }

    public static Integer sumIgnoringDuplicates(Integer[] integers) {
        Integer[] uniqueIntegers = removeDuplicates(integers);
        int sum = 0;
        for (Integer i : uniqueIntegers) {
            sum += i;
        }
        return sum;
    }

}