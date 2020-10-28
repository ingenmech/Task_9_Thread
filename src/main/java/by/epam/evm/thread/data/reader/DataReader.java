package by.epam.evm.thread.data.reader;

public interface DataReader {
    String read(String fileName) throws DataException;
}
