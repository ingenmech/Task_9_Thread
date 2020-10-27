package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Visitor;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JsonVisitorCreator {

    public static List<Visitor> create(String data){
        JSONArray jsonVisitors = new JSONArray(data);
        return new ArrayList<>();
    }
}
