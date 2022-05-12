package tietorakenteet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sasu Ilmo
 * @author jaakko palm
 * @version 26.4.2019
 *
 */
public class Valineet {
    private  int MAX_VALINEITA = 20;
    private  int lkm = 0;
    private  String tiedNimi = "valineet.txt";
    private  Valine alkiot[] = new Valine[MAX_VALINEITA];
    private boolean muutettu = false;
    private String kayttaja = "";
	
	
    /**
     * Oletusmuodostaja
     */
    public Valineet () {
        //Oletusmuodostaja
    }
    
    
    /**
     * Lisätään väline
     * @param valine lisättävä väline
     * @throws SailoException antaa virhee njos lisätään liikaa harrastuksia
     * @example
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Valineet valineet = new Valineet();
     * Valine pallo = new Valine(), joukkuepuku = new Valine();
     * valineet.getLkm() === 0;
     * valineet.lisaa(pallo); valineet.getLkm() === 1;
     * valineet.lisaa(joukkuepuku); valineet.getLkm() === 2;
     * </pre>
     */
    public void lisaa (Valine valine) throws SailoException{
        if (lkm >= alkiot.length) throw new SailoException("liikaa alkitoita");
        alkiot[lkm] = valine;
        
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen haluttuun harrastukseen
     * @param i minkä välineen viite halutaan
     * @return viite jäseneen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Valine anna (int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("laiton indeksi" + i);
        return alkiot[i];
    }
    
    
    /**
     * @param tunnusNumero numero jonka perusteella etsitään varusteita
     * @param i indeksi jossa ollaan
     * @return palauttaa varusteen jos se on indeksissä muuten null
     */
    public Valine etsi(int tunnusNumero,int i) {
        if(alkiot[i].gettunnusNum() == tunnusNumero) return alkiot[i];
        return null;
    }
    
    /**
     * Luetaan tiedosto
     * @throws IOException jos jokin menee pieleen
     */
    public void lueTiedostosta() throws IOException {
        BufferedReader fi = new BufferedReader(new FileReader(tiedNimi));
        String tiedot = "";
        try {
            while ( (tiedot = fi.readLine()) != null ) {
                
                if ( "".equals(tiedot)) continue;
                Valine valine = new Valine();
                valine.parse(tiedot); 
                lisaa(valine);
            }
            
        } catch (IOException | SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            fi.close();
        }
   
    }
    
    
    
    /**
     * Tallentaa välineet tiedostoon
     * @throws SailoException jos tiedosto ei aukea
     */
    public void talleta () throws SailoException {
        if ( !muutettu ) return;

        try ( PrintWriter fo = new PrintWriter(new FileWriter("valineet.txt")) ) {
            
           
            for (int i = 0 ;i < lkm;i++) {
                if(alkiot[i] != null)  fo.println(alkiot[i].toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + tiedNimi + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Palautetaan välineiden lukumäärä
     * @return harrastuksien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    /**
     * asettaa muutetun niin ohjelma tietää, että välineitä on muutettu
     */
    public void setMuutettu() {
        this.muutettu = true;
    }
    
    /**
     * @return palauttaa käytäjän
     */
    public String getKayttaja() {
        return kayttaja;
    }


    /**
     * @param kayttaja asettaa käyttäjän
     */
    public void setKayttaja(String kayttaja) {
        this.kayttaja = kayttaja;
    }


    /**
     * Testipääohjelma harrastukset-luokalle
     * @param args ei käytössä
     */
    public static void main(String [] args) {
        //
    }


    /**
     * poistaa välineen 
     * @param poistettava väline joka poistetaan 
     * @example
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Valineet valineet = new Valineet();
     * Valine pallo = new Valine(), joukkuepuku = new Valine();
     * valineet.getLkm() === 0;
     * valineet.lisaa(pallo); valineet.getLkm() === 1;
     * valineet.lisaa(joukkuepuku); valineet.getLkm() === 2;
     * valineet.poista(pallo); valineet.getLkm() === 1;
     * valineet.poista(joukkuepuku); valineet.getLkm() === 0;
     * </pre>
     */
    public void poista(Valine poistettava) {
        for(int i = 0;i < lkm;i++) {
            if(alkiot[i].getKayttaja().equals(poistettava.getKayttaja()) && alkiot[i].getNimi().equals(poistettava.getNimi()) &&alkiot[i].gettunnusNum() == poistettava.gettunnusNum()) {
                alkiot[i] = alkiot[lkm-1];
                alkiot[lkm-1] = null;
                lkm--;
                break;
            }
        }
        
    }

    
	/**
	 * Tyhjennetään välineet
	 */
    public void nollaus() {
        for(int i = 0;i < alkiot.length;i++) {
            alkiot[i] = null;
        }
        lkm = 0;
    }

    
    /**
     * poistaa harrastuksen kaikki välineet
     * @param tunnus harrastuksen joka poistetaan tunnus
     */
    public void poistaKaikki(int tunnus) {
        int i = 0;
        while(i < lkm) {
            if(alkiot[i].gettunnusNum() == tunnus && alkiot[i].getKayttaja().equals(kayttaja)) {
                alkiot[i] = alkiot[lkm-1];
                alkiot[lkm-1] = null;
                lkm--;
            }
            else i++;
        }
    }
}
