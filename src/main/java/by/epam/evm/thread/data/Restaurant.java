package by.epam.evm.thread.data;

import by.epam.evm.thread.model.CashDesk;
import by.epam.evm.thread.model.Order;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private static Restaurant INSTANCE = null;
    private final static Lock LOCK = new ReentrantLock();

    private final static int CASH_DESC_NUMBER = 3;
    private final static int ORDERS_NUMBER = 20;

    private final CashDesk cashDesk = new CashDesk();
    private final Deque<CashDesk> cashDesks = new ArrayDeque<>();
    private final Deque<Order> orders = new ArrayDeque<>();
    private final Semaphore semaphore = new Semaphore(CASH_DESC_NUMBER, true);

    private Restaurant() {
    }

    public static Restaurant getInstance() {

        Restaurant local = INSTANCE;
        if (local == null) {
            LOCK.lock();
            local = INSTANCE;
            try {
                if (local == null) {
                    local = new Restaurant();
                    local.initOrders();
                    local.initCashDesks();
                    INSTANCE = local;
                }
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE;
    }

    private void initOrders() {

        for (int i = 0; i < ORDERS_NUMBER; i++) {
            orders.add(new Order(i));
        }
    }

    private void initCashDesks() {

        for (int i = 0; i < CASH_DESC_NUMBER; i++) {
            cashDesks.add(cashDesk);
        }
    }

    public CashDesk getCashDesk() throws DataException {

        LOCK.lock();
        try {
            semaphore.acquire();

            CashDesk cashDesk = cashDesks.poll();
            Order order = orders.poll();
            cashDesk.addOrder(order);
            return cashDesk;

        } catch (InterruptedException e) {
            throw new DataException(e.getMessage(), e);
        } finally {
            LOCK.unlock();
        }
    }

    public void returnCashDesk(CashDesk cashDesk) {

        LOCK.lock();
        try {
            cashDesks.add(cashDesk);
            semaphore.release();
        } finally {
            LOCK.unlock();
        }
    }

    public int getSizeOrders() {
        return orders.size();
    }

}
