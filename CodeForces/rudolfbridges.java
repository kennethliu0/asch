import java.util.*;
import java.io.*;
public class rudolfbridges {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            long[] ans = new long[N];
            for (int i = 0; i < N; i++) {
                long[] dp = new long[M];
                long[] depths = new long[M];
                st = new StringTokenizer(br.readLine());
                TreeMap<Long, Long> costs = new TreeMap<>();
                for (int j = 0; j < M; j++) {
                    depths[j] = Long.parseLong(st.nextToken());
                    if (j -D- 2 >= 0) {
                        if (costs.get(dp[j-D-2]) == 1) costs.remove(dp[j-D-2]);
                        else costs.put(dp[j-D-2], costs.get(dp[j-D-2]) - 1);
                    }
                     if (j == 0) {
                        dp[j] = depths[j] + 1;
                    }
                    else {
                        dp[j] = depths[j] + costs.firstKey() + 1;
                    }    
                    costs.put(dp[j], costs.getOrDefault( dp[j], (long) 0) + 1);
                }
                ans[i] = dp[M - 1];
            }
            long res = Long.MAX_VALUE;
            for (int i = 0; i + K <= N; i++) {
                long temp = 0;
                for (int j = i; j < i + K; j++) {
                    temp += ans[j];
                }
                res = Math.min(res, temp);
            }
            sj.add(Long.toString(res));
        }
        br.close();
        System.out.println(sj);
    }
}