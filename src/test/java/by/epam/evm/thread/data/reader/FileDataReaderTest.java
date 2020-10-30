package by.epam.evm.thread.data.reader;

import org.junit.Assert;
import org.junit.Test;

public class FileDataReaderTest {

    private final static String FILE_NAME = "src/test/resources/data.json";
    private final static String EXPECTED = "{  \"customers\": [    " +
            "{      \"id\": 1,      \"name\": \"customer\"    }," +
            "    {      \"id\": 2,      \"name\": \"customer\"    }  ]}";


    @Test
    public void testReadShouldReturnStringWhenDataIsCorrect() throws DataException {
        DataReader reader = new FileDataReader();

        String actual = reader.read(FILE_NAME);

        Assert.assertEquals(EXPECTED, actual);
    }

    @Test(expected = DataException.class)
    public void testReadShouldReturnExceptionWhenDataNotExist() throws DataException {
        DataReader reader = new FileDataReader();

        reader.read("src/test/resources/a.json");
    }
}
