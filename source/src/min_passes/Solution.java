package min_passes;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the minimumPasses function below.
    // m - machines
    // w - workers
    // p - cost (candies) to buy machine / hire worker
    // n - number of candies to produce
    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0;
        long produced = 0;

        while (produced < n) {
                while (produced - p >= 0 && produced + m * w < n) {
                    long mi = (m > w) ? m : m + 1;
                    long wi = (m > w) ? w + 1 : w;
                    if ( (n - (produced - p)) / (mi * wi) <= (n - produced) / (m * w)) {
                        m = mi;
                        w = wi;
                        produced = produced - p;
                    } else {
                        break;
                    }
                }

            produced = produced + m * w;
                passes++;
        }
        return passes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}