package exceptions.numbers;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Tests {

    @Test(expected = MissingLanguageFileException.class)
    public void selectingMissingLanguageThrows() {
        new NumberConverter("ru");
    }

    @Test(expected = BrokenLanguageFileException.class)
    public void selectingBrokenLanguageFileThrows() {
        new NumberConverter("fr");
    }

    @Test(expected = MissingTranslationException.class)
    public void missingEssentialTranslationThrows() {
        new NumberConverter("es").numberInWords(1);
    }

    @Test
    public void canConvertNumbersUpTo20ToEnglish() {
        assertCanConvertNumbersUpTo(20, "en");
    }

    @Test
    public void canConvertNumbersUpTo20ToEstonian() {
        assertCanConvertNumbersUpTo(20, "et");
    }

    @Test // 4 points instead of 5 if this test fails
    public void canConvertNumbersUpTo130() {
        assertCanConvertNumbersUpTo(130, "en");
        assertCanConvertNumbersUpTo(130, "et");
    }

    private void assertCanConvertNumbersUpTo(int upperBound, String lang) {
        for (int i = 0; i <= upperBound; i++) {
            String numberInWords = new NumberConverter(lang).numberInWords(i);

            assertThat(numberInWords, is(getExpected(lang, i)));
        }
    }

    private String getExpected(String lang, int index) {
        List<String> strings = map.get(lang);

        if (strings == null) {
            throw new RuntimeException("unexpected language: " + lang);
        }

        if (index < 0 || index >= strings.size()) {
            throw new RuntimeException("no test data for number: " + index);
        }

        return strings.get(index);
    }

    private Map<String, List<String>> map = new HashMap<>();

    @Before
    public void setUp() throws IOException {
        String template = "src/exceptions/numbers/expected-%s.txt";

        map.put("en", Files.readAllLines(Paths.get(String.format(template, "en"))));
        map.put("et", Files.readAllLines(Paths.get(String.format(template, "et"))));
    }
}
