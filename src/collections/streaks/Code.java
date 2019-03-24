package collections.streaks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code {

    public static List<List<String>> getStreakList(String input) {

        LinkedList<List<String>> streak = new LinkedList<>();

        for (char character : input.toCharArray()) {

            String currentSymbol = String.valueOf(character);

            if (streak.size() == 0) {
                streak.add(new LinkedList<>(Arrays.asList(currentSymbol)));
            } else if (streak.get(streak.size() - 1).contains(currentSymbol)) {
                streak.getLast().add(currentSymbol);
            }
            else {
               streak.add(new LinkedList<>(Arrays.asList(currentSymbol)));
            }
        }

        return streak;
    }


}
