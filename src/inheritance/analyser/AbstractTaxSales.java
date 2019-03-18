package inheritance.analyser;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTaxSales {

    Double totalSales = 0.0;
    Map<String, Double> productIdToTotalSales = new HashMap<>();
    Map<String, Integer> productIdToTotalNumberSold = new HashMap<>();

    public void TaxSalesAnalyser(SalesRecord[] records) {
        for (SalesRecord record : records) {

            Double currentSale = (double) record.getItemsSold() * record.getProductPrice();

            if (record.hasReducedRate()) {
                currentSale = currentSale / getSpecialTaxRate();
            } else {
                currentSale = currentSale / getTaxRate();
            }
            totalSales += currentSale;

            String key = record.getProductId();
            if (productIdToTotalSales.containsKey(key)) {
                productIdToTotalSales.put(key, productIdToTotalSales.get(key) + currentSale);

                productIdToTotalNumberSold.put(key, productIdToTotalNumberSold.get(key) + record.getItemsSold());
            } else {
                productIdToTotalSales.put(key, currentSale);

                productIdToTotalNumberSold.put(key, record.getItemsSold());
            }

        }
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public Double getTotalSalesByProductId(String id) {
        return productIdToTotalSales.get(id);
    }


    public String getIdOfCombined(Map map) {        // tried to combine them, but couldn't
        Double mostPopularItem = 0.0;
        String idOfPopularItem = "";
        for (Object key : map.keySet()) {           // String turns to Object?
            System.out.println(map.get(key).getClass());  // Integer but also Object?
            if ((double) map.get(key) >= mostPopularItem) {
                mostPopularItem = (double) map.get(key);
                idOfPopularItem = (String) key;
            }
        }
        return idOfPopularItem;
    }


    public String getIdOfMostPopularItem() {
        Integer mostPopularItem = 0;
        String idOfPopularItem = "";
        for (String key : productIdToTotalNumberSold.keySet()) {
            if (productIdToTotalNumberSold.get(key) >= mostPopularItem) {
                mostPopularItem = productIdToTotalNumberSold.get(key);
                idOfPopularItem = key;
            }
        }
        return idOfPopularItem;
    }

    public String getIdOfItemWithLargestTotalSales() {
        Double mostPopularItem = 0.0;
        String idOfPopularItem = "";
        for (String key : productIdToTotalSales.keySet()) {
            if (productIdToTotalSales.get(key) >= mostPopularItem) {
                mostPopularItem = productIdToTotalSales.get(key);
                idOfPopularItem = key;
            }
        }
        return idOfPopularItem;
    }

    protected Double getTaxRate() {
        return 1.2;
    }

    protected Double getSpecialTaxRate(){  // meant to be overwritten
        return getTaxRate();
    }

}
