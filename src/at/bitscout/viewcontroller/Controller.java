package at.bitscout.viewcontroller;

import at.bitscout.helper.ACLogger;
import at.bitscout.helper.FxUtilTest;
import at.bitscout.helper.HoveredThresholdNode;
import at.bitscout.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Controller class for managing code to UI communication
 * @author Bandalo
 * @version 1.9
 */

public class Controller implements Initializable {
    // Line Graph Attributes and FXML controlls
    @FXML LineChart<?, ?> lchart;
    @FXML private CategoryAxis lc_x;
    @FXML private NumberAxis lc_y;
    @FXML private ComboBox cbox_crypto = new ComboBox();
    @FXML private ComboBox cbox_fiat = new ComboBox();
    @FXML private  ComboBox cbox_date = new ComboBox();
    @FXML private GridPane gpane_defaultRates = new GridPane();
    @FXML private Label lbl_Date = new Label();
    @FXML private Label lbl_Time = new Label();
    @FXML private ImageView imgv_settings = new ImageView();
    @FXML private TextArea txt_log = new TextArea();
    @FXML private TextField txtfield_searchLog = new TextField();
    @FXML private Button btn_logSearch = new Button();

    //For calling the method fillGraphdata() and showing
    private final RestAPI api = new RestAPI();
    private List<CourseTimePeriod> courseTimePeriodList;
    private List<Rate> rateList;
    private String x;
    private String y;
    private ArrayList<Double> doubleArrayList = new ArrayList<>();
    private XYChart.Series series;
    private XYChart.Data data;
    private Splashscreen splashscreen = new Splashscreen();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    private ObservableList <String> dateSelect = FXCollections.observableArrayList("1 Day", "1 Week", "1 Month", "1 Year");


    /** initialize method gets called when the program is started
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calls method to fill the Comboboxes cbox_crypto & cbox_fiat
        CurrencyList.loadCurrencyAssets();

        //calls method for the VBox on the left side
        rateList = api.getAPICourse();
        showPopCryptos();

        //Sets the comboboxes
        cbox_crypto.setItems(CurrencyList.cryptoNamesList);
        cbox_fiat.setItems(CurrencyList.fiatNamesList);
        cbox_fiat.setValue(Settings.fiat);
        cbox_date.setItems(dateSelect);
        FxUtilTest.autoCompleteComboBoxPlus(cbox_crypto, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));
        FxUtilTest.autoCompleteComboBoxPlus(cbox_fiat, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()));

        //shows in the left down corner the current date and time
        lbl_Date.setText(dtf.format(LocalDateTime.now()));
        lbl_Time.setText(dtf2.format(LocalDateTime.now()));

        //sets the tooltip of the neccessary controls
        cbox_crypto.setTooltip(new Tooltip("Select a crypto currency"));
        cbox_fiat.setTooltip(new Tooltip("Select your default currency"));
        cbox_date.setTooltip(new Tooltip("Select a date"));
    }


    /** Shows the line in the LineChart*/
    public void fillGraphdata(){
        try{
            ACLogger.writeCorrespondence("SEARCH","Searching for the Course:");
            //clears the chart
            lchart.getData().clear();

            //sets labels
            lchart.setTitle(cbox_crypto.getValue().toString());
            lc_y.setLabel(cbox_fiat.getValue().toString());

            //gets the list with the rate and sorts at the end
            courseTimePeriodList = api.getAPICourse(cbox_crypto.getValue().toString(), cbox_fiat.getValue().toString(), cbox_date.getValue().toString());     //when combo box is clicked
            doubleArrayList = RateList.sortByRate(courseTimePeriodList);

            //sets the max. and min. value on the y axis
            lc_y.setLowerBound(doubleArrayList.get(0));
            lc_y.setUpperBound(doubleArrayList.get(doubleArrayList.size()-1));

            //creates a new line on the chart
            series = new XYChart.Series();

            //sets the right x value depending on the date the user selected
            switch (cbox_date.getValue().toString()){
                case "1 Day":
                    //sets the label and the ticks right
                    lc_x.setLabel("Hours");
                    lc_y.setTickUnit(24);

                    //sets the values on the y and x axis
                    for(CourseTimePeriod obj: courseTimePeriodList){
                        x = obj.getTime_period_start();
                        x = x.substring(x.indexOf("T")+1, x.indexOf(":"));

                        y = obj.getRate_open();
                        //System.out.println(x + " " + y);
                        data = new XYChart.Data(x, Double.parseDouble(y));
                        data.setNode(new HoveredThresholdNode(x, Double.parseDouble(y)));
                        series.getData().add(data);
                    }
                    break;
                case "1 Week":
                    //sets the label and the ticks right
                    lc_x.setLabel("Days");
                    lc_y.setTickUnit(7);
                    //sets the values on the y and x axis
                    for(CourseTimePeriod obj: courseTimePeriodList) {
                        x = obj.getTime_period_start();
                        x = x.substring(x.indexOf("-")+4, x.indexOf("T"));

                        y = obj.getRate_open();
                        //System.out.println(x + " " + y);
                        //series.getData().add(new XYChart.Data(x, Double.parseDouble(y)));
                        data = new XYChart.Data(x, Double.parseDouble(y));
                        data.setNode(new HoveredThresholdNode(x, Double.parseDouble(y)));
                        series.getData().add(data);
                    }
                    break;
                case "1 Month":
                    //sets the label and the ticks right
                    lc_x.setLabel("Days " + LocalDate.now().minusDays(30).getMonth() + "-" + LocalDate.now().getMonth());
                    lc_y.setTickUnit(30);

                    //sets the values on the y and x axis
                    for(CourseTimePeriod obj: courseTimePeriodList){
                        x = obj.getTime_period_start();
                        x = x.substring(x.indexOf("-")+4, x.indexOf("T"));

                        y = obj.getRate_open();
                        //System.out.println(x + " " + y);
                        //series.getData().add(new XYChart.Data(x, Double.parseDouble(y)));
                        data = new XYChart.Data(x, Double.parseDouble(y));
                        data.setNode(new HoveredThresholdNode(x, Double.parseDouble(y)));
                        series.getData().add(data);
                    }
                    break;
                case "1 Year":
                    //sets the label and the ticks right
                    lc_x.setLabel("Months " + LocalDate.now().minusYears(1).getYear() + "-" + LocalDate.now().getYear());
                    lc_y.setTickUnit(30);

                    //sets the values on the y and x axis
                    for(CourseTimePeriod obj: courseTimePeriodList){
                        x = obj.getTime_period_start();
                        x = x.substring(x.indexOf("-")+1, x.indexOf("T")-3);

                        y = obj.getRate_open();
                        //System.out.println(x + " " + y);
                        //series.getData().add(new XYChart.Data(x, Double.parseDouble(y)));
                        data = new XYChart.Data(x, Double.parseDouble(y));
                        data.setNode(new HoveredThresholdNode(x, Double.parseDouble(y)));
                        series.getData().add(data);
                    }
                    break;
            }
            //adds the series (line) on the chart
            lchart.getData().addAll(series);
        }
        catch (NullPointerException e){
            ACLogger.writeCorrespondence("ERROR","Crypto currency or date not selected");
        }
    }

