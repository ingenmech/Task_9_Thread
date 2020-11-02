package by.epam.evm.thread.model;

import by.epam.evm.thread.data.DataException;
import by.epam.evm.thread.data.Restaurant;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Customer.class);

    private final int id;
    private final String name;
    private final Restaurant restaurant;

    @JsonCreator
    public Customer(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JacksonInject("restaurant") Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        CashDesk cashDesk = null;
        try {
            cashDesk = restaurant.getCashDesk();
            cashDesk.pollOrder();
        } catch (DataException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (cashDesk != null) {
                restaurant.returnCashDesk(cashDesk);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        return restaurant != null ? restaurant.equals(customer.restaurant) : customer.restaurant == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}
