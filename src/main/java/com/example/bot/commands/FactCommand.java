package com.example.bot.commands;

import com.example.bot.dto.FactResponse;
import com.example.bot.service.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FactCommand extends Command {
    @Value("${bot.factURL}")
    private String factURL;

    @Override
    public void run(Bot bot, long chatId) {
        FactResponse factResponse = service.getResponse(factURL, FactResponse.class);
        messageService.sendMessage(factResponse.fact(), chatId);
    }
}
