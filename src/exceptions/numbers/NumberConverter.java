package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class NumberConverter {

    private Properties properties = new Properties();

    public NumberConverter(String lang) {

        String filePath = String.format(
                "src/exceptions/numbers/numbers_%s.properties", lang);

        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(
                    is, Charset.forName("UTF-8"));

            properties.load(reader);

        } catch (Exception e) {
            if (is == null) {
                throw new MissingLanguageFileException();
            } else {
                throw new BrokenLanguageFileException();
            }

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public String numberInWords(Integer number) {

        int hundreds = number / 100;
        int tens = (number - hundreds * 100) / 10;
        int ones = number - hundreds * 100 - tens * 10;

        StringBuilder result = new StringBuilder();
        String sep = "";
        boolean numberIsOneIrregular = false;

        if  (!properties.containsKey(String.valueOf(ones)) ||       // check if any of the required numbers exist.
                !properties.containsKey(String.valueOf(tens)) ||
                !properties.containsKey(String.valueOf(hundreds))) {
            throw new MissingTranslationException();
        }

        if (properties.containsKey(String.valueOf(number))) {       // zero to ten + everything straight from lng file
            return properties.getProperty(String.valueOf(number));
        }

        if (hundreds != 0) {    // hundreds
                result.append(sep)
                        .append(properties.getProperty(String.valueOf(hundreds)))
                        .append(properties.getProperty("hundreds-before-delimiter"))
                        .append(properties.getProperty("hundred"));

            sep = " ";
        }

        if (tens != 0) {

            if (properties.containsKey(String.valueOf(tens * 10 + ones))) {     // irregular 10 - 19
                result.append(sep)
                        .append(properties.getProperty(String.valueOf(tens * 10 + ones)));
                numberIsOneIrregular = true;
            } else if (tens == 1 && ones != 0) {         // regular 11 - 19
                result.append(sep)
                        .append(properties.getProperty(String.valueOf(ones)))
                        .append(properties.getProperty("teen"));
                numberIsOneIrregular = true;

            } else if (properties.containsKey(String.valueOf(tens * 10))){      // irregular tens
                result.append(sep)
                        .append(properties.getProperty(String.valueOf(tens * 10)));
            } else {                                     // regular tens
                result.append(sep)
                        .append(properties.getProperty(String.valueOf(tens)))
                        .append(properties.getProperty("tens-suffix"));

            } if (ones != 0 && !numberIsOneIrregular) {       // last digit for tens (sixty-six)
                result.append(properties.getProperty("tens-after-delimiter"))
                        .append(properties.getProperty(String.valueOf(ones)));
            }

        }

        if (ones != 0 && tens == 0) {      // last digit 1 - 9 when there are hundreds
            result.append(sep)
                    .append(properties.getProperty(String.valueOf(ones)));
        }

        return result.toString();
    }

}