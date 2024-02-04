import java.util.*;
import java.io.*;
// https://cses.fi/problemset/task/1620/
public class factory {
    static int[] k;
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        k = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            k[i] = Integer.parseInt(st.nextToken());
        }
        long  lo= 0;
        long  hi = (long) 1e18;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (check(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        System.out.println(lo);
        br.close();
    }
    static boolean check(long  i) {
        long ans = 0;
        for (int x: k) {
            if (ans >= T) return true;
            ans += i / x;
        }
        return ans >= T;
    }
}