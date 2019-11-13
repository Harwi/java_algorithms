package look_and_say;

public class Solution {

    public static void main(String[] args) {
        String res = countAndSay(20);
    }

    public static String countAndSay(int n) {
        if (n == 0) {
            return Integer.toString(n);
        } else if (n == 1) {
            return "1";
        }

        String s = "1";
        for (int i = 1; i < n; i++) {
            s = read(s);
        }
        return s;
    }

    public static String read(String s) {
        StringBuilder resBuilder = new StringBuilder();

        long similarCount = 0;
        char prev = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                similarCount++;
            } else {
                resBuilder.append(similarCount).append(prev);
                similarCount = 1;
                prev = s.charAt(i);
            }

            if (s.length() - 1 == i) {
                resBuilder.append(similarCount).append(prev);
            }
        }
        System.out.println(resBuilder.toString());
        return resBuilder.toString();
    }

}
