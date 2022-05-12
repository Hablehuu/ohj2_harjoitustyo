package fxHarrastukset;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class LisaaharrastusGUIController implements ModalControllerInterface<String>{
      
	@FXML private TextField textHarrastus;
	private String harrastus;
	
    @FXML
    void handleCancel() {
    	ModalController.closeStage(textHarrastus);
    }

    @FXML
    String handleOk() {    
        harrastus = textHarrastus.getText().toString();
        ModalController.closeStage(textHarrastus);
        return harrastus;
        
    }
    
    
    @Override
    public void setDefault (String oletus) {
    	textHarrastus.setText(oletus);
    	
    }
    
    
    @Override
    public void handleShown() {
    	textHarrastus.requestFocus();
    }
	
    @Override
    public String getResult() {
    	return harrastus;
    }
    
    
    /**
     * Luodaan dialogi jolla kysyt��n harrastusta ja palautetaan 
     * siihen kirjoitettu harrastus
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus Oletus harrastus (mieluiten tyhj�)
     * @return kirjoitettu harrastus paitsi jos painetaan peruuta sitten null
     */
    public static String LisaaHar(Stage modalityStage, String oletus) {
    	return ModalController.showModal(LisaaharrastusGUIController.class.getResource("LisaaharrastusGUIView.fxml"),
    			"harrastus", modalityStage, oletus);
    }
}