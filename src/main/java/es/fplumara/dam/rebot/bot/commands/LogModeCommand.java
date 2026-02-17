package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LogModeCommand implements BotCommand{
    public LogModeCommand() {
    }

    @Override
    public String getName() {
        return "!logmode";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        if(appConfig.logMode().toString().equals("TXT")){
            appConfig.changeLogMode("CSV");
        } else {
            appConfig.changeLogMode("TXT");
        }
        appConfig.save();
        appConfig.load();
        event.getChannel().sendMessage("Log Mod: " + appConfig.logMode()).queue();

    }
}
