package by.epam.evm.thread.model;

import java.util.ArrayList;
import java.util.List;

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

    public Order sell() {
        return orders.remove(FIRST_ELEMENT);
    }
}
