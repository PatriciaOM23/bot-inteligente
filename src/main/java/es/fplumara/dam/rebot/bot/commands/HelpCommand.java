package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand implements BotCommand{
    public HelpCommand() {
    }

    @Override
    public String getName() {
        return "!help";
    }

    @Override
    public void execute(AppConfig appConfig, FileService fileService, MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Comandos disponibles" +
                "!help -> Lista comandos disponibles y ejemplos." +
                "!readall -> Lee todas las líneas de un log" +
                "!last -> Leer las últimas líneas de un log - !last 20" +
                "!logon -> Permite registrar logs" +
                "!logoff -> Deja de registrar logs ").queue();
    }


}
