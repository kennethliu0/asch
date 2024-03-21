import java.util.*;
import java.io.*;
public class gridpaths {
    static final long MOD = (long) 1e9 + 7;
    static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j =0; j < N; j++) {
                if(s.charAt(j) == '*') {
                    dp[i][j] = -2;
                }
            }
        }
        br.close();
        if (dp[0][0] == -2) {
            System.out.println(0);
            return;
        }
        dp[0][0] = 1;
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == -2) continue;
                if (j + 1 < N && dp[i][j + 1] != -2) {
                    dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % MOD;
                }
                if (i + 1 < N && dp[i + 1][j] != -2) {
                    dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % MOD;
                }
            }
        }
        System.out.println(dp[N-1][N-1] == -2 ? 0: dp[N-1][N-1] );
    }
}