import java.util.*;
import java.io.*;
public class maxcross {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter("maxcross.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] broken = new int[B];
        for (int i = 0; i < B; i++) {
            broken[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(broken);
        int[] ps = new int[N + 1];
        int j = 0;
        for (int i = 1; i <= N; i++) {
            if (j < B && broken[j] == i) {
                ps[i] = ps[i-1] + 1;
                j++;
            } else {
                ps[i] = ps[i-1];
            }
        }
        int ans = K;
        for (int i = 0; i <= N - K;i++) {
            ans = Math.min(ans, ps[i + K] - ps[i]);
        }
        out.println(ans);
        br.close();
        out.close();
    }
}