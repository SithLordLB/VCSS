package at.bitscout.helper;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;

/**
 * @author Bandalo
 * @version 1.3
 * Description: This class is a container for the hover effect on a point of the graph, it lets a label appear
 */

/** a node which displays a value on hover, but is otherwise empty */
public class HoveredThresholdNode extends StackPane{
    private DecimalFormat df = new DecimalFormat("####0.00");       //sets a double to 2 decimal
    private Label label;

    //Constructor
    public HoveredThresholdNode(String priorValue, double value) {
        //sets the label box
        setPrefSize(10, 10);

        //returns the label with the right configs and value
        label = createDataThresholdLabel(Integer.parseInt(priorValue), value);

        //when the mouse hovers on a point on the graph, then the label shows
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                getChildren().setAll(label);
                setCursor(Cursor.NONE);
                toFront();
            }
        });

        //if the mouse exit it, then the label disappears
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
                setCursor(Cursor.CROSSHAIR);
            }
        });
    }

    //returns a label and sets it configs and text
    private Label createDataThresholdLabel(int priorValue, double value) {
        label = new Label(df.format(value) + "");
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        if (priorValue == 0) {
            label.setTextFill(Color.DARKGRAY);
        } else if (value > priorValue) {
            label.setTextFill(Color.FORESTGREEN);
        } else {
            label.setTextFill(Color.FIREBRICK);
        }

        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return label;
    }
}

