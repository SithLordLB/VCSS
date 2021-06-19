package at.bitscout.viewcontroller;
/*
    Author: Sebastian Gojer
    Created on: 12.06.2021 18:50
    Changed on:
    Changed from:
    Description: Functions for start screen and settings screen
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import at.bitscout.*;

import java.io.IOException;

public class Splashscreen {
    static Stage stage = new Stage();

    //Start screen you see when launching the program
    public void startScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./dashboard.fxml"));
        stage.setTitle("BitScout");
        stage.getIcons().add(new Image("/at/bitscout/Images/BitscoutB_Transparent.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1048, 600));
        stage.show();
    }

    //Creates new stage with the instruction of how Black Jack works
    public void startSettings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("./settings.fxml"));
        stage.setScene(new Scene(root, 372, 400));
        stage.show();
    }

}