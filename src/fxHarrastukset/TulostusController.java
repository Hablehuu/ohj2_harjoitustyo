package fxHarrastukset;


import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;



/**
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class TulostusController implements ModalControllerInterface<String>{
      
	@FXML private TextArea textTulostus;
	

    @FXML
    void handlePeruuta() {
    	ModalController.closeStage(textTulostus);
    }

    /**
     * Tulostaa ikkunassa n�kyv�t tiedot napin painalluksella
     */
    @FXML 
    private void handleTulosta() {
    	PrinterJob job = PrinterJob.createPrinterJob();
    	if (job != null && job.showPrintDialog(null)) {
    	    WebEngine webeng = new WebEngine();
    	    webeng.loadContent("<pre>" + textTulostus.getText() + "</pre>");
    	    webeng.print(job);
    	    job.endJob();
    	}
        
    }
    
    
    @Override
    public void setDefault (String oletus) {
    	textTulostus.setText(oletus);
    }
    
    
    @Override
    public void handleShown() {
    	textTulostus.requestFocus();
    }
	
    @Override
    public String getResult() {
    	return null;
    }
    
	
    /**
     * @return alue johon tulostetaan
     */
    public TextArea getTextArea() {
        return textTulostus;
    }
    
    
    /**
     * N�ytt�� mit� tulostetaan
     * @param tulostus tulostettava teksti
     * @return kontrolleri jolta voi pyyt�� lis�� tietoja
     */
    public static TulostusController tulosta(String tulostus) {
    	TulostusController tulostusCtrl = ModalController.showModeless(TulostusController.class.getResource("Tulostus.fxml"),
    	        "Tulostus", tulostus);
    	return tulostusCtrl;
    }
}