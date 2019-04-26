package poly.customer;

public class BonusCollector {

    private CustomerRepository repository;

    public BonusCollector(CustomerRepository repository) {
        this.repository = repository;
    }

    public void gatherCustomerBonus(String customerId, Order order) {

        AbstractCustomer customer = repository.getCustomerById(customerId);

        customer.collectBonusPointsFrom(order);

        repository.save(customer);
    }


}
