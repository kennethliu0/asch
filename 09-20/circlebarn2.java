import java.util.*;
import java.io.*;
public class circlebarn2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] R = new int[N];
        for (int i = 0; i < N; i++) {
            R[i] = Integer.parseInt(br.readLine());
        }
        int ans = 0;
        for (int i = 0; i < N; i++) { // i is the first door
            int[][] dp = new int[K][N];  // total cost of opening i - 1 total doors with last at j
            // calculate initial cost
            for (int j = i; j < N + i; j++) {
                dp[0][i] += R[j % N] * j; 
            }

            for (int j = 1; j < K; j++) { // iterate through # doors placed
                for (int prev = i; prev < N; prev++) { // iterate through previous one
                    for (int next = prev + 1; next < N; next++) { // iterate through next
                        dp[j][next] = dp[j - 1][prev] - (next - prev) * (N);
                    }
                }
            }
        }
    }
}