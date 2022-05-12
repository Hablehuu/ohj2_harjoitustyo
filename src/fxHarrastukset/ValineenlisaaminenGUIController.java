package fxHarrastukset;


import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @author jaakko palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class ValineenlisaaminenGUIController implements ModalControllerInterface<String>{
      
	@FXML private TextField textValine;
    @FXML private TextField textKunto;
    private String valine = "";
    private String kunto = "";
    private String tiedot = "";
    
    @FXML
    void handlePeruuta() {
    	ModalController.closeStage(textValine);
    	return;
    }

	
	/**
	 * Annetaan välineen tiedot tekstilaatikoista ja suljetaan ikkuna
	 */
    @FXML
    String handleTallenna() {
        valine = textValine.getText();
        kunto = textKunto.getText();
        ModalController.closeStage(textValine);
        return valine;
    }

    @Override
    public String getResult() {
       StringBuilder yhdessa = new StringBuilder(valine + '|' + kunto);
       tiedot = yhdessa.toString();
        return tiedot;
    }

    @Override
    public void handleShown() {
       textValine.requestFocus();
        
    }

    @Override
    public void setDefault(String oletus) {
        textValine.setText(oletus);
        
    }
    /**
     * @param modalityStage mille ollaan mmodaalisia
     * @param oletus välineen oletus nimi
     * @return välineen nimi ja kunto
     */
    public static String valineenLisaaminen(Stage modalityStage, String oletus) 
    {
        return ModalController.showModal(
                ValineenlisaaminenGUIController.class.getResource("ValineenlisaaminenGUIView.fxml"),
                "testi", modalityStage, oletus);
    }
    
}