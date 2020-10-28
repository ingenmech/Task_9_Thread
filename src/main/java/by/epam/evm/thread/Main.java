package by.epam.evm.thread;

import by.epam.evm.thread.data.*;
import by.epam.evm.thread.data.reader.DataException;
import by.epam.evm.thread.data.reader.DataReader;
import by.epam.evm.thread.data.reader.FileDataReader;
import by.epam.evm.thread.model.Visitor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    //TODO
    //private final static Logger LOGGER

    public static void main(String[] args) {
        try {
            process();
        } catch (DataException e) {
            //Logger
        }
    }

    private static void process() throws DataException {

        String fileName = "data.json";
        DataReader reader = new FileDataReader();
        String jsonData = reader.read(fileName);

        Restaurant restaurant = Restaurant.getInstance();
        JsonVisitorCreator creator = new JsonVisitorCreator(restaurant);
        List<Visitor> visitors = creator.create(jsonData);

        ExecutorService service = Executors.newFixedThreadPool(visitors.size());
        visitors.forEach(visitor -> service.submit(visitor));
        service.shutdown();
    }
}
