package inheritance.calc;

public class TaxFreePayCalculator extends PayCalculator {

    @Override
    protected Double getTaxRate() {
        return 0.0;
    }
}
