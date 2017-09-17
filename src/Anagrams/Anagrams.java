package Anagrams;

import java.util.Arrays;

/**
 *
 * Created by Daniel Lim and JJ Lim on 9/17/17.
 */
public class Anagrams {

    public Anagrams() {
    }

    public boolean isAnagrams(String s1, String s2) {

        String lowercase1 = s1.toLowerCase();
        String lowercase2 = s2.toLowerCase();

        String clean1 = lowercase1.replaceAll("\\W", "");
        String clean2 = lowercase2.replaceAll("\\W", "");

        char [] A = clean1.toCharArray();
        char [] B = clean2.toCharArray();

        Arrays.sort(A);
        Arrays.sort(B);

        return Arrays.equals(A, B);
    }
}
