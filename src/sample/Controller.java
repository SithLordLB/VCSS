package sample;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;



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
    @FXML private ComboBox cbox_crypto;
    @FXML private ComboBox cbox_fiat;

    //For calling the method fillGraphdata()
    private RestAPI api = new RestAPI();
    private List<CourseTimePeriod> courseTimePeriodList;
    private List<Course> courseList;
    private String x;
    private String y;
    private ArrayList<Double> doubleArrayList = new ArrayList<>();
    private XYChart.Series series;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //api.getAPICourse("BTC", "EUR");
        //Calls method to fill the Comboboxes cbox_crypto & cbox_fiat
        //api.getAllAssets();
        courseList = api.getAPICourse();
        System.out.println(courseList.get(0).getAsset_id() + courseList.get(0).getPriceInUSD());



        //Sets the comboboxes
        cbox_crypto.setItems(CurrencyList.cryptoNamesList);
        cbox_fiat.setItems(CurrencyList.fiatNamesList);

        FxUtilTest.autoCompleteComboBoxPlus(cbox_crypto, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));
        FxUtilTest.autoCompleteComboBoxPlus(cbox_fiat, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));

    }


    //Shows the line in the LineChart
    public void fillGraphdata(){
        //clears the chart
        lchart.getData().clear();

        lchart.setTitle(cbox_crypto.getValue().toString());
        lc_x.setLabel("Hours");
        lc_y.setLabel(cbox_fiat.getValue().toString());

        courseTimePeriodList = api.getAPICourse(cbox_crypto.getValue().toString(), cbox_fiat.getValue().toString(), "1D");     //when combo box is clicked
        doubleArrayList = CourseList.sortByRate(courseTimePeriodList);

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

    //Changes the linegraph if the currency is changed
    public void changeCurrency(){
        fillGraphdata();
    }
}
