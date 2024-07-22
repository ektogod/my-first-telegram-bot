package com.example.bot.config;

import com.example.bot.service.Bot;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

@Configuration
@Data

@PropertySource("application.properties")

public class BotConfigClass {
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${nasa.token}")
    private String nasaToken;

//    @Bean
//    public TelegramLongPollingBot telegramLongPollingBot(BotConfigClass config){
//        return new Bot(config);
//    }
//
//    @Bean
//    public DefaultBotOptions defaultBotOptions(){
//        return new DefaultBotOptions();
//    }
}
