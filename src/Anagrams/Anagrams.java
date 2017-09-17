package Anagrams;

import java.util.Arrays;

/**
 * A class that determines if two Strings are anagrams.
 * Created by Daniel Lim and JJ Lim on 9/17/17.
 */
public class Anagrams {
    /**
     * Constructor for the class Anagrams.
     */
    public Anagrams() {
    }

    /**
     * Determines if two input words are anagrams.
     * @param s1 a single-word string
     * @param s2 a single-word string
     * @return true if s1 and s2 are anagrams, false otherwise
     */
    public boolean isAnagrams(String s1, String s2) {
        // Turn the string into lowercase
        String lowercase1 = s1.toLowerCase();
        String lowercase2 = s2.toLowerCase();

        // Get rid of non-word characters
        String clean1 = lowercase1.replaceAll("\\W", "");
        String clean2 = lowercase2.replaceAll("\\W", "");

        // Convert the strings into character arrays
        char [] word1 = clean1.toCharArray();
        char [] word2 = clean2.toCharArray();

        // Sort the character arrays
        Arrays.sort(word1);
        Arrays.sort(word2);

        // Determines if the two sorted character arrays are the same. If they are, the two words are anagrams and
        // return true, false otherwise.
        return Arrays.equals(word1, word2);
    }
}