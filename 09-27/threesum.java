import java.util.*;
import java.io.*;
public class threesum {
    public static void main (String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("threesum.in"));
        PrintWriter out = new PrintWriter("threesum.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
           A[i] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[N][N];
        /* 
        for (int i = N - 1; i >= 0; i--) {
            int[] dist = new int[2000001];
            for (int x = 0; x <= i; x++) {
                dist[A[x] + 1000000]++;
            }
            for (int j = i + 1; j < N; j++) {
                int k = -1 * ( A[j] + A[i]) + 1000000;
                if (k >= 0 && k <= 2000000) {
                    dp[i][j] += dist[k];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j-1];
                }
                if (i < N - 1) {
                    dp[i][j] += dp[i+1][j];
                }
                if (i < N - 1 && j > 0) {
                    dp[i][j] -= dp[i+1][j - 1];
                }
                dist[A[j] + 1000000]--;
                    
            }
        } */
        int[] dist = new int[2000001];
        for (int i = N -1; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                int k = 1000000 - A[i] - A[j];
                if (k >= 0 && k < 2000001) dp[i][j] = dist[k];
                dist[1000000 + A[j]]++;
            }
            for (int j = i + 1; j < N; j++) {
                dist[1000000 + A[j]]--;
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j =  i + 1; j < N; j++) {
                dp[i][j] += dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
            }
        }
        StringBuilder sb = new StringBuilder();
        while (Q-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[a][b] + "\n");
        }
        out.print(sb);
        out.close();
    }
}