package fxHarrastukset;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import tietorakenteet.KayttajaNimi;
import tietorakenteet.SailoException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 12.2.2019
 *
 */
public class HarrastuksetMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("HarrastuksetGUIView.fxml"));
            final Pane root = ldr.load();
            final HarrastuksetGUIController harrastuksetCtrl = (HarrastuksetGUIController) ldr.getController();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("harrastukset.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Harrastukset");
            
            
            primaryStage.setOnCloseRequest((event) -> {
                    try {
                        if ( !harrastuksetCtrl.voikoSulkea() ) event.consume();
                    } catch (SailoException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
             });
            
            KayttajaNimi kayttajanimi = new KayttajaNimi();
            harrastuksetCtrl.setKayttaja(kayttajanimi);
            
            
            if(!harrastuksetCtrl.avaa()) Platform.exit();
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * K�ytt�liittym�n k�ynnistys
     * @param args komentorivin parametrit
     */
    public static void main(String[] args) {
        launch(args);
    }
}