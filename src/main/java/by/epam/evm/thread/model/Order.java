package by.epam.evm.thread.model;

import java.util.Objects;

public class Order {
    private final int price;

    public Order(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return price == order.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                '}';
    }
}
