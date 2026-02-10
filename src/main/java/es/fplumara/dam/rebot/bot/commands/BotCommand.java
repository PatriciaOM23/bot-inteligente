package es.fplumara.dam.rebot.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public interface BotCommand {
   //- **commands/BotCommand.java**
    //
    //    **Responsabilidad:** contrato para comandos.
    //
    //    Un comando debe poder:
    //
    //    - indicar el nombre del comando (ej: "!last")
    //    - ejecutarse con acceso a:
    //        - AppConfig
    //        - FileService
    //        - el evento de JDA

}
