package com.example.bot.commands;

import com.example.bot.responses.NasaResponse;
import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Setter
@Component
public class NasaImageCommand extends Command{
    private String date;

    @Autowired
    public NasaImageCommand(MessageService messageService){
        this.messageService = messageService;
    }

    @Override
    public void run(Bot bot, long chatId) {
        try{
            JSONObject object = ResponseService.createJSONObject("https://api.nasa.gov/planetary/apod?api_key=" + bot.getNasaToken() + "&date=" + date);
            NasaResponse nasaResponse = new NasaResponse(object.getString("url"),
                    object.getString("title"),
                    object.getString("explanation"));
            messageService.sendImage(nasaResponse.getUrl(), nasaResponse.getTitle(), chatId);
            messageService.sendMessage("Explanation: " + nasaResponse.getExplanation(), chatId);
        }
        catch(IOException ex) {
            messageService.sendMessage("Incorrect date. Please check input data.", chatId);
        }
    }
}
