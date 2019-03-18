package inheritance.analyser;

public class TaxFreeSalesAnalyser extends AbstractTaxSales {

    public TaxFreeSalesAnalyser(SalesRecord[] records) {
        TaxSalesAnalyser(records);
    }

    @Override
    protected Double getTaxRate() {
        return 1.0;
    }
}
