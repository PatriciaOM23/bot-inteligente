package es.fplumara.dam.rebot.services.files;

import es.fplumara.dam.rebot.exceptions.StoreException;
import es.fplumara.dam.rebot.model.LogEntry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvLogStore implements LogStore {
    LogEntry logEntry;
    @Override
    public void appendLog(Path path, LogEntry entry) {
        try {

            boolean fileExists = Files.exists(path);
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setDelimiter(';')
                    .setHeader("timestamp", "author", "entry")
                    .setSkipHeaderRecord(fileExists)
                    .get();
            //build is deprecated
            Writer writer = Files.newBufferedWriter(path,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            CSVPrinter printer = new CSVPrinter(writer,format);
            printer.printRecord(entry.timestamp(),entry.author(),entry.content());
            printer.flush();
            printer.close();
        } catch (Exception e){
            throw new StoreException("Failure trying to store csv.");
        }
    }

    @Override
    public String readAll(Path path) {
        try(Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("timestamp","author","entry")
                    .setSkipHeaderRecord(false)
                    .setTrim(true)
                    .get();
            CSVParser parser = format.parse(reader);
            StringBuilder sb = new StringBuilder();

            for(CSVRecord r : parser){
                sb.append(r.get("timestamp")).append(";");
               sb.append(r.get("author")).append(";");
                sb.append(r.get("entry")).append("\n");
            }
        return sb.toString();
        } catch (Exception e){
            throw new StoreException("CSV not found");
        }
    }

    @Override
    public String readLast(Path path, int n) {
        try(Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader("timestamp", "author", "entry")
                    .setSkipHeaderRecord(false)
                    .setTrim(true)
                    .get();
            CSVParser parser = format.parse(reader);
            List<CSVRecord> records = parser.getRecords();

            int length = Math.max(0,records.size() - n);
            StringBuilder sb = new StringBuilder();
            for(int i = length; i < records.size(); i++){
            CSVRecord r = records.get(i);
                sb.append(r.get("timestamp")).append(";");
                sb.append(r.get("author")).append(";");
                sb.append(r.get("entry")).append("\n");
            }
            return sb.toString();
            } catch (Exception e){
            throw new StoreException("CSV not found");
        }
    }
}
