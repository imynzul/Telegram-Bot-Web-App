package com.evgen.bot;

import org.json.JSONObject;

/**
 * Класс проверяет является ли сообщения пользователя первым в этом чате или нет
 *
 * @author Eugene +375-29-702-28-51
 * */
public class FirstMessageValidator {
    private boolean firstMessage;
    private JSONObject jsonMessages;

    public FirstMessageValidator() {
        firstMessage = false;
        jsonMessages = new JSONObject();
    }

    /**
     * Метод проверяет является ли сообщения пользователя первым в этом чате
     *
     * Если да, то метод формирует приветсвие пользователю
     * @param message
     * */
    public void FMValidation(String message){
        if (message.equals("/start")){
            firstMessage = true;
            jsonMessages.put("greeting", "Greetings! My name is CityInfoBot! I can provide you " +
                    "information about best places to visit in some of the european capitals! " +
                    "Which cities are you planning to visit?");
        }
    }

    public boolean isFirstMessage() {
        return firstMessage;
    }

    public JSONObject getJsonMessages() {
        return jsonMessages;
    }
}
