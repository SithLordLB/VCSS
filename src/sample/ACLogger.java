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
 * Activity Logger van Marko
 */

public class ACLogger {
    final static String txtFileName="outputLog.txt";
    static FileWriter fW=null;

    public static void writeCorrespondence(String activityType, String activityText) {                      // This Method writes stuff into the new File called output.txt
        LocalTime errorTime=LocalTime.now();
        LocalDate errorDate=LocalDate.now();
        File file=new File(txtFileName);
        try {
            if (file.exists()){                                                                            //here the method looks if the file already exists or not if it exists it will be appended
                fW=new FileWriter(txtFileName,true);
            }
            else if(!file.exists())  {                                                                      // if it doesnt exists it will be created
                fW=new FileWriter(txtFileName);
            }
            fW.write(errorDate.toString()+"[]"+errorTime.getHour()+":"+errorTime.getMinute()+":"+errorTime.getSecond()+"[] "+activityType+"   "+activityText+"\n");                        //here is the Log message that is written
            fW.close();                                                                                                 //dies symbolisiert das es zuende ist
        }
        catch (Exception e){
            System.out.println("Logger Funktioniert nicht");
        }

    }
    public static String readCorrespondence() throws IOException {                                                      //this Method reads every line and returns a String for a text area
        StringBuffer stringBuffer=new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(txtFileName));
        for (int i = 0; i < lines.size(); i++) {
            // if you want to make a sort algorythm just put here an if and get at the call a Filter
            stringBuffer.append(lines.get(i)+"/n");
        }



        return  stringBuffer.toString();
    }
}
