package backtobackswe.valid_palindrome;

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalStateException("Input must be specified");
        }

        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            char l = s.charAt(left);
            char r = s.charAt(right);

            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else {
                if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    return false;
                }
                left++;
                right--;
            }
        }

        return true;
    }
}