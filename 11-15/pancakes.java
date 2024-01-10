
import java.io.*;
public class pancakes {
    static int[][] DP;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        DP = new int[1001][1001];
        DP[0][0] = 1;
         
        for (int i=1; i<=1000; i++)
        {
            DP[i][0] = DP[i-1][i-1] % MOD;
  
            for (int j=1; j<=i; j++)
                DP[i][j] = (DP[i-1][j-1] + DP[i][j-1]) % MOD;
        }
         
        while (Q-->0) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(DP[N][0]);
        }
    }
    
}