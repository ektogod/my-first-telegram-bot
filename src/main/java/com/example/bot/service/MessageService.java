package com.example.bot.service;

import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Setter
@Component
public class MessageService {
    private Bot bot;

    public void sendWelcomeMessage(String username, long chatId) {
        String message = "Hi, " + username + "! We're glad to see you there! There are a couple of commands which are available for this bot:" +
                '\n' + '\n' +
                "/start: starts the bot and shows you a list of available commands." +
                '\n' + '\n' +
                "/cats: bot gives you a cat image and a random fact about cats." +
                '\n' + '\n' +
                "/dogs: bot sends you a random image of a funny dog." +
                '\n' + '\n' +
                "/nasa: bot sends you an Astronomy picture of the Day (APOD) from NASA archives. After sending this command, write a date of APOD you want to see in YYYY-MM-DD format.";
        sendMessage(message, chatId);
    }

    public void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendImage(String imageURL, String caption, long chatId) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId);
        photo.setPhoto(new InputFile(imageURL));
        photo.setCaption(caption);
        try {
            bot.execute(photo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
