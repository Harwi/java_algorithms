package leetcode.graph_roads_and_libs;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        long costTotal = 0;
        if (c_lib <= c_road) {
            costTotal = (long) c_lib * n;
        } else {
            AdjMatrix adjMatrix = new AdjMatrix(n, cities);

            for (int cityIx = 0; cityIx < n; cityIx++) {
                long roads = adjMatrix.dfs(cityIx);
                if (roads >= 0) {
                    //System.out.println("Roads = " + roads);
                    costTotal = costTotal + roads * (long) c_road + c_lib;
                }
            }
        }

        //System.out.println("Result cost = " + costTotal);
        return costTotal;
    }

    static class AdjMatrix {
        final ArrayList<Integer>[] adjCities;
        final boolean[] visited;

        public AdjMatrix(int citiesNum, int[][] cities) {
            this.adjCities = (ArrayList<Integer>[]) new ArrayList[citiesNum];
            this.visited = new boolean[citiesNum];

            for (int ix = 0; ix < citiesNum; ix++) {
                this.adjCities[ix] = new ArrayList<>();
            }

            for (int roadIx = 0; roadIx < cities.length; roadIx++) {
                adjCities[cities[roadIx][0] - 1].add(cities[roadIx][1] - 1);
                adjCities[cities[roadIx][1] - 1].add(cities[roadIx][0] - 1);
            }
        }

        long dfs(int city) {
            if (visited[city]) {
                return -1;
            }
            visited[city] = true;
            long res = 0;
            for (Integer adjCity : adjCities[city]) {
                if (!visited[adjCity]) {
                    //System.out.println("Traversing through path = <" + (cityIx + 1) + "> <" + (adjCityIx + 1) + ">");
                    res = res + 1 + dfs(adjCity);
                }
            }
            return res;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