    /**adds a gridpane to the left which shows the rates of the most popular cryptos of the current time*/
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

    /** opens the calculator when the calculator icon is pressed */
    public void openCalc() throws Exception {
        ACLogger.writeCorrespondence("ERROR","Crypto currency or date not selected");
        Stage primaryStage = new Stage();
        Calculator calc = new Calculator();
        calc.start(primaryStage);
    }


    /**shows the log when you press "Show Log" in the settings window*/
    public void showLog() throws IOException {
        ACLogger.writeCorrespondence("FETCHING","Fetching output from outputLog.txt and writing it");
        txtfield_searchLog.setVisible(true);
        btn_logSearch.setVisible(true);
        txt_log.setVisible(true);
        searchLog();
    }

    /**searches the log with a user input filter*/
    public void searchLog() throws IOException {
        txt_log.setText(ACLogger.readCorrespondence(txtfield_searchLog.getText().toUpperCase()));
    }

    public void selectDate(){
        ACLogger.writeCorrespondence("MODIFY","Timespan Changed to: "+cbox_date.getValue().toString());
    }
    public void selectCrypto(){
        ACLogger.writeCorrespondence("MODIFY","Crypto Changed to: "+cbox_crypto.getValue().toString());
    }
    public void selectFiat(){
        ACLogger.writeCorrespondence("MODIFY","Fiat Changed to: "+cbox_fiat.getValue().toString());
    }

    /**If pressed the the settingslogo on the right top, you go to the settings*/
    public void startSettings() throws IOException {
        ACLogger.writeCorrespondence("START","The Settings Window started");
        splashscreen.startSettings();
    }

    /**if you want to go back to the Dashboard*/
    public void startBack() throws IOException {
        ACLogger.writeCorrespondence("RESTART","Returning to Dashboard");
        splashscreen.startScreen(cbox_fiat.getValue().toString());
    }
}
