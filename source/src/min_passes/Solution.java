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
        double produced = 0;
        double producedThisTurn;
        double md = m;
        double wd = w;
        double mi;
        double wi;
        double addPower;
        double mwDiff;
        double freeAfterEq;
        double missing;

        while (produced < n) {
            producedThisTurn = md * wd;
            if ((produced + producedThisTurn) < n) {
//                if (produced - p < 0 && (long) producedThisTurn / p > 1) {
//                    long shiftPasses = (long) Math.floor((p - produced) / producedThisTurn);
//                    produced = produced + producedThisTurn * shiftPasses;
//                    passes = passes + shiftPasses;
//                    continue;
//                }

                if (produced - p >= 0) {

                    addPower = Math.floor(produced / p);
                    mwDiff = Math.abs(md - wd);
                    freeAfterEq = 0;

                    if (addPower > mwDiff) {
                        freeAfterEq = addPower - mwDiff;
                    }

                    double minAdd = Math.min(mwDiff, addPower);

                    mi = ((md > wd) ? md : md + minAdd) + Math.floor(freeAfterEq / 2);
                    wi = ((md > wd) ? wd + minAdd : wd) + (freeAfterEq - Math.floor(freeAfterEq / 2));

                    missing = n - produced;

                    double cost = addPower * p;
                    double producedUpdated = mi * wi;

                    if ( ((missing + cost) / (producedUpdated)) <= (missing / (producedThisTurn))) {
                        producedThisTurn = producedUpdated;
                        md = mi;
                        wd = wi;
                        produced = produced - cost;
                    }
                }
            }

            produced = produced + producedThisTurn;
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