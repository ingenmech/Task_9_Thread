package by.epam.evm.thread.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CashDesk {

    private final static int FIRST_ELEMENT = 0;
    private final List<Order> orders = new ArrayList<>();

    public CashDesk() {
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order process() {
        return orders.remove(FIRST_ELEMENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashDesk point = (CashDesk) o;
        return Objects.equals(orders, point.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
