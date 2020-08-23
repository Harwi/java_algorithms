package leetcode.word_distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        String[] arr = new String[]{"practice", "makes", "perfect", "coding", "makes"};

        WordDistance wordDistance = new WordDistance(arr);
        System.out.println("Dist = " + wordDistance.shortest("coding", "practice"));
        System.out.println("Dist = " + wordDistance.shortest("makes", "coding"));
    }

    static class WordDistance {

        final Map<String, List<Integer>> wordMap;
        public WordDistance(String[] words) {
            wordMap = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                if (!wordMap.containsKey(words[i])) {
                    wordMap.put(words[i], new ArrayList<>());
                }
                wordMap.get(words[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            if (!wordMap.containsKey(word1) || !wordMap.containsKey(word2)) {
                return -1;
            }
            List<Integer> indices1 = wordMap.get(word1);
            List<Integer> indices2 = wordMap.get(word2);
            int i1 = 0;
            int i2 = 0;
            int min = Integer.MAX_VALUE;

            while (i1 < indices1.size() || i2 < indices2.size()) {
                int v1 = i1 < indices1.size() ? indices1.get(i1) : indices1.get(indices1.size() - 1);
                int v2 = i2 < indices2.size() ? indices2.get(i2) : indices2.get(indices2.size() - 1);
                min = Math.min(min, Math.abs(v1 - v2));
                if (i1 >= indices1.size()) {
                    i2++;
                } else if (i2 >= indices2.size()) {
                    i1++;
                } else if (v1 < v2) {
                    i1++;
                } else {
                    i2++;
                }
            }

            return min;
        }
    }

}
