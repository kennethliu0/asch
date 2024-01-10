import java.util.*;
import java.io.*;
public class knapsack {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] S = new int[N + 1];
        int[] V = new int[N + 1];
        int[][] DP = new int[N + 1][M + 1];
        for (int i= 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) { // last item considered
            for (int j = 0; j <= M; j++) { // max value reaches J
                DP[i][j] = DP[i-1][j];
                if (S[i] <= j)
                    DP[i][j] = Math.max(DP[i][j], V[i] + DP[i-1][j - S[i]]);
            }
        }
        System.out.println(DP[N][M]);
        br.close();
    }
}