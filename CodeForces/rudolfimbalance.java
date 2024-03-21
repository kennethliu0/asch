import java.util.*;
import java.io.*;
public class rudolfimbalance {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] a= new int[N];
            st = new StringTokenizer(br.readLine());
            int minright = 0;
            int minleft = 0;
            int secondMin = 0;
            for (int i =0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N - 1; i++) {
                if (a[i + 1] - a[i] > secondMin) {
                    secondMin = a[i+ 1] - a[i];
                }
                if (secondMin > minright - minleft) {
                    secondMin = minright - minleft;
                    minright = a[i + 1];
                    minleft = a[i];
                }
            }
            int[] d = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                d[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(d);
            int[] f = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                f[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(f);
            int ans = minright - minleft;
            long target = ((long) minright + minleft) / 2;
            for (int i = 0; i < M; i++) {
                int lo = 0;
                int hi = K - 1;
                while (lo + 1 < hi) {
                    int mid = (lo + hi + 1) / 2;
                    if ((long) d[i] + f[mid] <= target) {
                        lo = mid;
                    } else {
                        hi = mid;
                    }
                }
                ans = Math.min(ans, Math.max(d[i] + f[lo] - minleft, minright - d[i] - f[lo] ));
                if (lo < K - 1) {
                    ans = Math.min(ans, Math.max(d[i] + f[hi] - minleft, minright - d[i] - f[hi]));
                }
            }
            sj.add(Integer.toString(Math.max(ans, secondMin)));

        }
        br.close();
        System.out.println(sj);
    }
}