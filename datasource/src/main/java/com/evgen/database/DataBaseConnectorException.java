package com.evgen.database;

/**
 * Класс-исключение для методов подключения к БД
 *
 * @author Eugene +375-29-702-28-51
 */
public class DataBaseConnectorException extends RuntimeException {


    public DataBaseConnectorException(String message, Throwable cause) {
        super(message, cause);
    }


}
