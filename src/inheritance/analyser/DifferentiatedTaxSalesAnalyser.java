package inheritance.analyser;

public class DifferentiatedTaxSalesAnalyser extends AbstractTaxSales {

    public DifferentiatedTaxSalesAnalyser(SalesRecord[] records) {
        TaxSalesAnalyser(records);
    }

    @Override
    protected Double getSpecialTaxRate() {
        return 1.1;
    }
}