import java.util.*;
import java.io.*;
// https://usaco.org/index.php?page=viewproblem2&cpid=594
public class angrycowssilver {
    static int[] x;
    static int N, K;
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter out = new PrintWriter("angry.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        x = new int[N];
        for (int i= 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        Arrays.sort(x);
        int lo = 0;
        int hi = 500000000;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (check(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        out.println(lo);
        out.close();
    }
    static boolean check(int mid) {
        int blastCount = 0;
        int curr = x[0];
        int i = 0;
        while (curr < x[N - 1] && blastCount <= K) {
            blastCount++;
            curr = x[i] + 2 * mid;
            while (i < N && x[i] <= curr) i++;
        }
        return blastCount <= K;
    }
}