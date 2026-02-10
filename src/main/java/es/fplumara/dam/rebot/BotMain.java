package es.fplumara.dam.rebot;

import es.fplumara.dam.rebot.bot.BotListener;
import es.fplumara.dam.rebot.bot.commands.CommandRegistry;
import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.services.files.DefaultFileService;
import es.fplumara.dam.rebot.services.files.FileService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;


public class BotMain {
    private static final String GUILD_ID = "1468558368997380098";



    public static void main(String[] args) throws InterruptedException, IOException {
        AppConfig config = AppConfig.getInstance();
        FileService fileService = new DefaultFileService();
        CommandRegistry commandRegistry = new CommandRegistry();
        String token = System.getenv("DISCORD_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Falta DISCORD_TOKEN en variables de entorno");
        }

        // 2) Arrancamos JDA

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new BotListener(config,fileService,commandRegistry))
                .build()
                .awaitReady();

        // 3) Registramos slash commands
        var guild = jda.getGuildById(GUILD_ID);
        if (guild == null) {
            throw new IllegalStateException("No encuentro el guild con id " + GUILD_ID);
        }
    }
        }

