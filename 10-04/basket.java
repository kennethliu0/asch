import java.util.*;
import java.io.*;
public class basket {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] V = new int[N + 1];
        int[] W = new int[N + 1];
        int[] K = new int[N + 1];
        int[][] DP = new int[N + 1][S + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            V[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
        }
        // naive knapsack dp
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= S; j++) {
                DP[i][j] = DP[i-1][j];
                for (int q = 1; q <= K[i]; q++) {
                    if (j - q * W[i] < 0) {
                        break;
                    } else {
                        DP[i][j] = Math.max(DP[i][j], q * V[i] + DP[i-1][j - q * W[i]]);
                    }
                }
            }
        }
        /* 
         * Solution:
         * Sort item types by weight in a treemap
         * You can use entry set to iterate through weights
         * then, for every value of total weight restriction, either don't take any piece of weight w, or pick pieces with the greatest value,
         * going through as many types of items as possible by descending weight
         */
        System.out.println(DP[N][S]);
        br.close();
    }

}