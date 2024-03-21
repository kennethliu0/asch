import java.util.*;
import java.io.*;
public class removalgame {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] x= new long[N];
        long sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        long[][] dp = new long[N][N];
        for (int i =0; i < N; i++) {
            x[i] = Long.parseLong(st.nextToken());
            sum += x[i];
            dp[i][i] = x[i];
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                dp[i][j] = Math.max(x[i] - dp[i + 1][j], x[j] - dp[i][j-1]);
            }
        }
        System.out.println((sum + dp[0][N-1]) / 2);
    
        
    }
}