package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.DefaultFileService;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LastCommand implements BotCommand {
    FileService fileService = new DefaultFileService();
    public LastCommand() {
    }

    @Override
    public String getName() {
        return "!last";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
        fileService.readLast(event.getChannel().toString(), n);
        } catch (NumberFormatException e){
            event.getChannel().sendMessage("Introduce un número válido. _(!last 10)_").queue();
        }
    }

}
