package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.exceptions.StoreException;
import es.fplumara.dam.rebot.model.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DefaultFileService implements  FileService{
    private static final Logger log = LoggerFactory.getLogger(DefaultFileService.class);
    LogStoreFactory logStoreFactory = new LogStoreFactory();
    private Path pathLogsDir = AppConfig.getInstance().logsDir();
    private String logMode = String.valueOf(AppConfig.getInstance().logMode());
    private int maxCharacters = AppConfig.getInstance().logsMaxMessageLength();

    @Override
    public void appendLog(String channelId, LogEntry entry) {
        // guarda entrada
        try {
            System.out.println("LOG MODE -> " + logMode);
            Path path = Path.of(pathLogsDir + "/" + channelId + "." + logMode);
            //crea directorios si faltan
            Files.createDirectories(pathLogsDir);
            //aplica recorte max length
            String content = entry.content();
            if (content.length() > maxCharacters) {
                content = content.substring(0, maxCharacters);
            }
        // contenido recortado
            LogEntry trimmedEntry = new LogEntry(
              entry.timestamp(),
                    entry.author(),
                    content );
            LogStore logStore = logStoreFactory.createLog(logMode);
            logStore.appendLog(path, trimmedEntry);
        } catch (Exception e ){
            throw new StoreException("Failure appending logs.", e);
        }
    }

    @Override
    public String readAll(String channelId) {
        //devuelve historial completo
        Path path =  Path.of(pathLogsDir + "/" + channelId + "." + logMode);
        LogStore logStore = logStoreFactory.createLog(logMode);
        String info = logStore.readAll(path);
        if(info.length() > maxCharacters){
           return info.substring(0,maxCharacters);
        }
        return info;
    }

    @Override
    public String readLast(String channelId, int n) {
        // últimas N líneas/entradas
        Path path =  Path.of(pathLogsDir + "/" + channelId + "." + logMode);
        LogStore logStore = logStoreFactory.createLog(logMode);
        String lastLines = logStore.readLast(path,n);
        if(lastLines.length() > maxCharacters){
            return lastLines.substring(0,maxCharacters);
        }
        return lastLines;
    }
}
