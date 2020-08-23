package leetcode.array_reorder_logs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Solution {
    private static final String SPACE = " ";

    public String[] reorderLogFiles(String[] logs) {
        List<String> letters = new ArrayList<>();
        List<String> digits = new ArrayList<>();

        for (String log : logs) {
            int delimIx = log.indexOf(SPACE) + 1;
            if (Character.isDigit(log.charAt(delimIx))) {
                digits.add(log);
            } else {
                letters.add(log);
            }
        }

        Collections.sort(letters, new LogComparator());

        return Stream.concat(letters.stream(), digits.stream()).toArray(String[]::new);
    }

    class LogComparator implements Comparator<String> {
        public int compare(String str1, String str2) {
            int ix1 = str1.indexOf(SPACE) + 1;
            int ix2 = str2.indexOf(SPACE) + 1;

            while (ix1 < str1.length() && ix2 < str2.length()) {
                if (str1.charAt(ix1) == str2.charAt(ix2)) {
                    ix1++;
                    ix2++;
                } else {
                    return str1.charAt(ix1) - str2.charAt(ix2);
                }
            }

            ix1 = 0;
            ix2 = 0;

            while (ix1 < str1.indexOf(SPACE) && ix2 < str2.indexOf(SPACE)) {
                if (str1.charAt(ix1) == str2.charAt(ix2)) {
                    ix1++;
                    ix2++;
                } else {
                    return str1.charAt(ix1) - str2.charAt(ix2);
                }
            }

            return 0;
        }
    }
}