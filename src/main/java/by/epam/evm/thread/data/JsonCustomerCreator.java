package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonCustomerCreator {

    private final static String OBJECT_KEY = "customers";
    private final static String INJECTION = "restaurant";
    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final Restaurant restaurant;

    public JsonCustomerCreator(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Customer> create(String data) throws DataException {

        JSONObject objectCustomers = new JSONObject(data);
        JSONArray arrayCustomers = objectCustomers.getJSONArray(OBJECT_KEY);
        String jsonObjects = arrayCustomers.toString();

        InjectableValues.Std values = new InjectableValues.Std();
        values.addValue(INJECTION, restaurant);

        MAPPER.setInjectableValues(values);
        try {
            return MAPPER.readValue(jsonObjects, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
