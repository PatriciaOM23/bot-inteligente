package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface BotCommand {
    String getName();
    void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args);

}
