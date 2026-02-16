package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.config.AppConfig;
import es.fplumara.dam.rebot.exceptions.StoreException;
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
    private String logMode = String.valueOf(AppConfig.getInstance().logMode()).toLowerCase();
    private int maxCharacters = AppConfig.getInstance().logsMaxMessageLength();


   /* - Usa LogStoreFactory para obtener:
            - TxtLogStore o CsvLogStore
            - Crea directorios si faltan.

    - Usa AppConfig para:
            - decidir modo TXT/CSV (logsMode)
    - ruta base (logsDir)
    - máximo caracteres
     */
    @Override
    public void appendLog(String channelId, String entry) {
        // guarda entrada
        try {
            Path path = Path.of(pathLogsDir + "/" + channelId + "." + logMode);
            //crea directorios si faltan
            Files.createDirectories(pathLogsDir.getParent());
            //aplica recorte max length
            if (entry.length() > maxCharacters) {
                entry = entry.substring(0, maxCharacters);
            }
      // Usa LogStoreFactory para obtener:
            LogStore logStore = logStoreFactory.createLog(logMode);
            logStore.appendLog(path, entry);
        } catch (Exception e ){
            throw new StoreException("Failure appending logs.");
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
    public void readLast(String channelId, int n) {
        // últimas N líneas/entradas
        Path path =  Path.of(pathLogsDir + "/" + channelId + "." + logMode);
        LogStore logStore = logStoreFactory.createLog(logMode);
        logStore.readLast(path,n);

    }
}
