package com.example.bot.commands;

import com.example.bot.responses.FactResponse;
import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FactCommand extends Command {
    @Value("${bot.factURL}")
    private String factURL;

    @Autowired
    public FactCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(Bot bot, long chatId) {
        try {
            JSONObject object = ResponseService.createJSONObject(factURL);
            FactResponse response = new FactResponse((String) object.get("text"));

            messageService.sendMessage(response.getFact(), chatId);
        }
        catch (IOException ex){
            messageService.sendMessage("Something goes wrong!", chatId);
        }
    }
}
