package fxHarrastukset;

/**
 * @author Sasu Ilmo
 * @author Jaakko Palm
 * @version 14.5.2019
 *
 */
public class SailoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Poikkeuksen muodostaja
	 * @param viesti poikkeuksen viesti
	 */
	public SailoException (String viesti) {
		super(viesti);
	}
}
