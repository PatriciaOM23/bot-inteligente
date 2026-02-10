package es.fplumara.dam.rebot.bot;

import es.fplumara.dam.rebot.bot.commands.*;
import es.fplumara.dam.rebot.config.AppConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
    private final AppConfig appConfig;
    public BotListener(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void onMessageReceived(MessageReceivedEvent event) {

        String msg = event.getMessage().toString();
        if (event.getAuthor().isBot()) {
            return;
        }

        if (msg.startsWith("!last")) {
            CommandRegistry commandRegistry = new CommandRegistry(event);
        }
        if (msg.startsWith("!log on")){
        LogOnCommand logOnCommand = new LogOnCommand(event);
        }
        if(msg.startsWith("!log off")){
            LogOffCommand logOffCommand = new LogOffCommand(event);
        }
        if(msg.startsWith("!last")){
            LastCommand lastCommand = new LastCommand(event);
        }
        if(msg.startsWith("!help")){
            HelpCommand helpCommand = new HelpCommand(event);
        }
    }
}

