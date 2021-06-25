package at.bitscout.main;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 19.06.2021 19:38
    Changed from: Sebastian Gojer
    Changes: Line 22 Settings.fiat added
    Description: Main class for the program, just opens the window
 */
import at.bitscout.viewcontroller.Settings;
import at.bitscout.viewcontroller.Splashscreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Splashscreen splashscreen = new Splashscreen();
        splashscreen.startScreen(Settings.fiat);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
