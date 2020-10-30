package by.epam.evm.thread.model;

import java.util.*;

public class CashDesk {

    private final Deque<Order> orders = new ArrayDeque<>();

    public CashDesk() {
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order pollOrder() {
        return orders.poll();
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
