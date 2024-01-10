import java.util.*;
import java.io.*;
public class talent {
    static class Team {
        int talent, weight;
        public Team (int t, int w) {
            talent = t;
            weight = w;
        }
        public double getScore() {
            if (weight == 0) return Double.MIN_VALUE;
            return (double) talent / weight;
        }
    }
    static int N, W;
    static int[] weight, talent;
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("talent.in"));
        PrintWriter out = new PrintWriter("talent.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        weight = new int[N + 1];
        talent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            talent[i] = Integer.parseInt(st.nextToken());
        }
        /*
        Team[][] DP = new Team[N + 1][N + 1];
        int max = 0;
        for (int j = 0; j <= N; j++) {
            DP[0][j] = new Team(0, 0);
        }
        for (int i = 0; i <= N; i++) {
                DP[i][0] = new Team(0,0);
            
        }
        for (int i = 1; i <= N; i++) { // last considered
            for (int j = 1; j <= N; j++) { // int length
                Team excluded = new Team(DP[i - 1][j].talent, DP[i - 1][j].weight);
                Team included = new Team(DP[i-1][j - 1].talent + talent[i], DP[i-1][j-1].weight + weight[i]);
                if (excluded.getScore() >= included.getScore()) {
                    DP[i][j] = excluded;
                } else {
                    DP[i][j] = included;
                }
            }
        }
        for(Team[] r: DP) {
            for (Team t: r) {
                max  = Math.max(max, (int) (t.getScore() * 1000));
            }
        }
        System.out.println(max); */
        int lo = 0;
        int hi = 250 * 1000 * 1000 + 1;
        // DP on the best ratio
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (check(mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        out.println(lo);
        out.close();
        br.close();
    }
    static boolean check(int y) {
        long[] DP = new long[W + 1];
        for (int i = 1; i <= W; i++) {
            DP[i] = Integer.MIN_VALUE;
        }
        for (int j = 1; j <= N; j++) {
            long value = 1000 *(long) talent[j] - y * (long) weight[j];
            int inc = weight[j];
            for (int k = W; k >= 0; k--) {
                int k1 = Math.min(W, k + inc);
                if (DP[k] != Integer.MIN_VALUE) {
                    if (DP[k1] < DP[k] + value) {
                        DP[k1] = DP[k] + value;
                    }
                }
            }
        }
        return DP[W] >= 0;
    }
}