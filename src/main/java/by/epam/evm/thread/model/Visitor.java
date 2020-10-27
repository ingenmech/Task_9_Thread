package by.epam.evm.thread.model;

import by.epam.evm.thread.data.ResourceException;
import by.epam.evm.thread.data.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Visitor implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Visitor.class);
    private boolean isPreorder;
    private Restaurant restaurant;
    private Order order;

    public Visitor(boolean isPreorder, Restaurant restaurant) {
        this.isPreorder = isPreorder;
        this.restaurant = restaurant;
    }

    private void process() throws ResourceException {
        SalePoint salePoint = restaurant.getSalePoint();
        order = salePoint.sell();
        restaurant.returnSalePoint(salePoint);
    }

    @Override
    public void run() {
        try {
            process();
        } catch (ResourceException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
