import java.util.*;
import java.io.*;
public class sendmessage {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        out:
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int  n = Integer.parseInt(st.nextToken());
            long  f = Integer.parseInt(st.nextToken());
            long  a = Integer.parseInt(st.nextToken());
            long  b = Integer.parseInt(st.nextToken());
            long[]m = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                m[i] = Long.parseLong(st.nextToken());
            }
            f -= Math.min(a * m[0], b);
            if (f <= 0) {
                System.out.println("NO");
                continue out;
            }
            for (int i = 1; i < n; i++) {
                f -= Math.min(a * (m[i] - m[i-1]), b);
                if (f <= 0) {
                    System.out.println("NO");
                    continue out;
                }
            }
            
            System.out.println("YES");
        }
    }
}