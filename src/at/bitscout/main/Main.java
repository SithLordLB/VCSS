package at.bitscout.main;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 19.06.2021 19:38
    Changed from: Sebastian Gojer
    Changes: Line 22 Settings.fiat added
    Description: Main class for the program, just opens the window
 */
import at.bitscout.helper.ACLogger;
import at.bitscout.viewcontroller.Settings;
import at.bitscout.viewcontroller.Splashscreen;
import javafx.application.Application;
import javafx.stage.Stage;

/** Main Class of the program
 * @author Bandalo
 * @version 1.3
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Runtime.getRuntime().addShutdownHook(hook);
        Splashscreen splashscreen = new Splashscreen();
        ACLogger.writeCorrespondence("START","Program has started");
        splashscreen.startScreen(Settings.fiat);
    }

    //when the thread is closed, then the logger logs it
    Thread hook = new Thread(){
        public void run()
        {
            ACLogger.writeCorrespondence("TERMINATE","Programm has been shutdowned");
        }
    };


    public static void main(String[] args) {
        launch(args);
    }
}
