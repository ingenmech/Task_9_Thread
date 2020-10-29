package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonCustomerCreator {

    private final Restaurant restaurant;

    public JsonCustomerCreator(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Customer> create(String data) {

        JSONObject objectCustomers = new JSONObject(data);
        JSONArray arrayCustomers = objectCustomers.getJSONArray("customers");
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < arrayCustomers.length(); i++) {
            JSONObject jsonVisitor = arrayCustomers.getJSONObject(i);
            Customer customer = buildCustomer(jsonVisitor);
            customers.add(customer);
        }
        return customers;
    }

    private Customer buildCustomer(JSONObject object) {
        int id = object.getInt("id");
        boolean isPreorder = object.getBoolean("isPreorder");
        return new Customer(id, isPreorder, restaurant);
    }
}
