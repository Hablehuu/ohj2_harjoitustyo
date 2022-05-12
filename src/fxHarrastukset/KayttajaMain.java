package fxHarrastukset;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author admin
 * @version 29.1.2019
 *
 */
public class KayttajaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("KayttajaGUIView.fxml"));
            final Pane root = ldr.load();
            //final KayttajaGUIController kayttajaCtrl = (KayttajaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("kayttaja.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kayttaja");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}