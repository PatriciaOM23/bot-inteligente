package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LogOffCommand implements BotCommand{
    public LogOffCommand() {
    }

    @Override
    public String getName() {
        return "!logoff";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        //- Cambia logs.enabled=false
        appConfig.setLogsDisabled();
        //- save()
        //- Responde “Logging: OFF”
        event.getChannel().sendMessage("Logging: OFF").queue();
    }


}
