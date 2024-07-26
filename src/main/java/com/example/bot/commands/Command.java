package com.example.bot.commands;

import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

abstract public class Command {
    public MessageService messageService;
    abstract public void run(Bot bot, long chatId);
}
