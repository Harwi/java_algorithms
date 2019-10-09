package string_permutation_substring;

class Solution {
    // s1 - pattern
    // s2 - input
    private static int getIndex(char c) {
        return c - 'a';
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] patternFreq = new int[26];
        int[] inputFreq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            patternFreq[getIndex(s1.charAt(i))]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            inputFreq[getIndex(s2.charAt(i))]++;

            int windowLeftIx = i + 1 - s1.length();
            if (windowLeftIx >= 0) {
                // removing freqs of characters preceding to the window
                if (windowLeftIx > 0) {
                    inputFreq[getIndex(s2.charAt(windowLeftIx - 1))]--;
                }

                if (checkFreq(patternFreq, inputFreq)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkFreq(int[] freq1, int[] freq2) {
        for (int i = 0; i < freq1.length; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }
}