package at.bitscout.helper;

import java.io.*;

/**
 * @author Altinger
 * @version 1.1
 * Description: Writes and reads objects from a file
 */



public class FileStream {
    /**
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

    //writes an object to an file

    /**
     *
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
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param filepath Destination of file
     * @return The object from files
     */

    //reads the objects from the file of the given path
    public Object ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
