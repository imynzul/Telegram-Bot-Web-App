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
            "('Amsterdam', 'Аmsterdams name derives from Amstelredamme indicative of the citys origin around a dam in the river Amstel.')," +
            "('Athens', 'Classical Athens was a powerful city-state that emerged in conjunction with the seagoing development of the port of Piraeus.')," +
            "('Baku', 'Baku is divided into twelve administrative raions and 48 townships.')," +
            "('Belfast', 'By the early 19th century, Belfast became a major port. It played a key role in the Industrial Revolution, becoming the biggest linen-producer in the world, earning it the nickname \"Linenopolis\".')," +
            "('Belgrade', 'is the capital and largest city of Serbia. It is located at the confluence of the Sava and Danube rivers and the crossroads of the Pannonian Plain and the Balkan Peninsula.')," +
            "('Berlin', 'is the capital and largest city of Germany by both area and population. Its 3,748,148 (2018)[2] inhabitants make it the second most populous city proper of the European Union after London.')," +
            "('Bratislava', 'Bratislava is the capital of Slovakia. With a population of about 430,000, it is one of the smaller capitals of Europe but still the countrys largest city.')," +
            "('Brussels', 'Brussels is a region of Belgium comprising 19 municipalities, including the City of Brussels, which is the capital of Belgium.')," +
            "('Cardiff', 'Cardiff is the capital of Wales and its largest city. The eleventh-largest city in the United Kingdom, it is Waless chief commercial centre, the base for most national cultural institutions and Welsh media, and the seat of the National Assembly for Wales.')," +
            "('Copenhagen', 'Copenhagen is the capital and most populous city of Denmark.')," +
            "('Edinburgh', 'Edinburgh is the capital city of Scotland and one of its 32 council areas. Historically part of the county of Midlothian (interchangeably Edinburghshire prior to 1921), it is located in Lothian on the Firth of Forths southern shore.')," +
            "('Kiev', 'Kiev is the capital and most populous city of Ukraine, located in the north-central part of the country on the Dnieper.')," +
            "('Lisbon', 'Lisbon is the capital and the largest city of Portugal. With an estimated population of 505,526[1] within its administrative limits in an area of 100.05 km2,')," +
            "('London', 'London is the capital of and largest city in England and the United Kingdom, with the largest municipal population in the European Union.')," +
            "('Madrid', 'Madrid  is the capital of Spain and the largest municipality in both the Community of Madrid and Spain as a whole. ')," +
            "('Minsk', 'Minsk is the capital and largest city of Belarus, situated on the Svislač and the Nyamiha Rivers.')," +
            "('Moscow', 'Moscow is the capital and most populous city of Russia, with 13.2 million residents within the city limits,[12] 17 million within the urban area[13] and 20 million within the metropolitan area.')," +
            "('Prague', 'Prague is the capital and largest city in the Czech Republic, the 14th largest city in the European Union')," +
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