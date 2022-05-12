package tietorakenteet;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * 
 * @author Jaakko Palm
 * @author Sasu Ilmo
 * @version 26.4.2019
 * 
 */
public class Harrastus {
	private String kayttaja = "";
	private int tunnusNum = 0;
	private String harNimi = " ";
	private String paiva = " ";
	private String sijainti = " ";
	private String aloituspvm = " ";
	private double kulut = 0.0;
	private int aikavk = 0;
	private int aikakk = 0;
	private int aikavv = 0;
	private static int seuraavaNumero = 1;
	
	
	/**
	 * harrastuksen peruskonstruktori
	 */
	public Harrastus() {
	     tunnusNum = 0;
	     harNimi = " ";
	    paiva = " ";
	     sijainti = " ";
	     aloituspvm = " ";
	     kulut = 0.0;
	     aikavk = 0;
	     aikakk = 0;
	     aikavv = 0;
	}
	
	
	/**
	 * harrastus konstruktori jolle viedän harrastuken nimi
	 * @param nimi harrastuksen nimi
	 */
	public Harrastus(String nimi) {
		harNimi = nimi;
		paiva = "";
	    sijainti = "";
	    aloituspvm = "";
	    kulut = 0.0;
	    aikavk = 0;
	    aikakk = 0;
	    aikavv = 0;
	    
	}
	
	
	/**
	 * asettaa harrastukselle tunnuksen
	 * @param a tunnus joka asetetaan
	 */
	public void setTunnus(int a) {
	    this.tunnusNum = a;
	}
	
	
	/**
	 * palauttaa harrastuksen tunnuksen
	 * @return harrastuksen tunnus
	 */
	public int getTunnus() {
		return this.tunnusNum;
	}
	
	
	/**
	 * Palautetaan harrastuksen nimi
	 * @return harrastuksen nimi
	 */
	public String getHarrastus () {
		return harNimi;
	}
	
	
	/**
	 * asettaa harrastukselle nimen
	 * @param a nimi joka annetaan
	 */
	public void setNimi(String a) {
	    this.harNimi = a; 
	}
	
	
	/**
	 * palauttaa harrastuksen päivän
	 * @return harrastuksen päivä
	 */
	public String getPaiva() {
		return paiva;
	}
	
	
	/**
	 * palauttaa harrastuksen sijainnin
	 * @return sijainti 
	 */
	public String getSijainti() {
		return sijainti;
	}
	
	
	/**
	 * palauttaa harrastuksen 
	 * @return aloitus
	 */
	public String getAloitus() {
		return aloituspvm;
	}
	
	
	/**
	 * palauttaa harrastuksen kulut
	 * @return kulut
	 */
	public double getKulut() {
		return kulut;
	}

	
	/**
	 * paljon aikaa kuluu viikossa
	 * @return aikaa viikossa
	 */
	public int getAikavk() {
		return aikavk;
	}
	
	
	/**
	 * palauttaa kenelle käyttäjälle harrastus kuuluu
	 * @return käyttäjä
	 */
	public String getKayttaja() {
	    return this.kayttaja;
	}
	
	
	/**
	 * asettaa harrastukselle käyttäjän
	 * @param kayttaja asetettaja käyttäjä
	 */
	public void setKayttaja(String kayttaja) {
	    this.kayttaja = kayttaja;
	}
	
	
	/**
	 * rekisteröi harrastuksen
	 */
	public void rekisteroi () {
		this.tunnusNum = seuraavaNumero++;
	}
	
	 
    /**
     * asettaa harrastuksen päivän
     * @param a päivä
     */
    public void setPaiva(String a) {
         paiva = a;
    }
    
    
    /**
     * asettaa harrastuksen sijainnin
     * @param a sijainti
     */
    public void setSijainti(String a) {
        sijainti = a;
    }
    
    
    /**
     * asettaa harrastuksen aloituksen
     * @param a aloitus
     */
    public void setAloitus(String a) {
        aloituspvm = a;
    }
    
    
    /**
     * asettaa harrastuksen kulut
     * @param a kulut
     * 
     */
    public void setKulut(String a) {
         kulut = Double.parseDouble(a);
    }

    
    /**
     * asettaa viikottainsen ajan
     * @param a aika viikossa
     */
    public void setAikavk(String a) {
         aikavk = Integer.parseInt(a);
    }
    
    
    /**
     * asettaa ajan kuukaudessa
     * @param a aika kuukaudessa
     */
    public void setAikakk(String a) {
        aikakk = Integer.parseInt(a);
    }
    
    
    /**
     * asettaa ajan vuodessa
     * @param a aika vuodessa
     */
    public void setAikavv(String a) {
        aikavv = Integer.parseInt(a);
   }
    
    
	/**
	 * asettaa harrastuksen tiedot harrastukselle
	 * @param tiedot harrastuksen tiedot
	 */
	public void parse(String tiedot) {
	    StringBuilder sb = new StringBuilder(tiedot);
        	    
	    this.kayttaja = Mjonot.erota(sb,'|');
	    this.setTunnus( Mjonot.erota(sb,'|',0));
	    this.setNimi(Mjonot.erota(sb,'|'));
        this.setPaiva( Mjonot.erota(sb,'|'));
        this.setSijainti( Mjonot.erota(sb,'|'));
        this.setAloitus( Mjonot.erota(sb,'|'));
        this.kulut = Mjonot.erota(sb,'|',0.0);
        this.aikavk = Mjonot.erotaInt(sb,'|');
	}
	
	
	/**
	 * Palauttaa harrastuksen tiedot merkkijonona.
	 * @return tiedot eroteltuna tolppamerkeill�
	 * @example
	 * <pre name="test">
	 * Harrastus harrastus = new Harrastus();
	 * harrastus.parse("Teppo|1|jalkapallo|tiistai|kentt�|11.7.2008|20.0|4");
	 * harrastus.toString().startsWith("Teppo|1|jalkapallo") === true;
	 * </pre>
	 */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.kayttaja);
        sb.append('|');
        sb.append(this.tunnusNum);
        sb.append('|');
        sb.append(this.harNimi);
        sb.append('|');
        sb.append(this.paiva);
        sb.append('|');
        sb.append(this.sijainti);
        sb.append('|');
        sb.append(this.aloituspvm);
        sb.append('|');
        sb.append(this.kulut);
        sb.append('|');
        sb.append(this.aikavk);
        
        return sb.toString();
    }
	
	
	/**
	 * Tulostetaan harrastuksen tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta (PrintStream out) {
		out.println(harNimi);
		out.println(paiva);
		out.println(sijainti);
		out.println(aloituspvm);
		out.println(kulut + "�/kk");
		out.println(aikavk + "h/viikossa");
		out.println(aikakk + "h/kuukaudessa");
		out.println(aikavv + "h/vuodessa");
	} 
	
	
	/**
	 * Testip��ohjelma harrastus-luokalle
	 * @param args ei k�yt�ss�
	 */
	public static void main(String [] args) {
		//
		
	}
}
