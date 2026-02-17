package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ReadAllCommand implements BotCommand{
    public ReadAllCommand() {
    }

    @Override
    public String getName() {
        return "!readall";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage(fileService.readAll(event.getChannel().getId())).queue();
    }
}
