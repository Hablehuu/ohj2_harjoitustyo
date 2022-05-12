package testit;
// Generated by ComTest BEGIN
import tietorakenteet.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.05.18 14:39:27 // Generated by ComTest
 *
 */
public class HarrastuksetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa36 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testLisaa36() throws SailoException,CloneNotSupportedException {    // Harrastukset: 36
    Harrastukset harrastukset = new Harrastukset(); 
    Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus(); 
    jalkapallo.rekisteroi(); koripallo.rekisteroi(); 
    assertEquals("From: Harrastukset line: 42", 0, harrastukset.getLkm()); 
    harrastukset.lisaa(jalkapallo); assertEquals("From: Harrastukset line: 43", 1, harrastukset.getLkm()); 
    harrastukset.lisaa(koripallo); assertEquals("From: Harrastukset line: 44", 2, harrastukset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testNollaus154 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testNollaus154() throws SailoException,CloneNotSupportedException {    // Harrastukset: 154
    Harrastukset harrastukset = new Harrastukset(); 
    Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus(); 
    jalkapallo.rekisteroi(); koripallo.rekisteroi(); 
    assertEquals("From: Harrastukset line: 160", 0, harrastukset.getLkm()); 
    harrastukset.lisaa(jalkapallo); assertEquals("From: Harrastukset line: 161", 1, harrastukset.getLkm()); 
    harrastukset.lisaa(koripallo); assertEquals("From: Harrastukset line: 162", 2, harrastukset.getLkm()); 
    harrastukset.nollaus(); assertEquals("From: Harrastukset line: 163", 0, harrastukset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista195 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testPoista195() throws SailoException,CloneNotSupportedException {    // Harrastukset: 195
    Harrastukset harrastukset = new Harrastukset(); 
    Harrastus jalkapallo = new Harrastus(), koripallo = new Harrastus(); 
    jalkapallo.rekisteroi(); koripallo.rekisteroi(); 
    assertEquals("From: Harrastukset line: 201", 0, harrastukset.getLkm()); 
    harrastukset.lisaa(jalkapallo); assertEquals("From: Harrastukset line: 202", 1, harrastukset.getLkm()); 
    harrastukset.lisaa(koripallo); assertEquals("From: Harrastukset line: 203", 2, harrastukset.getLkm()); 
    harrastukset.poista(jalkapallo); assertEquals("From: Harrastukset line: 204", 1, harrastukset.getLkm()); 
    harrastukset.poista(koripallo); assertEquals("From: Harrastukset line: 205", 0, harrastukset.getLkm()); 
  } // Generated by ComTest END
}