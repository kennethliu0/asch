import java.util.*;
import java.io.*;
public class barntree {
    static int[][] DP;
    static final int MOD = (int) 1e9 + 7;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adj;
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new FileReader("barntree.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter out = new PrintWriter("barntree.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        DP = new int[N][3];
        for (int i =0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            DP[b][c] = 1;
            if (c != 0) DP[b][0] = -1;
            if (c != 1) DP[b][1] = -1;
            if (c != 2) DP[b][2] = -1;
        }
        visited = new boolean[N];
        int ans = 0;
        if (DP[0][0] != -1) ans = DP(0,0);
        visited = new boolean[N];
        if (DP[0][1] != -1) ans = (ans + DP(0, 1)) % MOD;
        visited = new boolean[N];
        if (DP[0][2] != -1) ans = (ans + DP(0, 2)) % MOD;
        System.out.println(ans);
        br.close();
        // out.close
    }
    static int DP(int barn, int c) {
        if (DP[barn][c] > 0) return DP[barn][c];
        if (DP[barn][c] == -1) return 0;
        visited[barn] = true;
        int res = 1;
        for (int i: adj.get(barn)) {
            int total = 0;
            if (! visited[i]) {
                if (c != 0) total = (total + DP(i, 0)) % MOD;
                if (c != 1) total = (total + DP(i, 1)) % MOD;
                if (c != 2) total = (total + DP(i, 2)) % MOD;
                
            }
            res = ( res * total) % MOD;
        }
        DP[barn][c] = res;
        return res;
    }
}