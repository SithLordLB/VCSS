package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CurrencyList {
    //Combo box assets to select a crypto or fiat currency
    static ArrayList<Fiat> fiatArrayList = new ArrayList<>();
    static ArrayList<Crypto> cryptoArrayList = new ArrayList<>();

    static ObservableList<String> cryptoNamesList = FXCollections.observableArrayList();
    static ObservableList<String> fiatNamesList = FXCollections.observableArrayList();

    //Adds a new Crypto in the in the crypto ArrayList
    public static void addCurrency(String name, String isoCode, byte is_Crypto){
        if(is_Crypto == 1){
            cryptoArrayList.add(new Crypto(isoCode, name));
            cryptoNamesList.add(isoCode);
        }
        else{
            fiatArrayList.add(new Fiat(isoCode, name));
            fiatNamesList.add(isoCode);
        }
    }

    //Removes a Crypto in the crypto list
    public void removeCurrency(String name, String isoCode, byte is_Crypto){
        if(is_Crypto == 1){
            cryptoArrayList.remove(new Crypto(isoCode, name));
            cryptoNamesList.remove(isoCode);
        }
        else{
            fiatArrayList.remove(new Fiat(isoCode, name));
            fiatNamesList.remove(isoCode);
        }
    }

}
