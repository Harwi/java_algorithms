package fibonacci;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution2 {

    public static void main(String args[]) {
        int n = 60;
        Instant start = Instant.now();
        System.out.println(String.format("Fibonacci for %s: %s", n, calc(n)));
        Instant end = Instant.now();
        System.out.println(String.format("Elapsed millis: %s", Duration.between(start, end).toMillis()));
    }

    // Dynamic progamming with memoization
    static BigInteger calc(int n) {
        ArrayList<BigInteger> fibArray = new ArrayList<>(n);
        fibArray.add(0, BigInteger.valueOf(1));
        fibArray.add(1, BigInteger.valueOf(1));

        for (int idx = 2; idx < n; idx++) {
            fibArray.add(idx, fibArray.get(idx - 1).add(fibArray.get(idx - 2)));
        }
        return fibArray.get(fibArray.size() - 1);
    }
}
