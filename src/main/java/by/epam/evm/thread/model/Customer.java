package by.epam.evm.thread.model;

import by.epam.evm.thread.data.ResourceException;
import by.epam.evm.thread.data.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Customer.class);

    private final int id;
    private final String name;
    private final Restaurant restaurant;
    private Order order;

    public Customer(int id, String name, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        CashDesk cashDesk = null;
        try {
            cashDesk = restaurant.getCashDesk();
            order = cashDesk.pollOrder();
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
        if (id != customer.id) {
            return false;
        }
        if (name != null ? !name.equals(customer.name) : customer.name != null) {
            return false;
        }
        if (restaurant != null ? !restaurant.equals(customer.restaurant) : customer.restaurant != null) {
            return false;
        }
        return order != null ? order.equals(customer.order) : customer.order == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
