package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Visitor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class JsonVisitorCreatorTest {

    private final static String JSON_DATA = "[  {    \"id\":1,    \"isPreorder\":false  },  {    \"id\":2,    \"isPreorder\":false  },  {    \"id\":3,    \"isPreorder\":true  }]";

    @Test
    public void testCreateShouldReturnListVisitorsWhenDataIsCorrect() {
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        List<Visitor> expectedVisitors = Arrays.asList(
                new Visitor(1, false, restaurant),
                new Visitor(2, false, restaurant),
                new Visitor(3, true, restaurant)
        );
        JsonVisitorCreator creator = new JsonVisitorCreator(restaurant);

        List<Visitor> actualVisitors = creator.create(JSON_DATA);

        Assert.assertEquals(expectedVisitors, actualVisitors);
    }
}
