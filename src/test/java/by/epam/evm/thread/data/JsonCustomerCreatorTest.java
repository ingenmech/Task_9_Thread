package by.epam.evm.thread.data;

import by.epam.evm.thread.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class JsonCustomerCreatorTest {

    private final static String JSON_DATA = "{  \"customers\": [   " +
            " {      \"id\": 1,      \"name\": \"customer\"    }," +
            "    {      \"id\": 2,      \"name\": \"customer\"    }  ]}";

    @Test
    public void testCreateShouldReturnListVisitorsWhenDataIsCorrect() {
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        List<Customer> expectedCustomers = Arrays.asList(
                new Customer(1, "customer", restaurant),
                new Customer(2, "customer", restaurant)
        );
        JsonCustomerCreator creator = new JsonCustomerCreator(restaurant);

        List<Customer> actualCustomers = creator.create(JSON_DATA);

        Assert.assertEquals(expectedCustomers, actualCustomers);
    }
}
