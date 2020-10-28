package by.epam.evm.thread.data.reader;

import by.epam.evm.thread.data.reader.DataException;
import by.epam.evm.thread.data.reader.DataReader;
import by.epam.evm.thread.data.reader.FileDataReader;
import org.junit.Assert;
import org.junit.Test;

public class FileDataReaderTest {

    private final static String FILE_NAME = "src/test/resources/data.json";
    private final static String EXPECTED = "[  {    \"id\":1,    \"isPreorder\":false  },  {    \"id\":2,    \"isPreorder\":false  },  {    \"id\":3,    \"isPreorder\":true  }]";


    @Test
    public void testReadShouldReturnStringWhenDataIsCorrect() throws DataException {
        DataReader reader = new FileDataReader();

        String actual = reader.read(FILE_NAME);

        Assert.assertEquals(EXPECTED, actual);
    }
}
