package fxHarrastukset;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Sasu Ilmo, Jaakko Palm
 * @version 26.4.2019
 *
 */
public class Valine {
    private String kayttaja = "";
    private int tunnusNum = 0;
    private String nimi = "";
    private String kunto = "";
    
    
    
    /**
	 * Väliaikainen testipääohjelma, ei käytösssä
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }

	
    /**
     * oletus muodostaja
     */
    public Valine() {
        //oletusmuodostaja
    }

    /**
     * luodaan väline jolla on nimi
     * @param nimi välineen nimi
     */
    public Valine(String nimi) {
        this.nimi = nimi;
    }
    

    /**
     * palautetaan välineen nimi
     * @return nimi
     */
    public String getNimi() {
        return this.nimi;
    }

	
    /**
     * asetetaan välineelle nimi
     * @param nimi välineen nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }



    /**
     * palautetaan välineen kunto
     * @return välineen kunto
     */
    public String getKunto() {
        return kunto;
    }


    /**
     * asetetaan välineen kunto
     * @param kunto välineen kunto
     */
    public void setKunto(String kunto) {
        this.kunto = kunto;
    }
    
    
    /**
     * asetetaan tunnusnumero
     * @param numero tunnusnumero
     */
    public void settunnusNum(int numero){
        this.tunnusNum = numero;
    }
    /**
     * palautetaan tunnusnumero
     * @return tunnusnumero
     */
    public int gettunnusNum() {
        return tunnusNum;
    }
    
    /**
     * @return palautetaan käyttäjä
     */
    public String getKayttaja() {
        return kayttaja;
    }

    /**
     * @param kayttaja asetetaan käyttäjä
     */
    public void setKayttaja(String kayttaja) {
        this.kayttaja = kayttaja;
    }

	/**
	 * Pikotaan tiedot merkkijonosta
	 * @param tiedot merkkijono joka pilkotaan
	 */
    public void parse(String tiedot) {
        StringBuilder sb = new StringBuilder(tiedot);
        this.kayttaja = Mjonot.erota(sb, '|');
        this.settunnusNum(Mjonot.erota(sb, '|',0));
        this.setNimi(Mjonot.erota(sb, '|'));
        this.setKunto(Mjonot.erota(sb, '|'));
    }
    
	/**
	 * Tulostetaan välineen tiedot
	 * @param out streami johon tulostetaan
	 */
    public void tulosta (PrintStream out) {
        out.println("Harrastuksen v�lineet:");
        out.println("V�line: " + nimi + ",  kunto: " + kunto);
    }
    
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.kayttaja);
        sb.append('|');
        sb.append(Integer.toString(this.gettunnusNum()));
        sb.append('|');
        sb.append(this.getNimi());
        sb.append('|');
        sb.append(this.getKunto());
        return sb.toString();
    }
}
