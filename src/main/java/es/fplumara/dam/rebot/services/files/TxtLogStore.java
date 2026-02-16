package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.model.LogEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TxtLogStore implements LogStore{
    LogEntry logEntry;
    @Override
    public void appendLog(Path path, String entry) {
        // guarda entrada
        try {
            //timestamp | author | content
            Files.write(
                    path,
                    List.of(new LogEntry(logEntry.getTimestamp(),logEntry.getAuthor(),entry).toString()),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String readAll(Path path) {
        //devuelve historial completo
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = Files.newBufferedReader(path)){
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();

    }

    @Override
    public String readLast(Path path, int n) {
        // últimas N líneas/entradas
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int count = 0;
            do {
                line = br.readLine();
                sb.append(line);
                count++;
            } while (count <= n);

        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}


