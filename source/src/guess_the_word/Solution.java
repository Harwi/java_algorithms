package guess_the_word;

import java.util.*;

interface Master {
   public int guess(String word);
}

class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        if (wordlist == null || wordlist.length == 0) {
            return;
        }

        int l = wordlist[0].length();

        Set<Integer> possible = new HashSet<>();
        for (int i = 0; i < wordlist.length; i++) {
            possible.add(i);
        }


        while (!possible.isEmpty()) {
            int choice = possible.iterator().next();

            final String chosenWord = wordlist[choice];
            int matches = master.guess(chosenWord);

            if (matches == l) {
                return;
            }

            final Set<Integer> possibleFiltered = new HashSet<>();
            for (Integer ix: possible) {
                if (countMatches(chosenWord, wordlist[ix]) == matches && ix != choice) {
                    possibleFiltered.add(ix);
                }
            }

            possible = possibleFiltered;
        }
    }

    private int countMatches(String word1, String word2) {
        int matches = 0;
        for (int k = 0; k < word1.length(); k++) {
            if (word1.charAt(k) == word2.charAt(k)) {
                matches++;
            }
        }
        return matches;
    }
}