package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileStream {
    private static final String filepath="C:\\Users\\nikos7\\Desktop\\obj";

    public static void main(String args[]) {

        FileStream objectIO = new FileStream();

        CurrencyList currencyList = new CurrencyList();
        objectIO.WriteObjectToFile(filepath, currencyList);

        //Read object from file
        CurrencyList cl = (CurrencyList) objectIO.ReadObjectFromFile(filepath);
        System.out.println(cl);
    }

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
