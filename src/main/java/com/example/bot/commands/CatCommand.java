package com.example.bot.commands;

import com.example.bot.dto.CatFactResponse;
import com.example.bot.dto.CatImageResponse;
import com.example.bot.service.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatCommand extends Command {
    @Value("${bot.catFactURL}")
    private String catFactURL;

    @Value("${bot.catImageURL}")
    private String catImageURL;

    @Override
    public void run(Bot bot, long chatId) {
        CatFactResponse catFactResponse = service.getResponse(catFactURL, CatFactResponse.class);
        CatImageResponse[] catImageResponse = service.getResponse(catImageURL, CatImageResponse[].class);

        messageService.sendImage(catImageResponse[0].getCatImageURL(), "", chatId);
        messageService.sendMessage("Here is your random fact about cats!" + '\n' + '\n' + catFactResponse.fact(), chatId);
    }
}
