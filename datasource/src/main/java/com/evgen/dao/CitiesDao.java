package com.evgen.dao;

import com.evgen.database.HibernateUtil;
import com.evgen.entity.Cities;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * DAO-класс для CRUD-операций с городами
 *
 * @author Eugene +375 29 702-28-51
 */
public class CitiesDao {

    public Session session;

    public CitiesDao() {
        session = HibernateUtil.getSession();
    }


    public Cities get(long id) {
        Cities city = session.get(Cities.class, id);
        return city;
    }


    public long insert(Cities ob) {
        return (long) session.save(ob);
    }


    public void update(Cities ob) {
        session.update(ob);
    }


    public void delete(Cities ob) {
        session.delete(ob);
    }

    /**
     * Метод обращается к БД и получает объект по названию города
     * В качестве аргумента ожидает String city - название города
     *
     * @param city
     * @return объект Cities, содержащий всю информацию о городе
     */
    public Cities getByCityName(String city) {
        Cities cityOb;
        try {
            Query<Cities> query = session.createQuery("FROM Cities WHERE city=:city", Cities.class);
            query.setParameter("city", city);
            cityOb = query.getSingleResult();
        } catch (NoResultException e) {
            cityOb = null;
        }
        return cityOb;
    }

    /**
     * Метод достает из БД свписок всех городов
     *
     * @return список List, содержащий все города и информацию по ним
     */
    public List<Cities> getAll() {
        List<Cities> citiesList;

        Query<Cities> query = session.createQuery("FROM Cities", Cities.class);
        citiesList = query.list();

        return citiesList;
    }

    /**
     * Данный метод закрывает соединение с БД
     */
    public void closeCurrentSession() {
        HibernateUtil.closeSession(session);
    }
}
