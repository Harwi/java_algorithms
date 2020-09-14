package backtobackswe.eliminateAllPrimes;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> enumeratePrimes(int n) {
        if (n <= 1) {
            return new ArrayList<>();
        }

        final List<Integer> res = new ArrayList<>();
        for (int ix = 2; ix < n; ix++) {
            if (isPrime(ix)) {
                res.add(ix);
            }
        }

        return res;
    }

    private boolean isPrime(Integer n) {
        int ix = 2;
        while (ix < n) {
            if (n % ix == 0) {
                return false;
            }
            ix++;
        }
        return true;
    }
}
