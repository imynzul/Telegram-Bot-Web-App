package com.evgen.controllers;

import com.evgen.dao.CitiesDao;
import com.evgen.entity.Cities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Контроллер, содержащий основные методы для добавления, изменения и удаления инфы из БД
 * @author Eugene +375-29-702-28-51
 */
@RestController
@RequestMapping("/api")
public class MainController {

    private static final String RESPONSE_OK = "{\"status\":\"ok\"}";
    private static final String RESPONSE_FAIL = "{\"status\":\"fail\", \"message\":\"something goes wrong\"}";

    /**
     * Контроллер, возвращающий лист List, содержащий все города и информацию по ним и
     * передающий весь список на стартовую страницу.
     * <p>
     * @param response
     *
     * @return объект ModelAndView, содержащий список городов и страницу отображения
     */
    @RequestMapping(value = "/getall", produces = "application/json;charset=utf-8")
    public List<Cities> getAll(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        CitiesDao citiesDao = new CitiesDao();
        List<Cities> citiesList = citiesDao.getAll();
        citiesDao.closeCurrentSession();

        return citiesList;
    }


    /**
     * Контроллер сохраняет новый объект в БД
     * <p>
     * @param response
     * @param cityName
     * @param cityInfo
     *
     * @return JSONObject
     */
    @RequestMapping(value = "/save", produces = "application/json;charset=utf-8")
    public String save(
            HttpServletResponse response,
            @RequestParam String cityName,
            @RequestParam String cityInfo) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        CitiesDao citiesDao = new CitiesDao();
        Cities city = new Cities(cityName, cityInfo);
        citiesDao.insert(city);
        citiesDao.closeCurrentSession();

        return RESPONSE_OK;
    }

    /**
     * Контроллер обновляет информацию о городе в БД
     * <p>
     * @param id
     * @param response
     * @param cityName
     * @param cityInfo
     *
     * @return JSONObject
     */
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String update(
            HttpServletResponse response,
            @RequestParam long id,
            @RequestParam String cityName,
            @RequestParam String cityInfo) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        CitiesDao citiesDao = new CitiesDao();
        Cities cityUpdate = new Cities(id, cityName, cityInfo);
        citiesDao.update(cityUpdate);
        citiesDao.closeCurrentSession();

        return RESPONSE_OK;
    }

    /**
     * Контроллер удаляет объект город из БД
     * <p>
     * @param response
     * @param id
     *
     * @return JSONObject
     */
    @RequestMapping(value = "/delete", produces = "application/json; charset=utf-8")
    public String delete(HttpServletResponse response, @RequestParam long id) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        CitiesDao citiesDao = new CitiesDao();
        Cities cityToDel = citiesDao.get(id);
        citiesDao.delete(cityToDel);
        citiesDao.closeCurrentSession();

        return RESPONSE_OK;
    }
}
