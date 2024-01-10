import java.util.*;
import java.io.*;
public class two48 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("248.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter("248.out");
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        int[][] dp = new int[N][N];
        int ans = 0;
        for (int len = 1; len <= N; len++) {
            for (int i = 0; i + len <= N; i++) {
               int j = i + len - 1;
               dp[i][j] = -1;
               if (len == 1) {
                dp[i][j] = A[i];
               }
               for (int k = i; k < j; k++) {
                if (dp[i][k] == dp[k + 1][j] && dp[i][k] > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
                }
               } 
            ans = Math.max(ans, dp[i][j]);
            } 
        }
        out.println(ans);
        out.close();
    }
}