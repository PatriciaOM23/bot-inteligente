package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LastCommand implements BotCommand{
    public LastCommand() {
    }

    @Override
    public String getName() {
        return "!last";
    }
    public int getArgs(){
        //if commandregistry = lastcommand ask for int
        return 0;
    }
    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event) {

    }
}
