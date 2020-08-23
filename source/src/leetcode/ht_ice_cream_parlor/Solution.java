package leetcode.ht_ice_cream_parlor;

import java.util.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        // price, index
        HashMap<Integer, ArrayList<Integer>> ht = new HashMap<>();

        for (int ix = 0; ix < cost.length; ix++) {
            ArrayList<Integer> ixArr = new ArrayList<>();
            ixArr.add(ix);
            if (ht.containsKey(cost[ix])) {
                ixArr.addAll(ht.get(cost[ix]));
            }
            ht.put(cost[ix], ixArr);
        }

        int firstIx = -1;
        int secondIx = -1;

        for (int ix = 0; ix < cost.length; ix++) {
            if (cost[ix] < money) {
                if (money % cost[ix] == 0 && ht.get(cost[ix]).size() > 1) {
                    firstIx = ht.get(cost[ix]).get(0);
                    secondIx = ht.get(cost[ix]).get(1);
                } else if (money % cost[ix] != 0 && ht.containsKey(money - cost[ix])) {
                    firstIx = ht.get(cost[ix]).get(0);
                    secondIx = ht.get(money - cost[ix]).get(0);
                }
                if (firstIx >= 0 && secondIx >= 0) {
                    System.out.println(String.format("%s %s", Math.min(firstIx, secondIx) + 1, Math.max(firstIx, secondIx) + 1));
                    return;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}

