package sample;
/*
    Author: LB
    Created on: 03.05.2021
    Changed on: 03.05.2021
    Changed from:
    Description: Controller class for managing code to UI communication
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    static FileWriter fileWriter;
    static final String logFile = "log.txt";
    static File file = new File(".//" + logFile);

    public static void writeLog(String text) throws IOException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm:ss");

            if(file.exists()){
                fileWriter = new FileWriter(logFile, true);
            }
            else {
                fileWriter = new FileWriter(logFile, Charset.defaultCharset());
            }

            fileWriter.write(dateTimeFormatter.format(localDateTime) + "| " + text + "\n");
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR writing in File" + e.toString());
            fileWriter.write("ERROR writing in File " + e.toString());
        }
    }

}
