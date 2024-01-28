import java.util.*;
import java.io.*;
public class goodtrip {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            double ans = 0;
            double combos = N * (N-1)/2;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken(); st.nextToken();
                ans = (ans + K * Integer.parseInt(st.nextToken()) / combos + Math.abs((K / combos) *(K / combos - 1) / 2) )% MOD;
            }
            if (ans < 1) {
                System.out.println((1e18 * ans) % MOD);
            } else
            {System.out.println(ans);}
        }
        br.close();
    }
}