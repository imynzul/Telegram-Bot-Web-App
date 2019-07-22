package com.evgen.bot;

import com.evgen.entity.Cities;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


/**
 * Данный класс отвечает за создание и работу бот
 *
 * @author Eugene +375-29-702-28-51
 * */
public class Bot extends TelegramLongPollingBot {

    /**
     * Метод инициализирует и запускает бот
     * */
    public static void botStarter() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод получает запрос пользователя и обрабатывает его
     * Для отправки ответа пользователю используется метод ниже - sendMsg();
     *
     * @param update - сообщение от пользователя
     * */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String cityName = message.getText();

        FirstMessageValidator messageValid = new FirstMessageValidator();
        messageValid.FMValidation(cityName);

        if (messageValid.isFirstMessage()){
            String greeting = messageValid.getJsonMessages().getString("greeting");
            sendMsg(message, greeting);
        } else {
            CityValidator validator = new CityValidator();
            validator.validation(cityName);

            if (validator.isValid()){
                Cities city = validator.getCityEntity();
                sendMsg(message, city.getInfo());
            } else{
                String response = validator.getJsonMessages().getString("response");
                sendMsg(message, response);
            }
        }
    }

    /**
     * Метод возвращает имя бот
     * */
    @Override
    public String getBotUsername() {
        return "CityInfoTestBot";
    }

    /**
     * Метод возвращает токен бот
     * */
    @Override
    public String getBotToken() {
        return "639836817:AAFGrwA9j0nT8B6kv0sUPAOzErQoUmF32hI";
    }

    /**
     * Метод формирует ответ и отправляет его конкретному пользователю по id чата
     *
     * @param message - содержит сообщение пользователя и id данного чата
     * @param text - содержит ответ бота пользователю
     * */
    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
