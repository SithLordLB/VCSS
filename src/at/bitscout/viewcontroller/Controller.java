package at.bitscout.viewcontroller;

import at.bitscout.helper.FxUtilTest;
import at.bitscout.model.*;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Controller class for managing code to UI communication
 */

public class Controller implements Initializable {
    // Line Graph Attributes
    @FXML LineChart<?, ?> lchart;
    @FXML private CategoryAxis lc_x;
    @FXML private NumberAxis lc_y;
    @FXML ComboBox cbox_crypto = new ComboBox();
    @FXML ComboBox cbox_fiat = new ComboBox();
    @FXML private GridPane gpane_defaultRates = new GridPane();

    //For calling the method fillGraphdata()
    private final RestAPI api = new RestAPI();
    private List<CourseTimePeriod> courseTimePeriodList;
    private List<Rate> rateList;
    private String x;
    private String y;
    private ArrayList<Double> doubleArrayList = new ArrayList<>();
    private XYChart.Series series;
    private Splashscreen splashscreen = new Splashscreen();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calls method to fill the Comboboxes cbox_crypto & cbox_fiat
        //api.getAllAssets();
        CurrencyList.loadCurrencyAssets();

        //calls method for the VBox on the left side
        rateList = api.getAPICourse();
        showPopCryptos();
        //System.out.println(courseList.get(0).getAsset_id() + courseList.get(0).getPriceInUSD());    //test




        //checks if the at.bitscout.currencyAssets file exists
        //julian altinger wir müssen eine methode erstellen ob es überprüft ob das file exisitiert


        //Sets the comboboxes
        cbox_crypto.setItems(CurrencyList.cryptoNamesList);
        cbox_fiat.setItems(CurrencyList.fiatNamesList);
        cbox_fiat.setValue(Settings.fiat);
        FxUtilTest.autoCompleteComboBoxPlus(cbox_crypto, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));
        FxUtilTest.autoCompleteComboBoxPlus(cbox_fiat, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));
    }


    //Shows the line in the LineChart
    public void fillGraphdata(){
        //clears the chart
        lchart.getData().clear();

        //sets labels
        lchart.setTitle(cbox_crypto.getValue().toString());
        lc_x.setLabel("Hours");
        lc_y.setLabel(cbox_fiat.getValue().toString());

        courseTimePeriodList = api.getAPICourse(cbox_crypto.getValue().toString(), cbox_fiat.getValue().toString(), "1D");     //when combo box is clicked
        doubleArrayList = RateList.sortByRate(courseTimePeriodList);

        lc_y.setLowerBound(doubleArrayList.get(0));
        lc_y.setUpperBound(doubleArrayList.get(doubleArrayList.size()-1));
        lc_y.setTickUnit(24);

        series = new XYChart.Series();
        for(CourseTimePeriod obj: courseTimePeriodList){
            x = obj.getTime_period_start();
            x = x.substring(x.indexOf("T")+1, x.indexOf(":"));

            y = obj.getRate_open();
            //System.out.println(x + " " + y);
            series.getData().add(new XYChart.Data(x, Double.parseDouble(y)));
        }
        lchart.getData().addAll(series);
    }

    //adds a gridpane to the left which shows the rates of the most popular cryptos
    public void showPopCryptos(){
        for(int i=0; i<rateList.size(); i++) {
            VBox gpCell = new VBox();
            gpCell.setPadding(new Insets(12,12,12,12));
            gpane_defaultRates.add(gpCell, 0, i);

            Label cryptoName = new Label();
            cryptoName.setText(rateList.get(i).getAsset_id());
            cryptoName.setStyle("-fx-font: 20px Ebrima; -fx-text-fill: white");
            gpCell.getChildren().add(cryptoName);

            Label cryptoRate = new Label();
            cryptoRate.setText(rateList.get(i).getPriceInUSD() + " USD");
            cryptoRate.setStyle("-fx-font: 15px Ebrima; -fx-text-fill: white");
            gpCell.getChildren().add(cryptoRate);
        }
    }

    //If pressed the the settingslogo on the right top, you go to the settings shit
    public void startSettings() throws IOException {
        splashscreen.startSettings();
    }

    //if you want to go back to the Dashboard
    public void startBack() throws IOException {
        splashscreen.startScreen(cbox_fiat.getValue().toString());
        setCurrency();
    }

    //Changes the linegraph if the currency is changed
    public void changeCurrency(){
        try {
            fillGraphdata();
        }
        catch (NullPointerException e){
            System.out.println("Could not find currency");
        }
    }

    public void setCurrency(){

    }
}
