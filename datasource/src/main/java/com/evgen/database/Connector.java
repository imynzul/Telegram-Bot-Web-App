package com.evgen.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * Класс предназначен для создания и предоставления соединения с базой данных
 *
 * @author Eugene +375-29-702-28-51
 */
public class Connector {

    /**
     * Поле для хранения настроек подключения
     */
    private static Properties dbConnectionProperties;

    /**
     * Поле для хранения подключения к базе данных
     */
    private Connection connection;


    static {
        try (InputStream in = Connector.class.getResourceAsStream("/db_connection.properties")) {
            dbConnectionProperties = new Properties();
            dbConnectionProperties.load(in);
        }
        catch (IOException e) {
            DataBaseConnectorException connectorException = new DataBaseConnectorException("Properties init error", e);
            throw connectorException;
        }
    }


    /**
     * Загружает драйвер БД. Создаёт подключение с БД и инициализирует поле {@link #connection}
     */
    public Connector() {
        try {
            Class.forName(dbConnectionProperties.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                dbConnectionProperties.getProperty("db.url"),
                dbConnectionProperties.getProperty("db.user_login"),
                dbConnectionProperties.getProperty("db.user_password")
            );
        }
        catch (ClassNotFoundException | SQLException e) {
            DataBaseConnectorException connectorException = new DataBaseConnectorException(
                "Connector creation error: dbConnectionProperties=" + dbConnectionProperties,
                e
            );
            throw connectorException;
        }
    }


    /**
     * @return {@link #connection}
     */
    public Connection getConnection() {
        return connection;
    }


    /**
     * Выполняет закрытие всех ресурсов связанных с взаимодействием с БД
     *
     * @param resultSet
     * @param statement
     */
    public void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            }
            catch (SQLException e) {
                DataBaseConnectorException connectorException = new DataBaseConnectorException(
                    "ResultSet and PreparedStatement close error: resultSet=" + resultSet + ", statement=" + statement,
                    e
                );
                throw connectorException;
            }
        }

        close(statement);
    }


    /**
     * Выполняет закрытие всех ресурсов связанных с взаимодействием с БД
     *
     * @param statement
     */
    public void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                DataBaseConnectorException connectorException =
                    new DataBaseConnectorException("PreparedStatement close error: statement=" + statement, e);
                throw connectorException;
            }
        }

        if (this.connection != null) {
            try {
                this.connection.close();
            }
            catch (SQLException e) {
                DataBaseConnectorException connectorException =
                    new DataBaseConnectorException("Connection close error: connection=" + connection, e);
                throw connectorException;
            }
        }
    }
}
