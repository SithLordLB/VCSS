package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
/*
    Author: LB
    Created on: 08.06.2021
    Changed on: 14.06.2021
    Changed from: LB
    Description: Adds all crypto and fiat currencies in the right list
 */
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

    //loads the assets from the Files so the combobox the values in an instant (for better runtime)
    public static void loadCurrencyAssets(){
        FileStream fileStream = new FileStream();
        ArrayList<String> crypto;
        ArrayList<String> fiat;
        RestAPI api = new RestAPI();
        if(new File("src/currencyAssets/cryptoAsset").exists() && new File("src/currencyAssets/fiatAsset").exists()){
            crypto = (ArrayList<String>) fileStream.ReadObjectFromFile("src/currencyAssets/cryptoAsset");
            fiat = (ArrayList<String>) fileStream.ReadObjectFromFile("src/currencyAssets/fiatAsset");

            cryptoNamesList = FXCollections.observableList(crypto);
            fiatNamesList = FXCollections.observableList(fiat);
        }
        else{
            api.getAllAssets();
            crypto = new ArrayList<>(cryptoNamesList);
            fiat = new ArrayList<>(fiatNamesList);
            fileStream.WriteObjectToFile("src/currencyAssets/cryptoAsset", crypto);
            fileStream.WriteObjectToFile("src/currencyAssets/fiatAsset", fiat);
        }
    }

}
