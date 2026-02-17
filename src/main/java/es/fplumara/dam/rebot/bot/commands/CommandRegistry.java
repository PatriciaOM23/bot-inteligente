package es.fplumara.dam.rebot.bot.commands;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    MessageReceivedEvent event;
    AppConfig appConfig;
    FileService fileService;
    Map<String, BotCommand> commandsMap = new HashMap<>();
    public CommandRegistry() {
       addCommand(new HelpCommand());
       addCommand(new LastCommand());
       addCommand(new LogOnCommand());
       addCommand(new LogOffCommand());
       addCommand(new ReadAllCommand());
    }

    public void addCommand (BotCommand command){
        commandsMap.put(command.getName(),command);
    }

    public void handleCommands(AppConfig appConfig,FileService fileService, MessageReceivedEvent event, String input){
        // DIVIDO COMANDO EN !comando
        String[] inputCommand = input.split(" ");
        String commandUser = inputCommand[0];
        // GUARDO LOS ARGUMENTOS DEL COMANDO
        String[] args = Arrays.copyOfRange(inputCommand,1,inputCommand.length);
        BotCommand command = commandsMap.get(commandUser);
        if(command == null){
            event.getChannel().sendMessage("Comando desconocido. Para ver los comandos usa !help").queue();
            return;
        }
        command.execute(appConfig,fileService,event,args);

    }

}
