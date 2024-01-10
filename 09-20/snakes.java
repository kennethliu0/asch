import java.util.*;
import java.io.*;
public class snakes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] snakes = new int[N];
        for (int i = 0; i < N; i++) {
            snakes[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N][K];
        for (int j = 1; j < N; j++) {
            for (int x = 1; x < K; x++) {
                int max = snakes[j];
                int total = 0;
                for (int i = j - 1; i >= 0; i--) {
                    dp[j][x] = Math.min(dp[j][x], dp[i][x- 1] + total);
                    if (max < snakes[i]) { // need new net
                        total += (j - i) * (snakes[i] - max);
                        max = snakes[i];
                    } else{
                        total += max - snakes[i];
                    }
                }
            }
        }
        System.out.println(dp[N-1][K-1]);
    }
}