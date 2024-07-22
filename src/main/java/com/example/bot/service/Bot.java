package com.example.bot.service;

import com.example.bot.commands.CatFactCommand;
import com.example.bot.commands.DogImageCommand;
import com.example.bot.commands.NasaImageCommand;
import com.example.bot.config.BotConfigClass;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private ResponseService responseService;

    private MessageService messageService;

    @Autowired
    private CatFactCommand catFactCommand;

    @Autowired
    private DogImageCommand dogFactCommand;

    @Autowired
    private NasaImageCommand nasaFactCommand;

    private final BotConfigClass config;
    public static final String CAT_FACT_URL = "https://catfact.ninja/fact";
    public static final String DOG_IMAGE_URL = "https://dog.ceo/api/breeds/image/random";
    private boolean nasaFlag = false;

    @Autowired
    public Bot(BotConfigClass config, @Lazy MessageService messageService) {
        this.messageService = messageService;
        messageService.setBot(this);

        this.config = config;
        init();
    }

    private void init() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "Show commands with description"));
        commands.add(new BotCommand("/cats", "Get a random fact about cats"));
        commands.add(new BotCommand("/dogs", "Get a random dog image"));
        commands.add(new BotCommand("/nasa", "Get a Astronomy picture of the Day"));

        try {
            execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String username = update.getMessage().getChat().getFirstName();
            long chatId = update.getMessage().getChatId();

            switch (message) {
                case "/start" -> {
                    messageService.sendWelcomeMessage(username, chatId);
                    nasaFlag = false;
                }
                case ("/cats") -> {
                    catFactCommand.run(this, chatId);
                    nasaFlag = false;
                }
                case ("/dogs") -> {
                    dogFactCommand.run(this, chatId);
                    nasaFlag = false;
                }
                case ("/nasa") -> {
                    messageService.sendMessage("Please enter the date in YYYY-MM-DD format.", chatId);
                    nasaFlag = true;
                }
                default -> {
                    if (nasaFlag) {
                        nasaFlag = false;
                        nasaFactCommand.setDate(update.getMessage().getText());
                        nasaFactCommand.run(this, chatId);
                    } else {
                        messageService.sendMessage("Bot can't recognise your command. Please check your request.", chatId);
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    public String getNasaToken() {
        return config.getNasaToken();
    }
}
