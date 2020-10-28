package by.epam.evm.thread.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalePoint {

    private final static int FIRST_ELEMENT = 0;
    private final int id;
    private final List<Order> orders = new ArrayList<>();

    public SalePoint(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order issueOrder() {
        return orders.remove(FIRST_ELEMENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalePoint point = (SalePoint) o;
        return id == point.id &&
                Objects.equals(orders, point.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orders);
    }
}
