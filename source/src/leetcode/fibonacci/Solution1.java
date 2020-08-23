package leetcode.fibonacci;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

public class Solution1 {

    public static void main(String args[]) {
        BigInteger n = BigInteger.valueOf(10);
        Instant start = Instant.now();
        System.out.println(String.format("Fibonacci for %s: %s", n, calc(n)));
        Instant end = Instant.now();
        System.out.println(String.format("Elapsed millis: %s", Duration.between(start, end).toMillis()));
    }

    // Recursion
    static BigInteger calc(BigInteger n) {
        if (n.compareTo(BigInteger.valueOf(2)) < 0) {
            return BigInteger.valueOf(1);
        }
        return calc(n.subtract(BigInteger.valueOf(1))).add(calc(n.subtract(BigInteger.valueOf(2))));
    }

}
