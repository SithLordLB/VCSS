package at.bitscout.viewcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/** Functions for start screen and settings screen
 * @author Gojer
 * @version 1.3
 */

public class Splashscreen {
    public static Stage stage = new Stage();

    /** Start screen you see when launching the program
     * @param cbox_fiat combo box of fiat
     */
    public void startScreen(String cbox_fiat) throws IOException {
        Settings.fiat = cbox_fiat;

        Parent root = FXMLLoader.load(getClass().getResource("./dashboard.fxml"));
        stage.setTitle("BitScout");
        stage.getIcons().add(new Image("/at/bitscout/Images/BitscoutB_Transparent.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1048, 600));
        stage.show();
    }

    /** Creates new stage with the instruction of how Black Jack works*/
    public void startSettings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }

}