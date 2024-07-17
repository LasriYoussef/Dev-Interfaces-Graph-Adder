package fr.afpa;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * JavaFX App affichage d'un Hello World
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        // Instanciations
        Label label = new Label("Hello World !");
        Button button = new Button("Quit");
        VBox vbox = new VBox(label, button);

        // Settings
        vbox.setAlignment(Pos.CENTER);

        button.setOnAction(value -> {
            label.setText("Goodbye World ! ");
            // créer une PauseTransition de 3 secondes
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            // définition de l'action suite à la pause
            delay.setOnFinished(e -> stage.close());
            // démarrer la pause
            delay.play();
        });

        // Assemblage
        Scene scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

}