package exceptions.numbers;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class ReadPropertiesFileExample {

    public static void main(String[] args) {

        String lang = "en";

        String filePath = String.format(
                "src/exceptions/numbers/numbers_%s.properties", lang);

        Properties properties = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);

            InputStreamReader reader = new InputStreamReader(
                    is, Charset.forName("UTF-8"));

            properties.load(reader);
        } catch (Exception e) {
            // handle exceptions
        } finally {
            // close input stream
        }

        System.out.println(properties.containsKey(String.valueOf(1)));
        System.out.println(properties.getProperty(String.valueOf(1)));

    }

}
