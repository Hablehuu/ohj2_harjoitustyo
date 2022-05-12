package tietorakenteet;

import java.io.*;
import java.util.ArrayList;


/**
 * Harrastusten tiedosto, joka osaa lis?t? ja poistaa harrastuksia
 * 
 * @author jaakko palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 *
 */
public class Harrastukset {
	private static final int MAX_HARRASTUKSIA = 20;
	private int lkm = 0;
	private boolean muutettu = false;
	private String tiedNimi = "harrastukset.txt";
	private String kayttaja = "";
	private ArrayList<Harrastus> alkiot = new ArrayList<Harrastus>();
	
	
	/**
	 * Oletusmuodostaja
	 */
	public Harrastukset () {
		//Oletusmuodostaja
	}
	
	
	/**
	 * Lis?t??n harrastus
	 * @param harrastus lis?tt?v? harrastus
	 * @throws SailoException antaa virhee njos lis√§t√§√§n liikaa harrastuksia
	 * <pre name="test">
	 * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
	 * Harrastukset harrastukset = new Harrastukset();
	 * Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus();
	 * jalkapallo.rekisteroi(); koripallo.rekisteroi();
	 * harrastukset.getLkm() === 0;
	 * harrastukset.lisaa(jalkapallo);     harrastukset.getLkm() === 1;
	 * harrastukset.lisaa(koripallo);      harrastukset.getLkm() === 2;
	 * </pre>
	 */
	public void lisaa (Harrastus harrastus) throws SailoException{

		if (lkm >= alkiot.size()+1) throw new SailoException("liikaa alkitoita");
		alkiot.add(harrastus);
		harrastus.rekisteroi();
		lkm++;
	}
	
	
	/**
	 * Palauttaa viitteen haluttuun harrastukseen
	 * @param i mink? harrastuksen viite halutaan
	 * @return viite j?seneen, jonka indeksi on i
	 * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
	 */
	public Harrastus anna (int i) throws IndexOutOfBoundsException {
		if (i < 0 || lkm <= i)
			throw new IndexOutOfBoundsException("laiton indeksi" + i);
		return alkiot.get(i);
	}
	
	
	/**
	 * Haetaan harrastukset tiedostosta
	 * @throws IOException virhe jos hakeminen tiedostosta ei onnistunut
	 */
	public void haeTiedostosta() throws IOException {
	    
            BufferedReader fi = new BufferedReader(new FileReader(tiedNimi));
            String tiedot = "";
            try {
                while ( (tiedot = fi.readLine()) != null ) {
                    
                    if ( "".equals(tiedot)) continue;
                    Harrastus harrastus = new Harrastus();
                    harrastus.parse(tiedot); 
                     lisaa(harrastus);
                }
                
            } catch (IOException | SailoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                 fi.close();
            }
       
        }
	   
	
	/**
	 * Tulevaisuudessa luetaan tiedosto (varmaan turha)
	 * @param hakemisto tiedoston hakemisto
	 */
	public void lueTiedostosta(String hakemisto) {
		tiedNimi = hakemisto;
	}
	
	
	/**
	 * Tallennetaan harrastukset tiedostoon
	 * @throws SailoException virheilmoitus jos tallennuksessa tulee ongelma
	 */
	public void talleta () throws SailoException {
	    if ( !muutettu ) return;

        try ( PrintWriter fo = new PrintWriter(new FileWriter(tiedNimi)) ) {     
            for (int i = 0 ;i < lkm;i++) {
                if(alkiot.get(i) != null)  fo.println(alkiot.get(i).toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + tiedNimi + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
	}
	
	
	/**
	 * Muutetaan boolean true:ksi jolla tarkistetaan onko ohjelmaan 
	 * tehty muutoksia ennen tallennusta
	 */
	public void setMuutettu() {
	    muutettu = true;
	}
	
	
	/**
	 * Palauttaa k‰ytt‰j‰n nimen
	 * @return k‰ytt‰j‰n nimi
	 */
	public String getKayttaja() {
        return kayttaja;
    }

	
	/**
	 * Asetetaan k‰ytt‰j‰n nimi
	 * @param kayttaja asetettava nimi
	 */
    public void setKayttaja(String kayttaja) {
        this.kayttaja = kayttaja;
    }

    
	/**
	 * Tyhjennet√§√§n alkiot
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Harrastukset harrastukset = new Harrastukset();
     * Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus();
     * jalkapallo.rekisteroi(); koripallo.rekisteroi();
     * harrastukset.getLkm() === 0;
     * harrastukset.lisaa(jalkapallo);     harrastukset.getLkm() === 1;
     * harrastukset.lisaa(koripallo);      harrastukset.getLkm() === 2;
     * harrastukset.nollaus();             harrastukset.getLkm() === 0;
     * </pre>
     */
    public void nollaus() {
        for(int i = 0;i < alkiot.size();i++) {
            alkiot.set(i, null);
        }
        lkm = 0;
    }

	
    /**
	 * Palautetaan harrastuksien lukum??r?
	 * @return harrastuksien lukum??r?
	 */
	public int getLkm() {
		return lkm;
	}
	
	
	/**
	 * Testip??ohjelma harrastukset-luokalle
	 * @param args ei k?yt?ss?
	 */
	public static void main(String [] args) {
		//
	}

	
	/**
	 * Poistetaan valittu harrastus
	 * @param poistettava poistettava harrastus
	 * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Harrastukset harrastukset = new Harrastukset();
     * Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus();
     * jalkapallo.rekisteroi(); koripallo.rekisteroi();
     * harrastukset.getLkm() === 0;
     * harrastukset.lisaa(jalkapallo);     harrastukset.getLkm() === 1;
     * harrastukset.lisaa(koripallo);      harrastukset.getLkm() === 2;
     * harrastukset.poista(jalkapallo);     harrastukset.getLkm() === 1;
     * harrastukset.poista(koripallo);      harrastukset.getLkm() === 0;
     * </pre>
	 */
    public void poista(Harrastus poistettava) {
        for(int i = 0;i < lkm;i++) {
            if(alkiot.get(i).getKayttaja().equals(poistettava.getKayttaja()) && alkiot.get(i).getHarrastus().equals(poistettava.getHarrastus()) && alkiot.get(i).getTunnus() == poistettava.getTunnus()) {
                alkiot.set(i, alkiot.get(lkm-1));  //[i] = alkiot[lkm-1];
                alkiot.set(lkm-1, null);
                lkm--;
                break;
            }
        }
    }
}
