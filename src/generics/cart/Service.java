package generics.cart;

public class Service implements CartItem {
    private String serviceId;
    private Double salePrice;

    public Service(String id, Double price) {
        this.serviceId = id;
        this.salePrice = price;
    }

    @Override
    public String getId() {
        return serviceId;
    }

    @Override
    public Double getPrice() {
        return salePrice;
    }
}
