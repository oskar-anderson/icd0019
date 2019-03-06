package exceptions.price;

public class PriceCalculator {

    public Double calculatePrice() {

        Double basePrice = null;
        try {
            basePrice = calculateBasePrice();
        } catch (MissingConstantException e) {
            return -1d;
        }

        return basePrice * (1 + getVat());
    }

    private Double getVat() {
        return 0.2;
    }

    private Double calculateBasePrice() {
        // some complex calculation that produces 100 as net cost
        Double netCost = 100D;

        Integer profitConstant = readProfitConstant();

        return netCost + (0.1 * profitConstant * netCost);
    }

    private Integer readProfitConstant() {
        // read constant from file
        // return -1 if some error happens

        // this simulates described behavior

        int result = 0;
        try {
            result = MyFileResource.read();
        } catch (Exception e) {
            throw new MissingConstantException();
        }

        if (result == -1) {
            throw new MissingConstantException();
        }

        return result;
    }

}
