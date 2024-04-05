import java.util.*;
import java.io.*;
public class removal {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N][N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0][N - 1] = 0;
        for (int i =0; i < N; i++ ) {
            for (int j = i; j < N; j++) {
                if (i + N - 1 - j % 2 == 0) {
                    dp[i+1][j] = Math.min(dp[i][j] + x[i], dp[i + 1][j]);
                } else{
                    dp[i + 1] = Math.min(dp[i][j],)
                }
            }
        }
        for (int j = N - 1; j >= 0; j--){ 
            for (int i = 0; i < N; i++) {
                if (i + N - 1 % 2 == 0) {
                    dp[i][j-1] = Math.min(dp[i][j-1], dp[i][j] + x[i]);
                }
            }
        }
    }
}
