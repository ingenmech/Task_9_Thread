package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Order;
import by.epam.evm.thread.model.SalePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Restaurant {

    private final static Restaurant INSTANCE = new Restaurant();
    private final static int FIRST_ELEMENT = 0;
    private final static int NUMBER_SALE_POINT = 3;

    private final List<Order> orders = new ArrayList<>();
    private final List<SalePoint> points = new ArrayList<>();

    private final Semaphore semaphore = new Semaphore(NUMBER_SALE_POINT, true);


    private Restaurant() {
    }

    public static Restaurant getInstance() {
        return INSTANCE;
    }

    public SalePoint getSalePoint() throws ResourceException {

        try {
            semaphore.acquire();

            SalePoint salePoint = points.remove(FIRST_ELEMENT);
            Order order = this.getOrder();
            salePoint.addOrder(order);

            return salePoint;

        } catch (InterruptedException e) {
            throw new ResourceException(e.getMessage(), e);
        }
    }

    public void returnSalePoint(SalePoint point) {
        points.add(point);
        semaphore.release();
    }

    private Order getOrder() {
        return orders.remove(FIRST_ELEMENT);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}
