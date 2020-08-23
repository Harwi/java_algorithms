package leetcode.strings_add;

public class Solution {
    public static void main(String[] args) {
        String res = addStrings("9", "99");
    }

    public static String addStrings(String num1, String num2) {
        int len = Math.max(num1.length(), num2.length());

        if (num1.length() < len) {
            num1 = padWithZeroes(num1, len - num1.length());
        } else {
            num2 = padWithZeroes(num2, len - num2.length());
        }

        StringBuilder resBuilder = new StringBuilder();
        int counter = len - 1;
        int buf = 0;

        while (counter >= 0) {
            int v1 = num1.charAt(counter) - '0';
            int v2 = num2.charAt(counter) - '0';

            int sum = v1 + v2 + buf;
            resBuilder.insert(0, sum % 10);
            buf = sum / 10;
            counter--;
        }

        if (buf != 0) {
            resBuilder.insert(0, buf);
        }

        return resBuilder.toString();
    }

    private static String padWithZeroes(String s, int zeroes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= zeroes; i++) {
            sb.append("0");
        }
        return sb.toString().concat(s);
    }
}
