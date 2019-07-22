package com.evgen.bot;

import com.evgen.dao.CitiesDao;
import com.evgen.entity.Cities;
import org.json.JSONObject;

/**
 * Класс выполняющий валидацию данных
 *
 * @author Eugene +375-29-702-28-51
 * */
public class CityValidator {

    private boolean valid;
    private JSONObject jsonMessages;
    private Cities cityEntity;

    public CityValidator() {
        jsonMessages = new JSONObject();
        valid = true;
    }

    /**
     * Метод выполняет проверку наличия введеного города в БД
     *
     * В случае отсутствия города в БД формирует ответ пользователю
     *
     * @param cityName
     * @return JSONObject
     * */
    public void validation(String cityName){

        CitiesDao citiesDao = new CitiesDao();
        Cities city = citiesDao.getByCityName(cityName);
        citiesDao.closeCurrentSession();

        if (city == null){
            jsonMessages.put("response", "Sorry, I can't help you with this city( it's my first workday and I still have" +
                    "a lot to study, but I promise that soon I'll add info about this city to my database=) ");
            valid = false;
        } else {
            cityEntity = city;
        }
        jsonMessages.put("status", valid ? "verified" : "failed");
    }


    public boolean isValid() {
        return valid;
    }

    public JSONObject getJsonMessages() {
        return jsonMessages;
    }

    public Cities getCityEntity() {
        return cityEntity;
    }
}
