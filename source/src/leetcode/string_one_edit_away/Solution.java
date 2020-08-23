package leetcode.string_one_edit_away;

public class Solution {

    private static Boolean isOneAway(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        int diff = Math.abs(str1.length() - str2.length());
        if (diff > 1) {
            return false;
        }

        for (int i = 0; i < str1.length() - 1 && i < str2.length() - 1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                break;
            }
        }

        return diff <= 1;
    }



    public static void main(String args[]) {
        boolean res = isOneAway("test1", "test2");
        res = isOneAway("test1", "test12");
    }

}
