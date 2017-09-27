package disjoint;

/**
 * Created by JJ Lim and Daniel Lim on 9/16/17.
 */
public class DisjointProgram {
    public static void main(String [] args) {
        Disjoint disjoint = new Disjoint();

        int [] array1 = {3, 2, 5};
        int [] array2 = {1, 4, 6};

        System.out.println(disjoint.isDisjoint(new int [] {3, 2, 5}, new int [] {1, 4, 6}));
    }

}