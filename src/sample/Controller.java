package sample;

import javafx.fxml.FXML;
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
public class Controller {

    @FXML ComboBox cmb_Waehrung;
    @FXML ComboBox cmb_Kursdatum;
    @FXML TextArea txt_Kurse;
    @FXML LineChart lc_Kursgraph;
    
}
