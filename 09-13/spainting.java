import java.io.*;
import java.util.*;
public class spainting {
    static final int MOD = (int) (1e9 + 7);
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
        PrintWriter out = new PrintWriter("spainting.out");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N  = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] s = new int[N + 1];
        s[0] = 1;
        for (int i = 1; i < K; i++) {
            s[i] = s[i-1];
        }
        for (int i = K; i <= N; i++) {
            s[i] = s[i - 1] + (M - 1) * (s[i - K + 1] - s[i - 2]);
        }
        int ans = 1;
        for (int i = 1; i <= N; i++) {
            ans = (int) ((M * (long) ans) % MOD);
        }
        System.out.println(ans - (s[N]+ s[N-1]));
        out.close();
    }
}
