package by.epam.evm.thread.model;

import java.util.ArrayDeque;
import java.util.Deque;

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

}
