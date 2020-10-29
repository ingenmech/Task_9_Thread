package by.epam.evm.thread.model;

import by.epam.evm.thread.data.ResourceException;
import by.epam.evm.thread.data.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Customer implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Customer.class);
    private final int id;
    private final boolean isPreorder;
    private final Restaurant restaurant;
    private Order order;

    public Customer(int id, boolean isPreorder, Restaurant restaurant) {
        this.id = id;
        this.isPreorder = isPreorder;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        CashDesk cashDesk = null;
        try {
            cashDesk = restaurant.getCashDesk();
            order = cashDesk.process();
        } catch (ResourceException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (cashDesk != null) {
                restaurant.returnCashDesk(cashDesk);
                System.out.println(String.format("Visitor %s leave restaurant", id));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id &&
                isPreorder == customer.isPreorder &&
                Objects.equals(restaurant, customer.restaurant) &&
                Objects.equals(order, customer.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isPreorder, restaurant, order);
    }
}
