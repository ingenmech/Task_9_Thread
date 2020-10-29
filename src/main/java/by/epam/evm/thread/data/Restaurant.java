package by.epam.evm.thread.data;

import by.epam.evm.thread.model.CashDesk;
import by.epam.evm.thread.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private static Restaurant INSTANCE = null;
    private final static Lock LOCK = new ReentrantLock();
    private final static Condition IS_FREE = LOCK.newCondition();

    private final static int FIRST_ELEMENT = 0;
    private final static CashDesk CASH_DESK = new CashDesk();
    private final static List<CashDesk> CASH_DESKS = new ArrayList<>(Arrays.asList(CASH_DESK, CASH_DESK, CASH_DESK));

    private final List<Order> orders = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(CASH_DESKS.size(), true);

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
                    INSTANCE = local;
                    IS_FREE.signalAll();
                }
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE;
    }

    private void initOrders() {
        for (int i = 0; i < 30; i++) {
            orders.add(new Order(i));
        }
    }

    public CashDesk getCashDesk() throws ResourceException {

        LOCK.lock();
        try {
            semaphore.acquire();

            CashDesk cashDesk = CASH_DESKS.remove(FIRST_ELEMENT);
            Order order = orders.remove(FIRST_ELEMENT);
            cashDesk.addOrder(order);

            IS_FREE.signalAll();
            return cashDesk;

        } catch (InterruptedException e) {
            throw new ResourceException(e.getMessage(), e);
        } finally {
            LOCK.unlock();
        }
    }

    public void returnCashDesk(CashDesk cashDesk) {

        LOCK.lock();
        try {
            CASH_DESKS.add(cashDesk);
            semaphore.release();
            IS_FREE.signalAll();
        } finally {
            LOCK.unlock();
        }
    }

    public int getSizeOrders() {
        return orders.size();
    }

}
