package com.example.bot.commands;

import com.example.bot.responses.DogResponse;
import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DogImageCommand extends Command{
    @Value("${bot.dogImageURL}")
    private String dogImageURL;
    @Autowired
    public DogImageCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(Bot bot, long chatId) {
        try{
            JSONObject object = ResponseService.createJSONObject(dogImageURL);
            DogResponse dogResponse = new DogResponse((String) object.get("message"));

            messageService.sendImage(dogResponse.getDogImageURL(), "Here is your image!", chatId);
        }
        catch(IOException ex){
            messageService.sendMessage("Something goes wrong!", chatId);
        }
    }
}
