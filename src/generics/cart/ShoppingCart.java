package generics.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T extends CartItem> {

    private Double totalDiscount = 0.0;
    private ArrayList<T> cart = new ArrayList<>();

    public void add(T item) {
        cart.add(item);
    }

    public void removeById(String id) {
        ArrayList<T> toRemove = new ArrayList<>();
        for (T item : cart) {
            if (item.getId().equals(id)) {
                toRemove.add(item);
            }
        }
        cart.removeAll(toRemove);
    }

    public Double getTotal() {
        Double total = 0.0;
        for (T item : cart) {
            total += (item.getPrice() * (1 - totalDiscount / 100));
        }
        return total;
    }

    public void increaseQuantity(String id) {
        ArrayList<T> toAdd = new ArrayList<>();
        for (T item : cart) {
            if (item.getId().equals(id)) {
                toAdd.add(item);
            }
        }
        cart.addAll(toAdd);
    }

    public void applyDiscountPercentage(Double discount) {
        if (totalDiscount.equals(0.0)) {
            totalDiscount = discount;
        } else {
        totalDiscount += (100.0 - totalDiscount) / 100 * discount;
        }
        System.out.println(totalDiscount);
    }

    public void cancelDiscounts() {
        totalDiscount = 0.0;
    }

    public void addAll(List items) {
        cart.addAll(items);
    }
}
