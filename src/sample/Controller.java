package sample;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javax.swing.text.html.ImageView;


/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Controller class for managing code to UI communication
 */

public class Controller implements Initializable {
    // FXML Attributes
    @FXML ComboBox cmb_Waehrung;
    @FXML ComboBox cmb_Kursdatum;
    @FXML TextArea txt_Kurse;
    @FXML LineChart lc_Kursgraph;
    // Line Graph Attributes
    @FXML private LineChart<?, ?> lchart;
    @FXML private CategoryAxis lc_x;
    @FXML private NumberAxis lc_y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series = new XYChart.Series();
        series.setName("benis");
        XYChart.Series series2 = new XYChart.Series();


        series.getData().add(new XYChart.Data("12", 30000));
        series.getData().add(new XYChart.Data("16", 35000));
        series.getData().add(new XYChart.Data("20", 37000));

        series2.getData().add(new XYChart.Data("12", 30));
        series2.getData().add(new XYChart.Data("16", 5000));
        series2.getData().add(new XYChart.Data("20", 1000));



    }

}
