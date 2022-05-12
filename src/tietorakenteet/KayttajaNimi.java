package tietorakenteet;

/**
 * @author Sasu Ilmo 
 * @author Jaakko Palm
 * @version 26.4.2019
 *
 */
public class KayttajaNimi {
    private String kayttaja = ""; 
	private  final Harrastukset harrastukset = new Harrastukset();
	private Valineet valineet = new Valineet();
	
	/**
	 * Haetaan harrastusten lukum��r�
	 * @return harrastuksien m��r�
	 */
	public int getHarrastuksia() {
		return harrastukset.getLkm();
	}
	
	
	/**
	 * Lis�t��n harrastus
	 * @param har lis�tt�v� harrastus
	 * @throws SailoException jos lisätään liikaa harrastuksia tulee virhe
	 */
	public void lisaa(Harrastus har) throws SailoException{
		har.setKayttaja(this.kayttaja);
	    harrastukset.lisaa(har);
	}
	/**
	 * @param val lisättävä väline
	 * @throws SailoException jos on liikaa välineitä
	 */
	public  void lisaaVal(Valine val) throws SailoException{
	    val.setKayttaja(this.kayttaja);
	    getValineet().lisaa(val);
    }
	
	
	/**
	 * vie tiedon harrastuksille ja välineille, että muutoksia on tehty
	 */
	public void setMuutettu() {
	    harrastukset.setMuutettu();
	    valineet.setMuutettu();
	}
	
	/**
	 * Palautaa i:n harrastuksen
	 * @param i monesko harrastus palautetaan
	 * @return viite i:n harrastukseen
	 * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
	 */
	public Harrastus annaHar (int i) throws IndexOutOfBoundsException{
		return harrastukset.anna(i);
	}

	/**
	 * asettaa itselleen, harrastuksille ja välineille käyttäjän
	 * @param kayttaja käyttäjän nimi
	 */
	public void setKayttaja(String kayttaja) {
	    this.kayttaja = kayttaja;
	    harrastukset.setKayttaja(kayttaja);
	    valineet.setKayttaja(kayttaja);
	}
	/**
	 * vie tiedon harrastuksille ja välineille, että kaikki pitää nollata
	 * ei käytössä
	 */
	public void nollaus() {
	    harrastukset.nollaus();
	    valineet.nollaus();
	}
	
    /**
     * palauttaa välineet
     * @return välineet
     */
    public Valineet getValineet() {
        return valineet;
    }

    /**
     * palauttaa harrastukset
     * @return harrastukset
     */
    public Harrastukset getHarrastukset() {
        return this.harrastukset;
    }

    
    /**
     * lähettää tiedon harrastuksille ja välineille että pitää tallentaa
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna() throws SailoException {
        harrastukset.talleta();
        valineet.talleta();
    }
    
    /**
     * asettaa välineet
     * @param valineet asetettavat välineet
     */
    public void setValineet(Valineet valineet) {
        this.valineet = valineet;
    }
    /**
     * palauttaa käyttäjän
     * @return käyttäjä
     */
    public String getKayttaja() {
        return this.kayttaja;
    }


    /**
     * poistaa valitun välineen
     * @param poistettava poistettava väline
     */
    public void poistaVal(Valine poistettava) {
        valineet.poista(poistettava);
        
    }


    /**
     * poistaa kaikki harrastuksen välineet
     * @param tunnus harrastuksen tunnus
     */
    public void poistaValineet(int tunnus) {  
        valineet.poistaKaikki(tunnus);
    }


    /**
     * kertoo harrastuksille, että harrastus pitää poistaa
     * @param poistettava poistettava harrastus
     */
    public void poistaHar(Harrastus poistettava) {
        harrastukset.poista(poistettava);
    }
    
}
