package sample;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Activity Logger von Marko
 */

public class ACLogger {
    final static String txtFileName="outputLog.txt";
    static FileWriter fW=null;

    public static void writeCorrespondence(String activityType, String activityText) {                      // diese Methode schreibt in das file outputLog.txt was oben in txtFileName angezeigt wird rein
        LocalTime errorTime=LocalTime.now();
        LocalDate errorDate=LocalDate.now();
        File file=new File(txtFileName);
        try {
            if (file.exists()){                                                                            //hier wird geschaut ob das file existiert wenn ja dann werden sachen dazugehaengt
                fW=new FileWriter(txtFileName,true);
            }
            else if(!file.exists())  {                                                                      // wenn das file nicht existiert dann wird ein neues file erstellt
                fW=new FileWriter(txtFileName);
            }
            fW.write(errorDate.toString()+"[]"+errorTime.getHour()+":"+errorTime.getMinute()+":"+errorTime.getSecond()+"[] "+activityType+"   "+activityText+"\n");                        //das ist die log message
            fW.close();                                                                                                 //dies symbolisiert das es zuende ist
        }
        catch (Exception e){
            System.out.println("Logger Funktioniert nicht");
        }

    }
    public static String readCorrespondence() throws IOException {                                                      //diese Methode Liest einzeln jede Zeile und gibt den Ganzen Log zurueck damit er in die Textarea reingesetzt werden kann
        StringBuffer stringBuffer=new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(txtFileName));
        for (int i = 0; i < lines.size(); i++) {
            // if(wort filter dings) dann appenden
            stringBuffer.append(lines.get(i)+"/n");
        }



        return  stringBuffer.toString();
    }
}
