package by.epam.evm.thread.data.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDataReader implements DataReader {

    @Override
    public String read(String fileName) throws DataException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder builder = new StringBuilder();
            String readLine = reader.readLine();
            while (readLine != null) {
                builder.append(readLine);
                readLine = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
