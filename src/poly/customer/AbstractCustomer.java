package poly.customer;

public abstract class AbstractCustomer {

    protected int bonusPoints;

    private String name;
    private String id;

    public AbstractCustomer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void collectBonusPointsFrom(Order order);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    @Override
    public String toString() {
        return String.format("Customer(%s, %s, %s)", id, name, bonusPoints);
    }

}