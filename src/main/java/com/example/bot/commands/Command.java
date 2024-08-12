package com.example.bot.commands;

import com.example.bot.service.Bot;
import com.example.bot.service.MessageService;
import com.example.bot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

abstract public class Command {
    @Autowired
    public MessageService messageService;

    @Autowired
    public ResponseService service;
    abstract public void run(Bot bot, long chatId);
}
