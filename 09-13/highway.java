
import java.util.*;
import java.io.*;
public class highway {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("10.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] x = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        int slow = 0, fast = 0;
        long[] DP = new long[N];
        for (; fast < N; fast++) {
            if (fast != 0) {
                DP[fast] = DP[fast - 1];
            }
            if (x[slow] + T > x[fast]) {
                DP[fast] = Math.max(DP[fast], v[fast]);
            } else {
                do {
                    slow++;
                } while (slow < N && x[slow] + T < x[fast]);
               DP[fast] = DP[slow] + v[fast];
            
            }
        }
        System.out.println(DP[N-1]);
    }
}
