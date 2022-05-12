package fxHarrastukset;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tietorakenteet.Harrastus;
import tietorakenteet.KayttajaNimi;
import tietorakenteet.SailoException;
import tietorakenteet.Valine;
import fi.jyu.mit.fxgui.ListChooser;

/**
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class HarrastuksetGUIController implements Initializable{

    @FXML private TextField Haku;
    @FXML private TextField harrastusPaiva;
    @FXML private TextField harrastusSijainti;
    @FXML private TextField harrastusAloitus;
    @FXML private TextField harrastusKulut;
    @FXML private TextField harrastusViikko;
    @FXML private TextField harrastusKK;
    @FXML private TextField harrastusVuosi;
    @FXML private TextField textValMaara;
    @FXML private ListChooser<Harrastus> listHarrastukset;
    
    @FXML private ListChooser<Valine> listvalineet;
    @FXML private ListChooser<String> listkunto;
    @FXML private Button valitsin;
    private Harrastus valittu;
    private Valine kohdalla;
    private String kayttis = "";
    private KayttajaNimi kayttajanimi;
    
    private boolean luettu = false;
    private boolean hakuval = false;
    
    
    @Override
    public void initialize (URL url, ResourceBundle resourcebundle) {
    	try {
            alusta();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	
    @FXML
    void Tallenna() throws SailoException {
        tallenna();
    }


    @FXML void apua() {
        auta();
    }

    
	/**
	 * Ei tee mitään mutta ohjelma kaatuu ilman tätä jostain syystä
	 */
    @FXML
    void harrastuksenTiedot() {
    	//
    }
    
    
    /**
     * Haetaan harrastusten ja välineiden tiedot
     * @param event hiiren klikkauksesta
     */
    @FXML
    void haeTiedot() {
        if(listHarrastukset.getObjects().size() > 0) {
            valittu = listHarrastukset.getSelectedObject();
        }
        else return;
        lisaaTiedot();
        
        
    }
    


    /**
     * Avataan harrastusten tietojen muokkausikkuna
     * @param event hiiren klikkauksesta
     */
    @FXML
    void avaaMuokkausIkkuna() throws SailoException {  
        
        if(listHarrastukset.getObjects().size() > 0) {
            valittu = listHarrastukset.getSelectedObject();
        }
        else return;
        harMoukkaus();
        
         tallenna();
    }

	
	


    /**
     * Lisätään harrastus ohjelmaan
     * @param event napin painallus
     */
    @FXML
    void lisaaHarrastus() throws SailoException {
    	
        lisHarrastus();
    	tallenna();
    }   
    


    /**
     * Lis�t��n v�line harrastukselle
     */
    @FXML
    public void lisaaValine() {
        if(listHarrastukset.getObjects().size() > 0) {
            valittu = listHarrastukset.getSelectedObject();
        }     
        else {
            return;
        }
         lisValine();    
         
         textValMaara.setText("" + listvalineet.getObjects().size());
    }
	
    
	


    /**
     * Poistetaan valittu väline
     * @param event napin painallus
     */
    @FXML
    void poistaValine() {
        if(listvalineet.getObjects().size() > 0) {
            kohdalla = listvalineet.getSelectedObject();
        }
        else return;
        
        poistaVal(kohdalla);
        textValMaara.setText("" + listvalineet.getObjects().size());
    }

    
	/**
     * Poistetaan valittu harrastus
     * @param event napin painallus
     */
    @FXML
    void poistaHarrastus() {
        poistaHar();
    }
    
	/**
	 * Ei tee mitään mutta ohjelma kaatuu ilman tätä jostain syystä
	 */
    @FXML
    void poistu() {
        //
    }
	
	
	/**
     * Annetaan lisätietoja ohjelmasta
     * @param event napin painallus
     */
    @FXML
    void tietoa() {
    	ModalController.showModal(HarrastuksetGUIController.class.getResource("TietojaView.fxml"), "fxHarrastukset", null, "");
    }

    
	/**
	 * Avataan tulostusikkuna
	 */
    @FXML
    void tulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null);
        tulostaVal(tulostusCtrl.getTextArea());
    }

    
    @FXML
    void vaihdaKayttajaa() throws IOException {
        vaihdakayttajaa();
    }
    
	
	/**
	 * Haetaan harrastuksia tai välineitä hakupalkista riippuen valinnasta
	 */
    @FXML
    void hae()   {
    	String hakuEhto = Haku.getText();
        if(!hakuval) {
        listHarrastukset.clear();
        listvalineet.clear();
        listkunto.clear();
        haeEhdolla(hakuEhto);
        }
        else { 
        listvalineet.clear();
        listkunto.clear();
        haeVal(hakuEhto);
        }
    }
   
	/**
	 * Vaihdetaan haku napin teksitä ohjelmassa
	 */
    @FXML
    void vaihda() {

        if(hakuval) {
            hakuval = false;
            valitsin.setText("etsi välineitä");
        }
        else {
            hakuval = true;
            valitsin.setText("etsi harrastuksia");
        }
        
    }

