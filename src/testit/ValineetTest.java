package testit;
// Generated by ComTest BEGIN
import tietorakenteet.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.05.18 15:10:21 // Generated by ComTest
 *
 */
public class ValineetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa38 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testLisaa38() throws SailoException,CloneNotSupportedException {    // Valineet: 38
    Valineet valineet = new Valineet(); 
    Valine pallo = new Valine(), joukkuepuku = new Valine(); 
    assertEquals("From: Valineet line: 43", 0, valineet.getLkm()); 
    valineet.lisaa(pallo); assertEquals("From: Valineet line: 44", 1, valineet.getLkm()); 
    valineet.lisaa(joukkuepuku); assertEquals("From: Valineet line: 45", 2, valineet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista174 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testPoista174() throws SailoException,CloneNotSupportedException {    // Valineet: 174
    Valineet valineet = new Valineet(); 
    Valine pallo = new Valine(), joukkuepuku = new Valine(); 
    assertEquals("From: Valineet line: 179", 0, valineet.getLkm()); 
    valineet.lisaa(pallo); assertEquals("From: Valineet line: 180", 1, valineet.getLkm()); 
    valineet.lisaa(joukkuepuku); assertEquals("From: Valineet line: 181", 2, valineet.getLkm()); 
    valineet.poista(pallo); assertEquals("From: Valineet line: 182", 1, valineet.getLkm()); 
    valineet.poista(joukkuepuku); assertEquals("From: Valineet line: 183", 0, valineet.getLkm()); 
  } // Generated by ComTest END
}