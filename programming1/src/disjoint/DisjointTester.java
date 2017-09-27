package disjoint;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * A class dedicated to test the class Disjoint's isDisjoint method.
 * Created by JJ Lim and Daniel Lim on 9/17/17.
 */
public class DisjointTester {
    @Test
    public void testDisjoint() {
        Disjoint disjoint = new Disjoint();
        // from the given example
        assertTrue(disjoint.isDisjoint(new int[] {3, 2, 5}, new int [] {1, 4, 6}));
        assertFalse(disjoint.isDisjoint(new int[] {3, 2, 5}, new int [] {1, 2, 3}));
        assertTrue(disjoint.isDisjoint(new int[] {-2, -1, 0}, new int [] {1, 2}));
        assertFalse(disjoint.isDisjoint(new int[] {1}, new int [] {2, 6, 8, 3, 1}));
        assertTrue(disjoint.isDisjoint(new int[] {}, new int [] {5, 4}));
        assertTrue(disjoint.isDisjoint(new int[] {}, new int [] {}));

        assertFalse(disjoint.isDisjoint(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int [] {1, 4, 6}));
        assertTrue(disjoint.isDisjoint(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, new int [] {11, 41, 61}));
        assertTrue(disjoint.isDisjoint(new int[] {-3, -2, -5}, new int [] {-1, -4, -6}));
        assertFalse(disjoint.isDisjoint(new int[] {-3, -2, -5}, new int [] {-1, -4, -6, -3}));
    }

}