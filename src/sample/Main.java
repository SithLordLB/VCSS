package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Main class for the program, just opens the window
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("BitScout");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();

        //Test
        RestAPI api = new RestAPI();
        Course course = api.getAPICourse("BTC", "EUR");
        System.out.println(course.getAsset_id_base() + " " + course.getAsset_id_quote() + " " + course.getRate() + " " + course.getTime());
        //api.getIcon(128);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
