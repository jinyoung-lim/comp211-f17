package disjoint;

/**
 * Created by JJ Lim and Daniel Lim on 9/16/17.
 */
public class Disjoint {

    /**
     * Constructor for this class.
     */
    public Disjoint() {

    }

    /**
     *
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
