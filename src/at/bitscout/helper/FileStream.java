package at.bitscout.helper;

import java.io.*;
/*
    Author: JA
    Created on: 12.06.2021
    Changed on: 14.06.2021
    Changed from: JA
    Description: Writes and reads objects from a file
 */
public class FileStream {
    public static void AccessFileStream(String filepath, Object observableList) {
        //private static final String filepath="C:\\Users\\nikos7\\Desktop\\obj";

        FileStream objectIO = new FileStream();

        objectIO.WriteObjectToFile(filepath, observableList);

        //Read object from file
        //CurrencyList cl = (CurrencyList) objectIO.ReadObjectFromFile(filepath);
        //System.out.println(cl);
    }

    //writes an object to an file
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
