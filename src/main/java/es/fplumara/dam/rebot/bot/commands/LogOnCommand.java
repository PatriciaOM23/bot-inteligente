package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.model.LogEntry;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Timestamp;
import java.time.Instant;

public class LogOnCommand implements BotCommand{
    public LogOnCommand() {
    }

    @Override
    public String getName() {
        return "!logon";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        //Cambia logs.enabled=true en AppConfig
        appConfig.setLogsEnabled();
        //- save() para persistir
        appConfig.save();
        appConfig.load();
        //- Responde “Logging: ON”
        event.getChannel().sendMessage("Logging: ON").queue();

    }




}
