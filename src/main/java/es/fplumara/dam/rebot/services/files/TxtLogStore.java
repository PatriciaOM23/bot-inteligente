package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.exceptions.StoreException;
import es.fplumara.dam.rebot.model.LogEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TxtLogStore implements LogStore{
    LogEntry logEntry;
    @Override
    public void appendLog(Path path, LogEntry entry) {
        // guarda entrada
        try {
            //timestamp | author | content
            // Inicicializo
            String line = entry.timestamp() + "|" + entry.author() + "|" + entry.content();
            Files.write(
                    path,
                    List.of(line),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (Exception e){
            throw new StoreException("Failure writing logs...");
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
            throw new StoreException("Failure reading logs.");
        }
        return sb.toString();

    }

    @Override
    public String readLast(Path path, int n) {
        try(BufferedReader br = Files.newBufferedReader(path)){
            List <String> text = new ArrayList<>();
            String line;
            // Leer todas las líneas en la lista
            while ((line = br.readLine()) != null) {
                text.add(line);
            }
            //si tiene más de n lineas empieza a leer por text.size - n
            int readLines = Math.max(0,text.size() - n);
            StringBuilder sb = new StringBuilder();
            //lee a partir de readLines
            for(int i = readLines; i < text.size(); i++){
                sb.append(text.get(i)).append("\n");
            }
            return sb.toString();
        } catch (Exception e ){
            throw new StoreException("Error al leer el archivo");
        }
}
}


