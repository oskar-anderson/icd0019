package poly.customer;

import java.time.LocalDate;

public class GoldCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public GoldCustomer(String id, String name, Integer bonusPoints, LocalDate date) {
        super(id, name);
        this.bonusPoints = bonusPoints;
        this.lastOrderDate = date;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100.0) {
            if (lastOrderDate != null) {
                LocalDate currentOrderDate = order.getDate();

                // System.out.println("Order date: " + currentOrderDate);
                // System.out.println("Customer last order: " + lastOrderDate);

                if (lastOrderDate.isBefore(currentOrderDate) && lastOrderDate.plusMonths(1).isAfter(currentOrderDate)) {
                    bonusPoints += order.getTotal() * 1.5;
                } else {
                    bonusPoints += order.getTotal();
                }

            } else {
                bonusPoints += order.getTotal() * 1.5;
            }
            lastOrderDate = order.getDate();
        }
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", "GOLD", getId(), getName(), bonusPoints, lastOrderDate);   // change AbstractCustomer id and name access to default?
    }

}
