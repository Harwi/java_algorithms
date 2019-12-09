package min_window_substring;

// Input: S = "ADOBECODEBANC", T = "ABC"
// Output: "BANC"

import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        minWindow("ADOBECODEBANC", "ABC");
    }

    public static String minWindow(String s, String t) {

        HashMap<Character, Integer> hm = new HashMap<>(t.length(), 1.0f);

        for (char c : t.toCharArray()) {
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, 1);
            }
        }

        int head = -1;
        int left = 0;
        int right = 0;
        int window = Integer.MAX_VALUE;
        int counter = t.length();

        while (right < s.length()) {
            char cRight = s.charAt(right++);
            if (hm.containsKey(cRight)) {
                if (hm.get(cRight) > 0) {
                    counter--;
                }
                hm.put(cRight, hm.get(cRight) - 1);
            }

            while (counter == 0) {
                if (window > right - left) {
                    head = left;
                    window = right - left;
                }

                char cLeft = s.charAt(left);
                if (hm.containsKey(s.charAt(left++))) {
                    if (hm.get(cLeft) >= 0) {
                        counter++;
                    }
                    hm.put(cLeft, hm.get(cLeft) + 1);
                }
            }
        }

        return window == Integer.MAX_VALUE ? "" : s.substring(head, head + window);
    }
}
