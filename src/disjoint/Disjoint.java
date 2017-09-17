package disjoint;

/**
 * This class is dedicated to determine if two given arrays of integers are disjoint (i.e. have no elements in common).
 * Created by JJ Lim and Daniel Lim on 9/16/17.
 */
public class Disjoint {
    /**
     * Constructor for this class.
     */
    public Disjoint() {

    }

    /**
     * Determines if the two given arrays are disjoint (i.e. have no elements in common).
     * @param array1 an array of integers
     * @param array2 an array of integers
     * @return
     */
    public boolean isDisjoint(int [] array1, int [] array2) {

        for(int i = 0, n = array1.length; i < n; i++) {
            for (int j = 0, m = array2.length; j < m; j++) {
                if (array1[i] == array2[j]) {
                    return false;
                }
            }
        }
        return true;
    }

}