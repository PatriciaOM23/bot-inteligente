package es.fplumara.dam.rebot.config;

import es.fplumara.dam.rebot.exceptions.ConfigException;
import es.fplumara.dam.rebot.model.LogMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.Properties;

public class AppConfig {
        private static AppConfig instance;
        private String storeType;
        private String messageFile;
        private Integer messageMaxLength;
        private final Properties props;
        private Path path = Path.of("C:\\Users\\patip\\IdeaProjects\\bot-inteligente\\data\\config.properties");

    private AppConfig() {
        try (InputStream in = Files.newInputStream(path)) {
            props = new Properties();
            props.load(in);
            storeType = props.getProperty("logs.mode", "TXT");
            messageMaxLength = Integer.parseInt(props.getProperty("log.maxMessageLength", "500"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
        //SINGLETON
    public static AppConfig getInstance() {
        if(instance == null){
            instance = new AppConfig();
        }
        return instance;
    }

    public boolean logsEnabled() {
        return  Boolean.parseBoolean(props.getProperty("logs.enabled","true"));
    }

    public void setLogsEnabled(){
         props.setProperty("logs.enabled","true");
    }

    public void setLogsDisabled(){
        props.setProperty("logs.enabled","false");
    }

    public Path logsDir(){
    return Path.of(props.getProperty("logs.dir","logs.dir"));

    }

    public LogMode logMode(){
        return LogMode.valueOf(props.getProperty("logs.mode","TXT"));
    }

    public void changeLogMode(String logMode){
        props.setProperty("logs.mode",logMode);
    }

    public boolean logsIncludeCommands(){
    return Boolean.parseBoolean(props.getProperty("logs.includeCommands","false"));
    }

    public int logsMaxMessageLength(){
        return Integer.parseInt(props.getProperty("logs.maxMessageLength","500"));
    }

    public Path reportsDir(){
    return Path.of(props.getProperty("reports.dir","data/reports"));
    }
    public boolean ttsEnabled(){
        return Boolean.parseBoolean(props.getProperty("tts.enabled","false"));
    }

    public Path ttsDir(){
        return Path.of(props.getProperty("tts.dir","data/tts"));
    }

    public int ttsTextMaxChars(){
        return Integer.parseInt(props.getProperty("tts.textMaxChars","300"));
    }

    public void createDefaultlfMissing(){

    }

    public void load()  {
        try(InputStream in = Files.newInputStream(path)){

        props.load(in);
        } catch (IOException e){
            throw new ConfigException("Unable to load the configuration");
        }
    }

    public void save(){
        //Si no llamo a save(), los cambios solo viven en memoria y se pierden al cerrar la app
        try(OutputStream out = Files.newOutputStream(path)){
            props.store(out, "Updated configuration");
        }catch (IOException e){
            throw new ConfigException("Unable to save the configuration");
        }

    }
}