//=========================================================
	
    /**
	 * Haetaan kirjoitettu harrastus/väline
     * @param hakuEhto regex jolla etsitään
     */
    public void haeEhdolla(String hakuEhto) {
         StringBuilder sb = new StringBuilder("");
        
        String regex = "";
        if(hakuEhto.equals("")) {
            regex = ".*";
        }
        else {
            sb.append(".*");
            sb.append(hakuEhto);
            sb.append(".*");
            regex = sb.toString(); 
        }
        
        for(int i = 0;i < kayttajanimi.getHarrastuksia();i++) {
            if(kayttajanimi.annaHar(i) != null && kayttajanimi.annaHar(i).getKayttaja().equals(kayttis) && kayttajanimi.annaHar(i).getHarrastus().matches(regex)) {
                listHarrastukset.add(kayttajanimi.annaHar(i).getHarrastus(),kayttajanimi.annaHar(i));
            }
        }
    }
	
    
    
    
    /**
     * lisää valitun harrastuksen tiedot näytölle
     * 
     */
    public void lisaaTiedot() {
        if(valittu == null) return;
        harrastusPaiva.setText( valittu.getPaiva());
        harrastusSijainti.setText( valittu.getSijainti());
        harrastusAloitus.setText( valittu.getAloitus());
        harrastusKulut.setText(Double.toString( valittu.getKulut()));
        harrastusViikko.setText(Integer.toString( valittu.getAikavk()));
        harrastusKK.setText(Integer.toString(valittu.getAikavk() * 4));
        harrastusVuosi.setText(Integer.toString(valittu.getAikavk() * 4 * 12));
         
        listvalineet.clear();
        listkunto.clear();
        textValMaara.setText("");
         int maara =  kayttajanimi.getValineet().getLkm();
       
        for(int i = 0; i < maara ;i++) {
            if(kayttajanimi.getValineet().etsi(valittu.getTunnus(),i) != null && kayttajanimi.getValineet().etsi(valittu.getTunnus(),i).getKayttaja().equals(kayttis) ) {
                listvalineet.add((kayttajanimi.getValineet().anna(i).getNimi()),kayttajanimi.getValineet().anna(i));
                listkunto.add(kayttajanimi.getValineet().anna(i).getKunto().toString());
                textValMaara.setText("" + listvalineet.getObjects().size());
            }
        } 
        
    }
	
    /**
     * avaa muokkausikkunan ja lisää tiedot näytölle 
     */
    public void harMoukkaus() {
        if(valittu == null) return;
        StringBuilder tiedot = new StringBuilder("");
        tiedot.append(harrastusPaiva.getText().toString() + '|');
        tiedot.append(harrastusSijainti.getText().toString() + '|');
        tiedot.append(harrastusAloitus.getText().toString() + '|');
        tiedot.append(harrastusKulut.getText().toString() + '|');
        tiedot.append(harrastusViikko.getText().toString() + '|');
        tiedot.append(harrastusKK.getText().toString() + '|');
        tiedot.append(harrastusVuosi.getText().toString() + '|');
        
        
        
        
        String harrastustiedot = MuokkausikkunaGUIController.muokkaa(null, tiedot.toString());
        if(harrastustiedot == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(harrastustiedot);
         harrastusPaiva.setText( Mjonot.erota(sb,'|'));
         harrastusSijainti.setText( Mjonot.erota(sb,'|'));
         harrastusAloitus.setText( Mjonot.erota(sb,'|'));
         harrastusKulut.setText( Mjonot.erota(sb,'|'));
         int tunnit =  Mjonot.erotaInt(sb,'|');
         String joku = Integer.toString(tunnit);
         harrastusViikko.setText(joku);
         harrastusKK.setText(joku = Integer.toString(tunnit*4));
         harrastusVuosi.setText(joku = Integer.toString(tunnit*4*12));
         valittu.setPaiva(harrastusPaiva.getText());
         valittu.setSijainti(harrastusSijainti.getText());
         valittu.setAloitus(harrastusAloitus.getText());
         valittu.setKulut(harrastusKulut.getText());
         valittu.setAikavk(harrastusViikko.getText());
         valittu.setAikakk(harrastusKK.getText());
         valittu.setAikavv(harrastusVuosi.getText());
        if(!(harrastustiedot.contentEquals(tiedot))) kayttajanimi.setMuutettu();
        
    }
    
    /**
     * luodaan uusi harrastus
     */
    public void lisHarrastus() {
        String nimi = LisaaharrastusGUIController.LisaaHar(null, "");
        if(nimi == null) return;
          Harrastus harrastus = new Harrastus(nimi);
            try {
                kayttajanimi.lisaa(harrastus);
            } catch (SailoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        listHarrastukset.add(harrastus.getHarrastus(),harrastus);
        kayttajanimi.setMuutettu();
        
    }
    
    
	/**
	 * Tekee tarvittavat alustukset
	 * @throws IOException jos tiedosto ei aukea
	 */
	public void alusta() throws IOException {
		listHarrastukset.clear();
		listvalineet.clear();
		//
		listHarrastukset.addSelectionListener(e -> naytaHarrastus());
	}
	
	
	/**
	 * asettaa otsikon ohjelmalle
	 * @param title teksti joka laitetaan ohjelman ylävasenkulmaan 
	 */
	public void setTitle(String title) {
		ModalController.getStage(Haku).setTitle(title);
	}
	
	
	/**
	 * Alustaa k�ytt�j�n lukemalla sen valitun nimisest� tiedostosta
	 * @param nimi teidosto josta k�ytt�j�n tiedot luetaan
	 * @throws IOException jos tiedosto ei aukea
	 */
	public void lueTiedosto(String nimi) throws IOException
	{
		kayttis = nimi;
		kayttajanimi.setKayttaja(nimi);
		setTitle("Kayttaja -" + kayttis);
		//kayttajanimi.nollaus();
		if(!luettu) {
		kayttajanimi.getHarrastukset().haeTiedostosta();
		kayttajanimi.getValineet().lueTiedostosta();
		luettu = true;
		}
		lisataan();
	}
	
	
	/**
	 * Kysyt��n tiedoston nimi (k�yttyj�n nimi) ja luetaan se
	 * @return true jos onnistui, false jos ei
	 * @throws IOException jos tiedoston avaaminen epäonnistuu
	 */
	public boolean avaa() throws IOException
	{
		String kayttajannimi = KayttajaGUIController.kayttajaNimi(null, kayttis);
		if (kayttajannimi == null) return false;
		
		lueTiedosto(kayttajannimi);
		return true;
	}
	
	
	/**
	 * Tarkistetaan onko tallennus tehty
	 * @return true jos sovelluksen voi sulkea
	 * @throws SailoException Joskus ei saada tiedostoja auki
	 */
	public boolean voikoSulkea() throws SailoException {
		tallenna();
		return true;
	}
	
	
	/**
	 * tallentaa muutokset
	 * @throws SailoException jos tallennus epäonnistuu 
	 */
	public void tallenna() throws SailoException {
		kayttajanimi.tallenna();
	}
	
	
	/**
	 * Poistetaan ohjelmasta valittu väline
	 * @param poistettava poistaa valitun välineen
	 */
	public void poistaVal(Valine poistettava) {
        if (poistettava == null)return; 
	    kayttajanimi.poistaVal(poistettava);
        listvalineet.clear();
        listkunto.clear();
         int maara =  kayttajanimi.getValineet().getLkm();
       
        for(int i = 0; i < maara ;i++) {
            if(kayttajanimi.getValineet().etsi(valittu.getTunnus(),i) != null && kayttajanimi.getValineet().etsi(valittu.getTunnus(),i).getKayttaja().equals(kayttis) ) {
                listvalineet.add((kayttajanimi.getValineet().anna(i).getNimi()),kayttajanimi.getValineet().anna(i));
                listkunto.add(kayttajanimi.getValineet().anna(i).getKunto().toString());
            }
        }
        kayttajanimi.setMuutettu();
    }
	
	
	/**
	 * luodaa uusi väline valittuun harrastukseen
	 */
	public void lisValine() {
	    if(valittu == null) return;
	    String tiedot = (ValineenlisaaminenGUIController.valineenLisaaminen(null, "testi"));
        if(tiedot == null || tiedot.equals("|")) return;
	    StringBuilder sb = new StringBuilder(tiedot);
        String nimi = Mjonot.erota(sb,'|');
        String kunto = Mjonot.erota(sb,'|');
        Valine uusi = new Valine(nimi);
        uusi.setKunto(kunto);
        if (nimi == null) return;
        try {
            kayttajanimi.lisaaVal(uusi);
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //lue(nimi);
        uusi.settunnusNum(valittu.getTunnus());
        listvalineet.add(uusi.getNimi(), uusi);
        listkunto.add(uusi.getKunto());
        kayttajanimi.setMuutettu();
        
    }
	
	/**
	 * Poistetaan harrastus
	 */
	public void poistaHar() {
        if(valittu == null) return;
	    kayttajanimi.poistaValineet(valittu.getTunnus());
	    kayttajanimi.poistaHar(valittu);
	    listHarrastukset.clear();
	    listvalineet.clear();
        listkunto.clear();
        lisataan();
	    kayttajanimi.setMuutettu();
	    
	        harrastusPaiva.clear();
	        harrastusSijainti.clear();
	        harrastusAloitus.clear();
	        harrastusKulut.clear();
	        harrastusViikko.clear();
	        harrastusKK.clear();
	        harrastusVuosi.clear();
	    
    }

	
	/**
	 * Nayttaa listasta valitun harrastuksen
	 */
	public void naytaHarrastus() {
		 valittu= listHarrastukset.getSelectedObject();
		if(valittu == null) return;
	}
	
	
	/**
	 * lisää tiedostosta haetut harrastukset näytölle
	 */
	public void lisataan() {
	    for(int i = 0;i < kayttajanimi.getHarrastuksia();i++) {
	        if(kayttajanimi.annaHar(i) != null && kayttajanimi.annaHar(i).getKayttaja().equals(kayttis)) {
	            listHarrastukset.add(kayttajanimi.annaHar(i).getHarrastus(),kayttajanimi.annaHar(i));
	        }
	    }
	}
	
	
	/**
	 * hakee harrastuksen tiedot listaan
	 * @param harNum harrastuksen numero, joka aktivoidaan haun jälkeen
	 */
	public void hae (int harNum) {
		listHarrastukset.clear();
		
		int index = 0;
		for (int i = 0; i < kayttajanimi.getHarrastuksia(); i++) {
			Harrastus harrastus = kayttajanimi.annaHar(i);
			if (harrastus.getTunnus() == harNum) index = i;
			if(kayttajanimi.annaHar(i).getKayttaja().contentEquals(kayttis)) {
			    listHarrastukset.add(harrastus.getHarrastus(), harrastus);
			}
		}
		
		listHarrastukset.setSelectedIndex(index);
	}
	
	/**
	 * Tyhjennetään tiedot ja avataan ohjelma uudella käyttäjällä
	 * @throws IOException virhe
	 */
	public void vaihdakayttajaa() throws IOException {
        
	    listHarrastukset.clear();
        listvalineet.clear();
        listkunto.clear();
	    avaa();     
    }
	
	
	/**
	 * ei tällä hetkellä käytössä
	 * luo uuden harrastuksen
	 */
	public void uusiHar() {
		Harrastus uusi = new Harrastus();
		
		
		try {
			kayttajanimi.lisaa(uusi);
		} catch (SailoException e) {
			Dialogs.showMessageDialog("Ongelmia uuden harrastuksen lisäämisessä");
			return;
		}
		
		hae(uusi.getTunnus());
	}
	
	
	/**
	 * asettaa käyttäjän nimen
	 * @param kayttajanimi käyttäjän antama nimi
	 */
	public void setKayttaja(KayttajaNimi kayttajanimi) {
		this.kayttajanimi = kayttajanimi;
		kayttajanimi.setKayttaja(kayttis);
		naytaHarrastus();
	}
	
	
	/**
	 * Tulostetaan harrastuksen ja sen välineen tiedot
	 * @param os tietovirta josta tulostetaan
	 * @param harrastus tulostettava harrastus
	 */
	public void tulosta (PrintStream os, Harrastus harrastus) {
	    os.println("-------------------------------------------------");
	    harrastus.tulosta(os);
	    
	    int maara =  kayttajanimi.getValineet().getLkm();
        
        for(int i = 0; i < maara ;i++) {
            if(kayttajanimi.getValineet().etsi(valittu.getTunnus(),i) != null && kayttajanimi.getValineet().etsi(valittu.getTunnus(),i).getKayttaja().equals(kayttis) ) {
                kayttajanimi.getValineet().anna(i).tulosta(os);
            }
        }
        os.println("-------------------------------------------------");
        
	}
	
	
	/**
	 * Tulostetaan listassa olevat harrastukset
	 * @param text alue johon tulostetaan
	 */
	public void tulostaVal (TextArea text) {
	    try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)){
	        os.println("Tulostetaan harrastukset");
	        for (Harrastus harrastus: listHarrastukset.getObjects()) {
	            tulosta(os, harrastus);
	        }
	    }
	}
	
	/**
	 * Avataan ohjelman suunnitelma selaimessa
	 */
	public void auta() {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/ht/palmjjxy");
			desktop.browse(uri);
		} catch (URISyntaxException e) {
			return;
		} catch (IOException e) {
			return;
		}
	}
	
	/**
	 * Haetaan välineitä hakuehdolla
	 * @param hakuEhto mitä välineitä haetaan
	 */
	 public void haeVal(String hakuEhto) {
	        StringBuilder sb = new StringBuilder("");
	        
	        String regex = "";
	        if(hakuEhto.equals("")) {
	            regex = ".*";
	        }
	        else {
	            sb.append(".*");
	            sb.append(hakuEhto);
	            sb.append(".*");
	            regex = sb.toString(); 
	        }
	        int maara =  kayttajanimi.getValineet().getLkm();
	        for(int i = 0; i < maara ;i++) {
	            if(kayttajanimi.getValineet().etsi(valittu.getTunnus(),i) != null && kayttajanimi.getValineet().etsi(valittu.getTunnus(),i).getKayttaja().equals(kayttis) && kayttajanimi.getValineet().etsi(valittu.getTunnus(),i).getNimi().matches(regex) ) {
	                listvalineet.add((kayttajanimi.getValineet().anna(i).getNimi()),kayttajanimi.getValineet().anna(i));
	                listkunto.add(kayttajanimi.getValineet().anna(i).getKunto().toString());
	            }
	        } 
	    }
}