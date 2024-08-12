package com.example.bot.commands;

import com.example.bot.ResponseException;
import com.example.bot.dto.NasaResponse;
import com.example.bot.service.Bot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Setter
@Component
public class NasaImageCommand extends Command {
    @Value("${bot.nasaURL}")
    private String nasaURL;

    @Value("${nasa.token}")
    private String nasaToken;

    private String date;

    @Override
    public void run(Bot bot, long chatId) {
        try {
            NasaResponse nasaResponse = service.getResponse(String.format(nasaURL, nasaToken, date), NasaResponse.class);

            messageService.sendImage(nasaResponse.url(), nasaResponse.title(), chatId);
            messageService.sendMessage("Explanation: " + nasaResponse.explanation(), chatId);
        } catch (ResponseException ex) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                int start = ex.getMessage().indexOf('{');
                int end = ex.getMessage().indexOf('}') + 1;
                String jsonBody = ex.getMessage().substring(start, end);

                String errorMessage = mapper.readValue(jsonBody, Map.class).get("msg").toString();
                messageService.sendMessage(errorMessage, chatId);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
