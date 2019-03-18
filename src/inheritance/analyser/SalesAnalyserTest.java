package inheritance.analyser;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SalesAnalyserTest {

    SalesRecord[] records = {
            new SalesRecord("i1", 1, 12),
            new SalesRecord("i2", 2, 24),
            new SalesRecord("i1", 1, 6),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i5", 12, 5),
            new SalesRecord("i4", 24, 2, true)
    };

    @Test
    public void calculatesTotalSalesWithFlatTaxRate() {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getTotalSales(), is(closeTo(295))); // sum all up
    }

    @Test
    public void calculatesTotalSalesByProductIdWithFlatTaxRate() {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getTotalSalesByProductId("i1"), is(closeTo(15)));
        assertThat(analyser.getTotalSalesByProductId("i2"), is(closeTo(40)));
    }

    @Test
    public void calculatesTotalSalesWithTaxFreeRate() {
        TaxFreeSalesAnalyser analyser = new TaxFreeSalesAnalyser(records);

        assertThat(analyser.getTotalSales(), is(closeTo(354)));
    }

    @Test
    public void calculatesTotalSalesByProductIdWithTaxFreeRate() {
        TaxFreeSalesAnalyser analyser = new TaxFreeSalesAnalyser(records);

        assertThat(analyser.getTotalSalesByProductId("i1"), is(closeTo(18)));
    }

    @Test
    public void calculatesTotalSalesWithDifferentiatedTaxRate() {
        DifferentiatedTaxSalesAnalyser analyser = new DifferentiatedTaxSalesAnalyser(records); // 354 / 1.2

        assertThat(analyser.getTotalSales(), is(closeTo(298.6)));  // 295 + 3.6  3.6 == 48 / 1.1 - 48 / 1.2
    }  // 255 + 43.6

    @Test
    public void allAnalysersHaveCommonAbstractSuperclass() {
        Class<?> s1 = DifferentiatedTaxSalesAnalyser.class.getSuperclass();
        Class<?> s2 = TaxFreeSalesAnalyser.class.getSuperclass();
        Class<?> s3 = FlatTaxSalesAnalyser.class.getSuperclass();

        assertThat(s1.getName(), is(s2.getName()));
        assertThat(s1.getName(), is(s3.getName()));

        assertTrue("Superclass should be abstract",
                Modifier.isAbstract(s1.getModifiers()));
    }

    @Test // -1 point if this test fails
    public void extraFunctionalityWorks() {
        FlatTaxSalesAnalyser analyser = new FlatTaxSalesAnalyser(records);

        assertThat(analyser.getIdOfMostPopularItem(), is("i2"));
        assertThat(analyser.getIdOfItemWithLargestTotalSales(), is("i5"));
    }

    private Matcher<Double> closeTo(double value) {
        double precision = 0.1;

        return Matchers.closeTo(value, precision);
    }

}
