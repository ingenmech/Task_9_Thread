package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Order;
import by.epam.evm.thread.model.SalePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Restaurant {

    private final static Restaurant INSTANCE = new Restaurant();
    private final static int FIRST_ELEMENT = 0;

    private final List<SalePoint> salePoints = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final Semaphore semaphore;

    private Restaurant() {
        salePoints.add(new SalePoint(1));
        salePoints.add(new SalePoint(2));
        salePoints.add(new SalePoint(3));
        semaphore = new Semaphore(salePoints.size(), true);
        fillOrder();
    }

    private final void fillOrder(){
        for (int i = 0; i < 15; i++){
            orders.add(new Order(1));
        }
    }

    public static Restaurant getInstance() {
        return INSTANCE;
    }

    public SalePoint getSalePoint() throws ResourceException {
        try {
            semaphore.acquire();

            SalePoint salePoint = salePoints.remove(FIRST_ELEMENT);
            Order order = orders.remove(FIRST_ELEMENT);
            salePoint.addOrder(order);

            return salePoint;

        } catch (InterruptedException e) {
            throw new ResourceException(e.getMessage(), e);
        }
    }

    public void returnSalePoint(SalePoint salepoint) {
        salePoints.add(salepoint);
        semaphore.release();
    }

    public int getSizeOrders(){
        return orders.size();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}
