package by.epam.evm.thread;

import by.epam.evm.thread.data.JsonCustomerCreator;
import by.epam.evm.thread.data.Restaurant;
import by.epam.evm.thread.data.reader.DataException;
import by.epam.evm.thread.data.reader.DataReader;
import by.epam.evm.thread.data.reader.FileDataReader;
import by.epam.evm.thread.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            process();
        } catch (DataException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void process() throws DataException {

        String fileName = "data.json";
        DataReader reader = new FileDataReader();
        String jsonData = reader.read(fileName);

        Restaurant restaurant = Restaurant.getInstance();
        JsonCustomerCreator creator = new JsonCustomerCreator(restaurant);
        List<Customer> customers = creator.create(jsonData);

        ExecutorService service = Executors.newFixedThreadPool(customers.size());
        customers.forEach(customer -> service.submit(customer));
        service.shutdown();
    }
}
