package sample;

/*
Autor: Lukas Bandalo
Beschreibung: Taschenrechner Layout mit Funktionnalität
Erstelldatum: 08.06.2020 irgendwann um 22 Uhr
Änderungsdatum: 12.06.2020
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public  class Calculator extends Application {
    private Button[] buttons = new Button[12];
    private Button[] buttonscalc = new Button[5];
    private Button[] buttonfunc = new Button[3];
    private byte zeile = 1;
    private byte spalte = 0;
    private Label label1;
    private boolean darkmode = false;

    //Für das ausrechnen des Labels als string

    private Object res = null;

    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        primaryStage.setTitle("Taschenrechner");
        Scene scene = new Scene(root, 240, 360, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Für die Ziffern 0-9
        for (int i = 0; i < buttons.length; i++) {
            String s = String.valueOf(i + 1);
            buttons[i] = new Button(s);
            switch (i) {
                case 9:
                    buttons[i] = new Button("Pi");
                    break;
                case 10:
                    buttons[i] = new Button("0");
                    break;
                case 11:
                    buttons[i] = new Button("=");
                    break;
            }
            buttons[i].setPrefSize(60, 60);
            buttons[i].setStyle("-fx-background-color: white;-fx-font-size: 15;");
            root.add(buttons[i], spalte, zeile);
            spalte++;
            if (spalte % 3 == 0) {
                spalte = 0;
                zeile++;
            }
        }

        //Für Addition, Subtraktion, Multiplikation und Division
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    buttonscalc[i] = new Button("<--");
                    break;
                case 1:
                    buttonscalc[i] = new Button("+");
                    break;
                case 2:
                    buttonscalc[i] = new Button("-");
                    break;
                case 3:
                    buttonscalc[i] = new Button("*");
                    break;
                case 4:
                    buttonscalc[i] = new Button("/");
                    break;
            }
            buttonscalc[i].setPrefSize(60, 60);
            buttonscalc[i].setStyle("-fx-background-color: white;-fx-font-size: 15;");
            root.add(buttonscalc[i], 4, i + 1);
        }

        //Andere Funktion Button
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    buttonfunc[i] = new Button("clear");
                    break;
                case 1:
                    buttonfunc[i] = new Button("Dark");
                    break;
                case 2:
                    buttonfunc[i] = new Button(".");
                    break;
            }
            buttonfunc[i].setPrefSize(60, 60);
            buttonfunc[i].setStyle("-fx-background-color: white;-fx-font-size: 15;");
            root.add(buttonfunc[i], i, 5);
        }

        /*
        Ende der Defnition von root und falls man weitere Objekte Einfügen will
         */
        label1 = new Label("");
        label1.setStyle("-fx-background-color: white;");
        label1.setFont(new Font("Arial", 20));
        label1.setPrefSize(240, 60);
        GridPane.setColumnSpan(label1, 5);
        root.add(label1, 0, 0);
        root.setAlignment(Pos.TOP_CENTER);

        //Darkmode
        buttonfunc[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (darkmode == false) {
                    scene.setFill(Color.BLACK);
                    root.setStyle("-fx-background-color: black;-fx-font-size: 15;");
                    for (int i = 0; i < 12; i++) {
                        buttons[i].setStyle("-fx-background-color: black;");
                        buttons[i].setTextFill(Color.WHITE);

                    }
                    for (int i = 0; i < 5; i++) {
                        buttonscalc[i].setStyle("-fx-background-color: black;");
                        buttonscalc[i].setTextFill(Color.WHITE);
                    }

                    for (int i = 0; i < 3; i++) {
                        buttonfunc[i].setStyle("-fx-background-color: black;");
                        buttonfunc[i].setTextFill(Color.WHITE);
                    }
                    //Ändert die Farbe nur zurück für darkmode auf background black
                    for (Button buttons : buttons) {
                        buttons.setOnMouseEntered(e -> buttons.setStyle("-fx-background-color: grey;"));
                        buttons.setOnMouseExited(e -> buttons.setStyle("-fx-background-color: black;"));
                    }

                    for (Button buttonscalc : buttonscalc) {
                        buttonscalc.setOnMouseEntered(e -> buttonscalc.setStyle("-fx-background-color: grey;"));
                        buttonscalc.setOnMouseExited(e -> buttonscalc.setStyle("-fx-background-color: black;"));
                    }

                    for (Button buttonfunc : buttonfunc) {
                        buttonfunc.setOnMouseEntered(e -> buttonfunc.setStyle("-fx-background-color: grey;"));
                        buttonfunc.setOnMouseExited(e -> buttonfunc.setStyle("-fx-background-color: black;"));
                    }

                    darkmode = true;
                } else {
                    scene.setFill(Color.WHITE);
                    root.setStyle("-fx-background-color: white;");
                    for (int i = 0; i < 12; i++) {
                        buttons[i].setStyle("-fx-background-color: white;");
                        buttons[i].setTextFill(Color.BLACK);
                    }
                    for (int i = 0; i < 5; i++) {
                        buttonscalc[i].setStyle("-fx-background-color: white;");
                        buttonscalc[i].setTextFill(Color.BLACK);
                    }

                    for (int i = 0; i < 3; i++) {
                        buttonfunc[i].setStyle("-fx-background-color: white;");
                        buttonfunc[i].setTextFill(Color.BLACK);
                    }
                    //Muss noch einmal definiert werden wenn darkmode auf true ist
                    for (Button buttons : buttons) {
                        buttons.setOnMouseEntered(e -> buttons.setStyle("-fx-background-color: grey;"));
                        buttons.setOnMouseExited(e -> buttons.setStyle("-fx-background-color: white;"));
                    }

                    for (Button buttonscalc : buttonscalc) {
                        buttonscalc.setOnMouseEntered(e -> buttonscalc.setStyle("-fx-background-color: grey;"));
                        buttonscalc.setOnMouseExited(e -> buttonscalc.setStyle("-fx-background-color: white;"));
                    }

                    for (Button buttonfunc : buttonfunc) {
                        buttonfunc.setOnMouseEntered(e -> buttonfunc.setStyle("-fx-background-color: grey;"));
                        buttonfunc.setOnMouseExited(e -> buttonfunc.setStyle("-fx-background-color: white;"));
                    }
                    darkmode = false;
                }
            }
        });

        //Button Hover effect
        for (Button buttons : buttons) {
            buttons.setOnMouseEntered(e -> buttons.setStyle("-fx-background-color: grey;"));
            buttons.setOnMouseExited(e -> buttons.setStyle("-fx-background-color: white;"));
        }

        for (Button buttonscalc : buttonscalc) {
            buttonscalc.setOnMouseEntered(e -> buttonscalc.setStyle("-fx-background-color: grey;"));
            buttonscalc.setOnMouseExited(e -> buttonscalc.setStyle("-fx-background-color: white;"));
        }

        for (Button buttonfunc : buttonfunc) {
            buttonfunc.setOnMouseEntered(e -> buttonfunc.setStyle("-fx-background-color: grey;"));
            buttonfunc.setOnMouseExited(e -> buttonfunc.setStyle("-fx-background-color: white;"));
        }

        /*
        Buttons mit allen Funktion und eingabe
         */

        //Button für Zahl 1
        EventHandler<MouseEvent> z1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 1);
            }
        };
        buttons[0].addEventFilter(MouseEvent.MOUSE_CLICKED, z1);

        //Button für Zahl 2
        EventHandler<MouseEvent> z2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 2);
            }
        };
        buttons[1].addEventFilter(MouseEvent.MOUSE_CLICKED, z2);

        //Button für Zahl 3
        EventHandler<MouseEvent> z3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 3);
            }
        };
        buttons[2].addEventFilter(MouseEvent.MOUSE_CLICKED, z3);

        //Button für Zahl 4
        EventHandler<MouseEvent> z4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 4);
            }
        };
        buttons[3].addEventFilter(MouseEvent.MOUSE_CLICKED, z4);

        //Button für Zahl 5
        EventHandler<MouseEvent> z5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 5);
            }
        };
        buttons[4].addEventFilter(MouseEvent.MOUSE_CLICKED, z5);

        //Button für Zahl 6
        EventHandler<MouseEvent> z6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 6);
            }
        };
        buttons[5].addEventFilter(MouseEvent.MOUSE_CLICKED, z6);

        //Button für Zahl 7
        EventHandler<MouseEvent> z7 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 7);
            }
        };
        buttons[6].addEventFilter(MouseEvent.MOUSE_CLICKED, z7);

        //Button für Zahl 8
        EventHandler<MouseEvent> z8 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 8);
            }
        };
        buttons[7].addEventFilter(MouseEvent.MOUSE_CLICKED, z8);

        //Button für Zahl 9
        EventHandler<MouseEvent> z9 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 9);
            }
        };
        buttons[8].addEventFilter(MouseEvent.MOUSE_CLICKED, z9);

        //Button für Pi
        EventHandler<MouseEvent> pi = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + Math.PI);
            }
        };
        buttons[9].addEventFilter(MouseEvent.MOUSE_CLICKED, pi);

        //Button für Zahl 0
        EventHandler<MouseEvent> z0 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + 0);
            }
        };
        buttons[10].addEventFilter(MouseEvent.MOUSE_CLICKED, z0);

        //Button = für alles ausrechnen
        EventHandler<MouseEvent> calc = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                res = eval(label1.getText());
                label1.setText(String.valueOf(res));

            }
        };
        buttons[11].addEventFilter(MouseEvent.MOUSE_CLICKED, calc);

        //Button um 1 zurück
        EventHandler<MouseEvent> back = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               label1.setText(label1.getText().substring(0, label1.getText().length()-1));
            }
        };
        buttonscalc[0].addEventFilter(MouseEvent.MOUSE_CLICKED, back);

        //Button für Addition
        EventHandler<MouseEvent> add = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               label1.setText(label1.getText()+" + ");
            }
        };
        buttonscalc[1].addEventFilter(MouseEvent.MOUSE_CLICKED, add);

        //Button für Subtrahieren
        EventHandler<MouseEvent> sub = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + " - ");
            }
        };
        buttonscalc[2].addEventFilter(MouseEvent.MOUSE_CLICKED, sub);

        //Button für Multiplizieren
        EventHandler<MouseEvent> multi = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            label1.setText(label1.getText() + " * ");
            }
        };
        buttonscalc[3].addEventFilter(MouseEvent.MOUSE_CLICKED, multi);

        //Button für Multiplizieren
        EventHandler<MouseEvent> div = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + " / ");
            }
        };
        buttonscalc[4].addEventFilter(MouseEvent.MOUSE_CLICKED, div);

        //Button für clear
        EventHandler<MouseEvent> clear = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText("");
            }
        };
        buttonfunc[0].addEventFilter(MouseEvent.MOUSE_CLICKED, clear);

        //Button für komma
        EventHandler<MouseEvent> com = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label1.setText(label1.getText() + ".");
            }
        };
        buttonfunc[2].addEventFilter(MouseEvent.MOUSE_CLICKED, com);

        /*
        Für Tastatur
         */
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {

            //Zaheln 0-9
            if (keyEvent.getCode() == KeyCode.DIGIT1 || keyEvent.getCode() == KeyCode.NUMPAD1){
                label1.setText(label1.getText()+1);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT2 || keyEvent.getCode() == KeyCode.NUMPAD2){
                label1.setText(label1.getText()+2);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT3 || keyEvent.getCode() == KeyCode.NUMPAD3){
                label1.setText(label1.getText()+3);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT4 || keyEvent.getCode() == KeyCode.NUMPAD4){
                label1.setText(label1.getText()+4);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT5 || keyEvent.getCode() == KeyCode.NUMPAD5){
                label1.setText(label1.getText()+5);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT6 || keyEvent.getCode() == KeyCode.NUMPAD6){
                label1.setText(label1.getText()+6);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT7 || keyEvent.getCode() == KeyCode.NUMPAD7){
                label1.setText(label1.getText()+7);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT8 || keyEvent.getCode() == KeyCode.NUMPAD8){
                label1.setText(label1.getText()+8);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT9 || keyEvent.getCode() == KeyCode.NUMPAD9){
                label1.setText(label1.getText()+9);
            }
            else if (keyEvent.getCode() == KeyCode.DIGIT0 || keyEvent.getCode() == KeyCode.NUMPAD0){
                label1.setText(label1.getText()+0);
            }
            //Für Komma
            else if (keyEvent.getCode() == KeyCode.COMMA || keyEvent.getCode() == KeyCode.DECIMAL){
                label1.setText(label1.getText()+".");
            }
            //Für die Grunrechnungen
            else if (keyEvent.getCode() == KeyCode.PLUS || keyEvent.getCode() == KeyCode.ADD){
                label1.setText(label1.getText()+" + ");
            }
            else if (keyEvent.getCode() == KeyCode.MINUS || keyEvent.getCode() == KeyCode.SUBTRACT){
                label1.setText(label1.getText()+" - ");
            }
            else if (keyEvent.getCode() == KeyCode.ASTERISK || keyEvent.getCode() == KeyCode.MULTIPLY){
                label1.setText(label1.getText()+" * ");
            }
            else if (keyEvent.getCode() == KeyCode.SLASH || keyEvent.getCode() == KeyCode.DIVIDE){      //Slash geht nicht ganz wie erwartet
                label1.setText(label1.getText()+" / ");
            }
            //Enter für das Berechnen also "="
            else if (keyEvent.getCode() == KeyCode.ENTER){
                Calculator.calc(label1);
            }
            //Für funktion clear
            else if (keyEvent.getCode() == KeyCode.BACK_SPACE){
                label1.setText(label1.getText().substring(0, label1.getText().length()-1));
            }
        });
}


    private static Object calc(Label label1){

        Object res = null;

            res = eval(label1.getText());
            label1.setText(String.valueOf(res));

        return res;
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }


            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
