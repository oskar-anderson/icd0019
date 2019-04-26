package poly.customer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private CustomerRepository repository = new CustomerRepository();
    private BonusCollector collector = new BonusCollector(repository);

    @Test
    public void fileReading() {
        try {
            Path path = Paths.get("src/poly/customer/data.txt");
            List<String> lines = Files.readAllLines(path);

            ArrayList<String[]> parts = new ArrayList<>();
            for (String line : lines) {
                // System.out.println(line.split(";"));
                parts.add(line.split(";"));
            }

            for (String[] part : parts) {
                // System.out.println(Arrays.toString(part));
            }

            // System.out.println(parts);
            // System.out.println(parts.get(0).getClass());
            // System.out.println(parts.get(0)[0].getClass());

            assertEquals(Arrays.toString(parts.get(0)), "[REGULAR, c1, Alice, 0, 2019-03-10]");
            assertEquals(Arrays.toString(parts.get(1)), "[REGULAR, c2, Bob, 0, 2019-01-04]");
            assertEquals(Arrays.toString(parts.get(2)), "[GOLD, c3, Carol, 0]");

/*  proper input data:
REGULAR;c1;Alice;0;2019-03-10
REGULAR;c2;Bob;0;2019-01-04
GOLD;c3;Carol;0;
*/


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void noBonusForSmallOrders() {

        Integer pointsBefore = getPointsFor("c1");

        collector.gatherCustomerBonus("c1", new Order(10, currentDate()));

        assertThat(getPointsFor("c1"), is(pointsBefore));
    }

    @Test
    public void goldCustomerGetsPointsForOrdersOver100() {
        Integer pointsBefore = getPointsFor("c3");

        collector.gatherCustomerBonus("c3", new Order(200, currentDate()));

        assertThat(getPointsFor("c3"), is(pointsBefore + 300));
    }

    @Test
    public void regularCustomerGetsPointsForOrdersOver100() {
        Integer pointsBefore = getPointsFor("c2");

        collector.gatherCustomerBonus("c2", new Order(200, currentDate()));

        assertThat(getPointsFor("c2"), is(pointsBefore + 200));
    }

    @Test
    public void regularCustomerGetsMorePointsForFrequentOrders() {
        Integer pointsBefore = getPointsFor("c1");

        collector.gatherCustomerBonus("c1", new Order(200, currentDate()));

        assertThat(getPointsFor("c1"), is(pointsBefore + 300));
    }

    private Integer getPointsFor(String c1) {
        // System.out.println(repository.getCustomerById(c1));
        return repository.getCustomerById(c1).getBonusPoints();
    }

    private LocalDate currentDate() {
        return LocalDate.parse("2019-03-28");
    }

}
