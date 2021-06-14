package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Main class for the program, just opens the window
 */
import calc.Calculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Calculator calculator = new Calculator();
        Stage primstage=new Stage();
        calculator.start(primstage);

        primaryStage.setTitle("BitScout");
        primaryStage.setScene(new Scene(root, 1024, 600));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
