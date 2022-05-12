package fxHarrastukset;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 12.2.2019
 *
 */
public class KayttajaGUIController implements ModalControllerInterface<String>{
      
	@FXML private TextField kayttaja;
	private String kayttis = null;

    @FXML void handleOk() {
    	kayttis = kayttaja.getText();
    	
    	ModalController.closeStage(kayttaja);
    }
    
    
    @Override
    public String getResult() {
    	return kayttis;
    }
	
    
    @Override
    public void setDefault(String oletus) {
    	kayttaja.setText(oletus);
    }
    
    
    @Override
    public void handleShown() {
    	kayttaja.requestFocus();
    }
    
    
	/**
	 * Luodaan k�ytt�j�n kysymisdialogi ja palautetaan siihen kirjoitettu nimi
	 * @param modalityStage mille ollaan modaalisia, null = sovellukselle
	 * @param oletus oletus nimi
	 * @return kirjoitettu nimi
	 */
	public static String kayttajaNimi(Stage modalityStage, String oletus) 
	{
		return ModalController.showModal(
				KayttajaGUIController.class.getResource("KayttajaGUIView.fxml"),
				"kayttaja", modalityStage, oletus);
	}
}