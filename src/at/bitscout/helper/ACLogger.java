package at.bitscout.helper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/** Logger Class
 * @author Marko
 * @version 1.1
 **/

public class ACLogger {
    final static String txtFileName="log/outputLog.txt";
    static FileWriter fW=null;

    /** This Method writes activityType and activityText into the new File called output.txt
     * @param activityType Shows type of error.
     * @param activityText Shows error message
     **/
    public static void writeCorrespondence(String activityType, String activityText) { // This Method writes activityType and activityText into the new File called output.txt
        LocalTime errorTime=LocalTime.now();
        LocalDate errorDate=LocalDate.now();
        File file=new File(txtFileName);
        try {
            if (file.exists()){  //here the method looks if the file already exists or not if it exists it will be appended
                fW=new FileWriter(txtFileName,true);
            }
            else if(!file.exists())  {  // if it doesnt exists it will be created
                fW=new FileWriter(txtFileName);
            }
            fW.write(errorDate.toString()+"/"+errorTime.getHour()+":"+errorTime.getMinute()+":"+errorTime.getSecond()+"  "+"<"+activityType+">  "+activityText+"  <"+activityType+"> "+"\n");                        //here is the Log message that is written
            fW.close();   //closing filereader
        }
        catch (Exception e){
            //ACLogger.writeCorrespondence("ERROR","File Writer doesnt work");
        }

    }

    /** this Method reads every line and returns a String for a text area
     * @return Logger string
     */
    public static String readCorrespondence(String filter) throws IOException {      //this Method reads every line and returns a String for a text area
        StringBuffer stringBuffer=new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(txtFileName));
        for (int i = 0; i < lines.size(); i++) {

            /*
            reserved for an optional sort algorythm (make an if-statement and get at the call a Filter
            */
            if (lines.get(i).toUpperCase().contains(filter) || filter.equals("")){
                stringBuffer.append(lines.get(i)+"\n");
            }

        }
        return  stringBuffer.toString();
    }
}
