package backtobackswe.longest_palindrome;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        final Set<Character> occur = new HashSet<>();

        for (Character c : s.toCharArray()) {
            if (occur.contains(c)) {
                count++;
                occur.remove(c);
            } else {
                occur.add(c);
            }
        }

        return (count * 2) + (occur.size() > 0 ? 1 : 0);
    }
}
