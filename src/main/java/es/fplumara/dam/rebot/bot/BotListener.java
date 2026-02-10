package es.fplumara.dam.rebot.bot;

import es.fplumara.dam.rebot.bot.commands.*;
import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
    private final AppConfig appConfig;
    private final FileService fileService;
    private final CommandRegistry commandRegistry;
    public BotListener(AppConfig appConfig,FileService fileService, CommandRegistry commandRegistry) {
        this.appConfig = appConfig;
        this.fileService = fileService;
        this.commandRegistry = commandRegistry;
    }

    public void onMessageReceived(MessageReceivedEvent event) {

        String msg = event.getMessage().toString();
        if (event.getAuthor().isBot()) {
            return;
        }
        if(msg.startsWith("!")){
            //Si el mensaje empieza por ! → delega a CommandRegistry.
        }
        //3. Si NO es comando:
        //    - si logs.enabled=false → no hace nada
        //    - si logs.enabled=true → crea LogEntry y lo guarda con FileService.appendLog(...)
    }
}

