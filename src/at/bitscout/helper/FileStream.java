package at.bitscout.helper;

import java.io.*;

/** Writes and reads objects from a file
 * @author Altinger
 * @version 1.1
 *
 */



public class FileStream {

    /** Method to access FileStream
     * @param filepath Destination of file
     * @param observableList List of all assets
     */
    public static void AccessFileStream(String filepath, Object observableList) {

        FileStream objectIO = new FileStream();

        objectIO.WriteObjectToFile(filepath, observableList);

        //Read object from file
        //CurrencyList cl = (CurrencyList) objectIO.ReadObjectFromFile(filepath);
        //System.out.println(cl);
    }



    /** writes an object to an file
     * @param filepath Destination of file
     * @param serObj The objects from the observable list
     */
    public void WriteObjectToFile(String filepath,Object serObj) {

        try {


            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ACLogger.writeCorrespondence("ERROR","Failed to write Object to File");
        }
    }

    /** reads the objects from the file of the given path
     * @param filepath Destination of file
     * @return The object from files
     */

    public Object ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ACLogger.writeCorrespondence("ERROR","Failed to read Object to File");
            return null;
        }
    }

}
