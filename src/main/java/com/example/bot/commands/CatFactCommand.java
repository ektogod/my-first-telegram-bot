package com.example.bot.commands;

import com.example.bot.responses.CatResponse;
import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatFactCommand extends Command{
    @Value("${bot.catFactURL}")
    private String catFactURL;

    @Value("${bot.catImageURL}")
    private String catImageURL;

    @Autowired
    public CatFactCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(Bot bot, long chatId) {
        try {
            JSONObject fact = ResponseService.createJSONObject(catFactURL);
            JSONArray imageURL = ResponseService.createJSONArray(catImageURL);
            CatResponse catResponse = new CatResponse((String) fact.get("fact"), imageURL.getJSONObject(0).getString("url"));

            messageService.sendImage(catResponse.getCatImageURL(), "", chatId);
            messageService.sendMessage("Here is your random fact about cats!" + '\n' + '\n' + catResponse.getFact(), chatId);
        }
        catch(IOException ex){
            messageService.sendMessage("Something goes wrong!", chatId);
        }
    }
}
