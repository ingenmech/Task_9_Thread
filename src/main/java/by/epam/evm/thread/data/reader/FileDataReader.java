package by.epam.evm.thread.data.reader;

import by.epam.evm.thread.data.DataException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileDataReader implements DataReader {

    @Override
    public String read(String fileName) throws DataException {

        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);
            StringBuilder builder = new StringBuilder();
            lines.forEach(line -> builder.append(line));

            return builder.toString();

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
