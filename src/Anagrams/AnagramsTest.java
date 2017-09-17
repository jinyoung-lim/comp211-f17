package Anagrams;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class dedicated to test Anagrams class.
 * Created by Daniel Lim and JJ Lim on 9/17/17.
 */
public class AnagramsTest {
    @Test
    public void testAnagrams() {
        Anagrams anagrams = new Anagrams();
        // The example cases are taken from www.english-for-students.com/One-Word-Anagrams.html
        assertTrue(anagrams.isAnagrams("Resistance", "Ancestries"));
        assertTrue(anagrams.isAnagrams("tea", "ate"));
        assertTrue(anagrams.isAnagrams("eat", "tea"));
        assertTrue(anagrams.isAnagrams("admirer", "Married"));
        assertTrue(anagrams.isAnagrams("saDDer", "DREADS"));
        assertTrue(anagrams.isAnagrams("te-a", "a-te"));

        assertFalse(anagrams.isAnagrams("admirer", "Marry"));
        assertFalse(anagrams.isAnagrams("below", "positive"));
        assertFalse(anagrams.isAnagrams("positive", "NE-GATIVE"));
        assertFalse(anagrams.isAnagrams("hi", "bye"));
    }

}