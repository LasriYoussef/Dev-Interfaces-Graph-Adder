package fr.afpa;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonType;


public class Adder extends Application {
    private TextArea textArea;
    private StringBuilder expressionBuilder;

    private boolean isFirstNumber = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        expressionBuilder = new StringBuilder();

        // Set up the user interface
        stage.setTitle("Adder");

        // TextArea within a ScrollPane
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);

        // Grid for number buttons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // la ligne suivante permet d'afficher ou non la grille du GridPane
        gridPane.setGridLinesVisible(true);
        gridPane.setStyle("-fx-background-color: red;");

        for (int i = 0; i <= 9; i++) {
            Button button = new Button(String.valueOf(i));
            button.setPrefSize(50, 50);

            // ajout d'une action lors du clic sur un bouton
            button.setOnAction(e -> addNumber(button.getText()));
            
            int row = i / 5;
            int col = i % 5;
            gridPane.add(button, col, row);
        }

        // Buttons for Calculate and Clear
        Button buttonCalculate = new Button("Calculate");
        buttonCalculate.setPrefSize(100, 50);
        buttonCalculate.setOnAction(e -> calculate());

        Button buttonClear = new Button("Clear");
        buttonClear.setPrefSize(100, 50);
        buttonClear.setOnAction(e -> clear());

        Button buttonQuit = new Button("Quit");
        buttonQuit.setPrefSize(100, 50);
        buttonQuit.setOnAction(e -> Platform.exit());

        HBox buttonActions = new HBox(10, buttonCalculate, buttonClear, buttonQuit);
        buttonActions.setPadding(new Insets(10, 0, 0, 0));
        buttonActions.setStyle("-fx-background-color: orange;"); 
        buttonActions.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, scrollPane, gridPane, buttonActions);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: purple;"); 

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ajoute un nombre à la zone de texte
     * @param number Le nombre à ajouter
     */
    private void addNumber(String number) {

        if (isFirstNumber) {
            expressionBuilder.append(number);
            isFirstNumber = false;
        } else {
            expressionBuilder.append(" + ").append(number);
        }
        textArea.setText(expressionBuilder.toString());
    }

    private void calculate() {
        if (expressionBuilder.length() > 3) {
            // Remove the last " + "
            try {
                // récupération d'une chaîne de caractères (objet de la classe String) à partir d'un StringBuilder
                String expression = expressionBuilder.toString();
                String[] expressionParts = expression.split("=");

                // faire le traitement sur la dernière partie
                String expressionPart = expressionParts[expressionParts.length - 1];

                String[] numbers = expressionPart.split(" \\+ ");
                int sum = 0;
                for (String number : numbers) {
                    sum += Integer.parseInt(number.trim());
                }
                expressionBuilder.append(" = " + sum);
                textArea.setText(expressionBuilder.toString());
            } catch (NumberFormatException e) {
                textArea.setText("Format error!");
                return;
            }
        }
    }

    private void clear() {
        // Confirmation pop-up
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear the text area?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            expressionBuilder.setLength(0);
            textArea.setText("");
        }
    }
    private void confirmQuit() {
        // Confirmation pop-up
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?", 
        ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

} // Fin de la classe "Adder"
