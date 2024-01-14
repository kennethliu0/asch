import java.util.*;
import java.io.*;
public class summation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int[] dp = new int[K + 1];
            Integer[] arr = new Integer[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dp[0] += arr[i];
            }
            Arrays.sort(arr, (e1, e2) -> e2 - e1);
            int[] pf = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                pf[i] = pf[i-1] + arr[i - 1];
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i <= K; i++) {
                if (X + i >= N) { 
                    max = Math.max(max, pf[N] - pf[i] - 2 * (pf[N] - pf[i]));
                } else {
                    max = Math.max(max, pf[N] - pf[i] - 2 * (pf[X + i] - pf[i]));
                }
            }
            sj.add(Integer.toString(max));
        }
        System.out.println(sj);
        br.close();
    }
}