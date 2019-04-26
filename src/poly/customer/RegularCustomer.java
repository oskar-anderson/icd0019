package poly.customer;

import java.time.LocalDate;

public class RegularCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public RegularCustomer(String id, String name, Integer bonusPoints, LocalDate date) {
        super(id, name);
        this.bonusPoints = bonusPoints;
        this.lastOrderDate = date;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100.0) {
            if (lastOrderDate != null) {
                LocalDate currentOrderDate = order.getDate();

                // System.out.println("Customer " + getName() + "'s last order: " + lastOrderDate);
                // System.out.println("Order date: " + currentOrderDate);

                if (lastOrderDate.isBefore(currentOrderDate) && lastOrderDate.plusMonths(1).isAfter(currentOrderDate)) {
                    bonusPoints += order.getTotal() * 1.5;
                } else {
                    bonusPoints += order.getTotal();
                }


            } else {
                bonusPoints += order.getTotal();
            }
            lastOrderDate = order.getDate();
        }
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", "REGULAR", getId(), getName(), bonusPoints, lastOrderDate);  // change AbstractCustomer id and name access to default?
    }

}
