package com.example.bot.commands;

import com.example.bot.responses.CatResponse;
import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatFactCommand extends Command{

    @Autowired
    public CatFactCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(Bot bot, long chatId) {
        try {
            JSONObject object = ResponseService.createJSONObject(bot, chatId, Bot.CAT_FACT_URL);
            CatResponse catResponse = new CatResponse((String) object.get("fact"));

            messageService.sendMessage("Here is your random fact about cats!" + '\n' + '\n' + catResponse.getFact(), chatId);
        }
        catch(IOException ex){
            messageService.sendMessage("Something goes wrong!", chatId);
        }
    }
}
