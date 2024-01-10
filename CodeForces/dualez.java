
import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1854/A1
public class dualez {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int positive = -1;
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] > 0) positive = i + 1;
            }
            StringBuilder sb = new StringBuilder();
            if (positive == -1) {
                sb.append(N - 1 + "\n");
                for (int i = N; i >= 2; i--) {
                    sb.append((i - 1) + " " + i + "\n");
                }
            } else {
                sb.append(5 + 2 * (N) + "\n");
                for (int i = 0; i < 5; i++) {
                    sb.append(positive + " " + positive + "\n");
                }
                int prev = positive;
                for (int i = 1; i <= N; i++) {
                    sb.append(i + " " + prev + "\n" + i + " " + prev + "\n");
                    prev= i;
                }
            }
            System.out.print(sb);
        }
    }
}
