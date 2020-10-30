package by.epam.evm.thread.data;

import by.epam.evm.thread.model.CashDesk;
import by.epam.evm.thread.model.Order;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private static Restaurant INSTANCE = null;
    private final static Lock LOCK = new ReentrantLock();
   // private final static Condition IS_FREE = LOCK.newCondition();

    private final static CashDesk CASH_DESK = new CashDesk();
    private final static Deque<CashDesk> CASH_DESKS = new ArrayDeque<>(Arrays.asList(CASH_DESK, CASH_DESK, CASH_DESK));

    private final Deque<Order> orders = new ArrayDeque<>();
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
                }
            } finally {
                LOCK.unlock();
                //IS_FREE.signalAll();
            }
        }
        return INSTANCE;
    }

    private void initOrders() {

        for (int i = 0; i < 300; i++) {
            orders.add(new Order(i));
        }
    }

    public CashDesk getCashDesk() throws ResourceException {

        LOCK.lock();
        try {
            semaphore.acquire();

            CashDesk cashDesk = CASH_DESKS.poll();
            Order order = orders.poll();
            cashDesk.addOrder(order);

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

        } finally {
            LOCK.unlock();
        }
    }

    public int getSizeOrders() {
        return orders.size();
    }

}
