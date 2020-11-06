package backtobackswe.add_binary_strings;

class Solution {
    public String addBinaryStrings(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            throw new IllegalArgumentException("Input arguments must be specified");
        }

        int ix1 = s1.length() - 1;
        int ix2 = s2.length() - 1;
        final StringBuilder sumBuilder = new StringBuilder();
        int carry = 0;

        while (ix1 >= 0 || ix2 >= 0) {
            int sum = carry;

            if (ix1 >= 0) {
                sum += Character.getNumericValue(s1.charAt(ix1--));
            }

            if (ix2 >= 0) {
                sum += Character.getNumericValue(s2.charAt(ix2--));
            }

            sumBuilder.append(sum % 2);
            carry = sum / 2;
        }

        if (carry > 0) {
            sumBuilder.append(carry);
        }

        return sumBuilder.reverse().toString();
    }
}
