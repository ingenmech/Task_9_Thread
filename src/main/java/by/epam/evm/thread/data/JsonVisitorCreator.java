package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonVisitorCreator {

    private final Restaurant restaurant;

    public JsonVisitorCreator(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Visitor> create(String data) {

        JSONArray jsonVisitors = new JSONArray(data);
        List<Visitor> visitors = new ArrayList<>();
        Visitor visitor;
        JSONObject jsonVisitor;
        for (int i = 0; i < jsonVisitors.length(); i++) {
            jsonVisitor = jsonVisitors.getJSONObject(i);
            visitor = buildVisitor(jsonVisitor);
            visitors.add(visitor);
        }
        return visitors;
    }

    private Visitor buildVisitor(JSONObject object) {
        int id = object.getInt("id");
        boolean isPreorder = object.getBoolean("isPreorder");
        return new Visitor(id, isPreorder, restaurant);
    }
}
