package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Main class for the program, just opens the window
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Splashscreen splashscreen = new Splashscreen();
        splashscreen.startScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
