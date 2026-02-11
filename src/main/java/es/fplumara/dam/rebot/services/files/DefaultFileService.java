package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.config.AppConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DefaultFileService implements  FileService{

    private Path pathLogsDir = AppConfig.getInstance().logsDir();
    private String logMode = String.valueOf(AppConfig.getInstance().logMode()).toLowerCase();
    private int maxCharacters = AppConfig.getInstance().logsMaxMessageLength();
   /* - Usa LogStoreFactory para obtener:
            - TxtLogStore o CsvLogStore
            - Crea directorios si faltan.
          */
    @Override
    public void appendLog(String channelId, String entry) {
        // guarda entrada
        try {
            Files.write(
                    Path.of(pathLogsDir + "/" + channelId + "." + logMode),
                    List.of(entry),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String readAll(String channelId) {
        //devuelve historial completo
        StringBuilder sb = new StringBuilder();
    try(BufferedReader br = Files.newBufferedReader(Path.of(pathLogsDir + "/" + channelId + "." + logMode))){
        String line;
        while ((line = br.readLine()) != null){
            sb.append(line);
        }
    } catch (IOException e){
        e.printStackTrace();
    }
    if(sb.toString().length() > maxCharacters){
        //Aplica recorte maxMessageLength.
    }
        return sb.toString();
    }

    @Override
    public String readLast(String channelId, int n) {
        // últimas N líneas/entradas
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = Files.newBufferedReader(Path.of(pathLogsDir + "/" + channelId + "." + logMode))) {
            String line;
            int count = 0;
            do {
                line = br.readLine();
                sb.append(line);
                count++;
            } while (count <= 20);

        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
