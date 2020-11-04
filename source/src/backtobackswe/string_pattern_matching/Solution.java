package backtobackswe.string_pattern_matching;

import java.util.*;

class Solution {
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        final List<String> res = Collections.synchronizedList(new ArrayList<>());
        if (words == null || words.length == 0 || pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Input words and pattern must be present");
        }

        Arrays.stream(words).parallel().forEach(
                word -> {
                    if (pattern.length() == word.length()) {
                        final HashMap<Character, Character> patternMap = new HashMap<>(pattern.length(), 1f);
                        final HashMap<Character, Character> wordMap = new HashMap<>(words.length, 1f);

                        boolean matched = true;
                        for (int ix = 0; ix < word.length(); ix++) {
                            Character cw = word.charAt(ix);
                            Character cp = pattern.charAt(ix);

                            patternMap.putIfAbsent(cp, cw);
                            wordMap.putIfAbsent(cw, cp);

                            if (!patternMap.get(cp).equals(cw) || !wordMap.get(cw).equals(cp)) {
                                matched = false;
                                break;
                            }
                        }

                        if (matched) {
                            res.add(word);
                        }
                    }
                }
        );

        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[3];
        words[0] = "abc";
        words[1] = "cde";
        words[2] = "zzz";

        List<String> res = findAndReplacePattern(words, "aaa");
        System.out.println(res);
    }
}
