package com.evgen.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

/**
 * Класс отвечает за создание базы данных при запуске модулей проекта: api и tgbot,
 * открывает и закрывает сессию
 *
 * @author Eugene +375-29-702-28-51
 * */
public class HibernateUtil {

    private static final String DROP_DB_QUERY = "drop database cityinfo";
    private static final String CREATE_DB_QUERY = "create database cityinfo";
    private static final String CREATE_TABLE_QUERY = "create table cities (id INT AUTO_INCREMENT UNIQUE NOT NULL, city VARCHAR(80) UNIQUE NOT NULL, info TEXT NOT NULL)";
    private static final String INSERT_DATA_QUERY = "insert into cities (city, info) values" +
            "('Dublin', 'They say you must be riding high on the Luck of the Irish to find yourself in the historic city of Dublin.')," +
            "('Paris', 'You might have heard countless things about Paris. You might have been there more than once.')," +
            "('Oslo', 'Oslo is one of the worlds largest capitals in terms of area, but only 20 percent of this land mass has been developed - the remainder consists of parks, protected forests, hills, and hundreds of lakes.')," +
            "('Rome', 'So, you’re planning to visit Rome during your next trip to Italy?With your lover or family, Rome is the perfect place to spend a 2-3 days weekend, especially since the city can be easily visited on foot!')," +
            "('Warsaw', 'Poland’s capital, Warsaw is the pulsating heart of the country.')";

    private static SessionFactory sessionFactory;

    /**
     * Статический блок, создающий БД и фабрику соединений
     * */
    static {
        checkDB();
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Метод создает соединения с БД
     * */
    public static Session getSession(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        return session;
    }

    /**
     * Метод закрывает соединения с БД
     * */
    public static void closeSession(Session session){
        if (session != null){
            session.getTransaction().commit();
            session.close();
        }
    }

    /**
     * Метод создает БД
     * */
    private static void checkDB(){
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(DROP_DB_QUERY);
            statement.executeUpdate(CREATE_DB_QUERY);
            statement.executeUpdate("use cityinfo");
            statement.executeUpdate(CREATE_TABLE_QUERY);
            statement.executeUpdate(INSERT_DATA_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException("Check database error", e);
        } finally {
            connector.close(statement);
        }
    }
}