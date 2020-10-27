package by.epam.evm.thread;

import by.epam.evm.thread.data.JsonVisitorCreator;
import by.epam.evm.thread.model.Visitor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        String DATA_FILE = "data.txt";
        List<Visitor> visitors = JsonVisitorCreator.create(DATA_FILE);
        ExecutorService service = Executors.newFixedThreadPool(visitors.size());

        visitors.forEach(visitor -> new Thread(visitor).start());


    }
}
