package backtobackswe.change_base;

class Solution {
    public String changeBase(String numAsString, int b1, int b2) {
        boolean isNegative = numAsString.startsWith("-");

        int start = isNegative ? 1 : 0;
        int maxPower = numAsString.length() - 1;
        int numberUnderBase10 = 0;

        for (int i = start; i < numAsString.length(); i++) {

            boolean isPlaceADigit = Character.isDigit(numAsString.charAt(i));
            int valueContributedByPlace =
                    isPlaceADigit ? numAsString.charAt(i) - '0' : numAsString.charAt(i) - 'A' + 10;

            int positionPowerWeight = maxPower - i;
            numberUnderBase10 += (int) valueContributedByPlace * Math.pow(b1, positionPowerWeight);
        }

        if (numberUnderBase10 == 0) {
            return "0";
        } else {
            return (isNegative ? "-" : "") + base10ToNewBase(numberUnderBase10, b2);
        }
    }

    private String base10ToNewBase(int numberUnderBase10, int base) {
        if (numberUnderBase10 == 0) {
            return "";
        }

        // lsd => "least significant digit"
        char lsdAsChar;
        int lsdUnderFinalBase = numberUnderBase10 % base;

        boolean needsHexConversion = lsdUnderFinalBase >= 10;
        if (needsHexConversion) {
            lsdAsChar = (char) ('A' + lsdUnderFinalBase - 10);
        } else {
            lsdAsChar = (char) ('0' + lsdUnderFinalBase);
        }

        int base10NumberWithoutLsd = numberUnderBase10 / base;
        String everythingElseToOurLeft = base10ToNewBase(base10NumberWithoutLsd, base);

        return everythingElseToOurLeft + lsdAsChar;
    }

    public static void main(String... args) {
        final String numAsString = "11101011100001101";
        final int base1 = 2;
        final int base2 = 16;
        final String res = new Solution().changeBase(numAsString, base1, base2);
        System.out.println(String.format("Result of converting %s from base [%s] to base [%s] in [%s]", numAsString, base1, base2, res));
    }
}