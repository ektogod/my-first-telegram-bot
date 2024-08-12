package com.example.bot.commands;

import com.example.bot.dto.DogResponse;
import com.example.bot.service.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DogImageCommand extends Command {
    @Value("${bot.dogImageURL}")
    private String dogImageURL;

    @Override
    public void run(Bot bot, long chatId) {
        DogResponse dogResponse = service.getResponse(dogImageURL, DogResponse.class);
        messageService.sendImage(dogResponse.dogImageURL(), "Here is your image!", chatId);
    }
}
