package at.bitscout.model;

import at.bitscout.model.Crypto;
import at.bitscout.model.Fiat;
import at.bitscout.model.RestAPI;
import at.bitscout.helper.FileStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/** Adds all crypto and fiat currencies in the right list
 * @author Bandalo
 * @version 1.2
 */

public class CurrencyList {
    //Combo box assets to select a crypto or fiat currency
    static ArrayList<Fiat> fiatArrayList = new ArrayList<>();
    static ArrayList<Crypto> cryptoArrayList = new ArrayList<>();

    public static ObservableList<String> cryptoNamesList = FXCollections.observableArrayList();
    public static ObservableList<String> fiatNamesList = FXCollections.observableArrayList();

    //Adds a new Crypto in the in the crypto ArrayList

    /** Adds currency to currency list
     * @param name name of currency
     * @param isoCode ios code of currency
     * @param is_Crypto defines if its a crypto or a currency
     */
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


    /**Removes a Crypto from the crypto list
     * @param name Name of Currency
     * @param is_Crypto Defines if it is a crypto or currency
     */
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

    /** loads the assets from the Files so the combo box the values in an instant (for better runtime)*/
    public static void loadCurrencyAssets(){
        FileStream fileStream = new FileStream();
        ArrayList<String> crypto;
        ArrayList<String> fiat;
        RestAPI api = new RestAPI();
        if(new File("src/at/bitscout/currencyAssets/cryptoAsset").exists() && new File("src/at/bitscout/currencyAssets/fiatAsset").exists()){
            crypto = (ArrayList<String>) fileStream.ReadObjectFromFile("src/at/bitscout/currencyAssets/cryptoAsset");
            fiat = (ArrayList<String>) fileStream.ReadObjectFromFile("src/at/bitscout/currencyAssets/fiatAsset");

            cryptoNamesList = FXCollections.observableList(crypto);
            fiatNamesList = FXCollections.observableList(fiat);
        }
        else{
            api.getAllAssets();
            crypto = new ArrayList<>(cryptoNamesList);
            fiat = new ArrayList<>(fiatNamesList);
            fileStream.WriteObjectToFile("src/at/bitscout/currencyAssets/cryptoAsset", crypto);
            fileStream.WriteObjectToFile("src/at/bitscout/currencyAssets/fiatAsset", fiat);
        }
    }

}
