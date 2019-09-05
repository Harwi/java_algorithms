package base64;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

    private static HashMap<Integer, Character> buildBase64Table() {
        char c;
        int counter = 0;
        HashMap<Integer, Character> res = new HashMap<>();
        for (c = 'A'; c <= 'Z'; c++) {
            res.put(counter, c);
            counter++;
        }
        for (c = 'a'; c <= 'z'; c++) {
            res.put(counter, c);
            counter++;
        }
        for (int i = 0; i <= 9; i++) {
            res.put(counter, Character.forDigit(i, 10));
            counter++;
        }
        res.put(counter++, '+');
        res.put(counter++, '/');
        return res;
    }


    private static String addZeroes(String input, int len) {
        while (input.length() < len) {
            input = "0" + input;
        }
        return input;
    }

    private static String toBase64(String input) {
        String bitsTotal = "";

        for (int ix = 0; ix < input.length(); ix++) {
            //Integer b = new Integer(input.charAt(ix));
            String bits = Integer.toBinaryString(input.charAt(ix));
            bits = addZeroes(bits, 8);
            bitsTotal = bitsTotal + bits;
        }

        List<Integer> list = new ArrayList<>();
        String readSoFar = "";
        for (int ix = 0; ix < bitsTotal.length(); ix++) {
            readSoFar = readSoFar + bitsTotal.charAt(ix);
            if (readSoFar.length() == 6) {
                list.add(Integer.parseInt(addZeroes(readSoFar, 8), 2));
                readSoFar = "";
            }
        }
        if (!readSoFar.isEmpty()) {
            list.add(Integer.parseInt(addZeroes(readSoFar, 8), 2));
        }

        HashMap<Integer, Character> base64Table = buildBase64Table();
        String res = "";

        for (int ix = 0; ix < list.size(); ix++) {
            res = res + base64Table.get(list.get(ix));
        }

        return res;
    }

    public static void main(String[] args) {
        toBase64("ABC");
    }

}
