package by.epam.evm.thread.model;

import by.epam.evm.thread.data.ResourceException;
import by.epam.evm.thread.data.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Visitor implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Visitor.class);
    private final int id;
    private final boolean isPreorder;
    private final Restaurant restaurant;
    private Order order;

    public Visitor(int id, boolean isPreorder, Restaurant restaurant) {
        this.id = id;
        this.isPreorder = isPreorder;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        SalePoint salePoint = null;
        try {
            salePoint = restaurant.getSalePoint();
            order = salePoint.issueOrder();
        } catch (ResourceException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (salePoint != null) {
                restaurant.returnSalePoint(salePoint);
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
        Visitor visitor = (Visitor) o;
        return id == visitor.id &&
                isPreorder == visitor.isPreorder &&
                Objects.equals(restaurant, visitor.restaurant) &&
                Objects.equals(order, visitor.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isPreorder, restaurant, order);
    }
}
