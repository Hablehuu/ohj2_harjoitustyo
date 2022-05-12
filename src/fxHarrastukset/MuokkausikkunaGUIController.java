package fxHarrastukset;


import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @author jaakko palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class MuokkausikkunaGUIController implements ModalControllerInterface<String>{


    @FXML private TextField textpaiva;
    @FXML private TextField textsijainti;
    @FXML private TextField textaloitus;
    @FXML private TextField textkulut;
    @FXML private TextField textviikko;
    @FXML private TextField textkuukausi;
    @FXML private TextField textvuosi;
    private String vanha = "";
    private String tmp;
    
	
	@FXML
	String handlePeruuta() {
	    String a = vanha;
    	ModalController.closeStage(textpaiva);
    	return a;
    }

	/**
	 * Tallennetaan tiedot String-muodossa
	 */
    @FXML
    void handleTallenna() {
    	StringBuilder tiedot = new StringBuilder("");
    	tiedot.append(textpaiva.getText().toString() + '|');
    	tiedot.append(textsijainti.getText().toString() + '|');
    	tiedot.append(textaloitus.getText().toString() + '|');
    	tiedot.append(textkulut.getText().toString() + '|');
    	tiedot.append(textviikko.getText().toString() + '|');
    	tmp =  tiedot.toString();
    	ModalController.closeStage(textpaiva);
    }
    

    @Override
    public String getResult() {
      return tmp;
    }

    @Override
    public void handleShown() {
       textpaiva.requestFocus();
    }

	/**
	 * Oikeastaan parse toisella nimellä
	 */
    @Override
    public void setDefault(String oletus) {
        StringBuilder sb = new StringBuilder(oletus);
        textpaiva.setText( Mjonot.erota(sb,'|'));
        textsijainti.setText( Mjonot.erota(sb,'|'));
        textaloitus.setText( Mjonot.erota(sb,'|'));
        textkulut.setText( Mjonot.erota(sb,'|'));
        textviikko.setText(Mjonot.erota(sb,'|'));
        textkuukausi.setText(Mjonot.erota(sb,'|'));
        textvuosi.setText(Mjonot.erota(sb,'|'));
        vanha = oletus;
    }
    
    /**Luodaan dialogi jolla muokataa valittua harrastusta
    * @param modalityStage mille ollaan modaalisia, null = sovellukselle
    * @param oletus vanhat tiedot
    * @return harrastuksen tiedot ja jos cancellataan niin vanhat tiedot
    */
    public static String muokkaa (Stage modalityStage, String oletus) {
    	return ModalController.showModal(MuokkausikkunaGUIController.class.getResource("MuokkausikkunaGUIView.fxml"),
    			"tiedot", modalityStage, oletus);
    }
}