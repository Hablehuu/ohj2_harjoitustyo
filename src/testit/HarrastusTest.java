package testit;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import tietorakenteet.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.05.18 15:00:18 // Generated by ComTest
 *
 */
public class HarrastusTest {



  // Generated by ComTest BEGIN
  /** testToString254 */
  @Test
  public void testToString254() {    // Harrastus: 254
    Harrastus harrastus = new Harrastus(); 
    harrastus.parse("Teppo|1|jalkapallo|tiistai|kentt�|11.7.2008|20.0|4"); 
    assertEquals("From: Harrastus line: 257", true, harrastus.toString().startsWith("Teppo|1|jalkapallo")); 
  } // Generated by ComTest END
}