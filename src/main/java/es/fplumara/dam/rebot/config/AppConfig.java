package es.fplumara.dam.rebot.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AppConfig {


    public AppConfig() throws IOException {
        Path path = Path.of("C:\\Users\\patip\\IdeaProjects\\bot-inteligente\\data\\config.properties");
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            props.load(in);
        }
    }

    public boolean logsEnabled() {
    return true;
    }

    public void setLogsEnabled(){
    }

    public Path logsDir(){
    return null;
    }

    public boolean logsIncludeCommands(){
    return false;
    }

    public int logsMaxMessageLength(){
        return 0;
    }

    public Path reportsDir(){
    return Path.of(null);
    }

    public boolean ttsEnabled(){
        return false;
    }

    public Path ttsDir(){
        return Path.of(null);
    }

    public int ttsTextMaxChars(){
        return 0;
    }

    public void createDefaultlfMissing(){

    }

    public void load(){

    }

    public void save(){

    }
}






