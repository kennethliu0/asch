import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1881/E
public class block {
    static int[] dp;
    static int[] A;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            A = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            dp = new int[N];
            Arrays.fill(dp, -1);
            sj.add("" + search(0));

        }
        System.out.println(sj);
        br.close();
    }
    static int search(int i) {
        if (i > A.length) return 200000;
        if (i == A.length) return 0;
        if (dp[i] > -1) return dp[i];
        dp[i] = Math.min( 1 + search(i + 1), search(i + A[i] + 1));
        return dp[i];
    }
}