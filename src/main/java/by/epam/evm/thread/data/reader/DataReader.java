package by.epam.evm.thread.data.reader;

import by.epam.evm.thread.data.DataException;

public interface DataReader {
    String read(String fileName) throws DataException;
}
