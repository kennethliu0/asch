import java.io.*;
import java.util.*;
// https://codeforces.com/problemset/problem/1851/E
public class potions {
    static int N, K;
    static long[] cost;
    static ArrayList<ArrayList<Integer>> brewing;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cost[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                cost[Integer.parseInt(st.nextToken()) - 1] = 0;
            }
            brewing = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                brewing.add(new ArrayList<Integer>());
                st = new StringTokenizer(br.readLine());
                int size = Integer.parseInt(st.nextToken());
                if (size != 0) {
                    while (st.hasMoreTokens()) {
                        brewing.get(i).add(Integer.parseInt(st.nextToken()) - 1);
                    }
                }
                
            }
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (! visited[i]) {
                    dfs(i);
                }
            }
            for (int i = 0; i < N; i++) {
                sb.append(cost[i] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static void dfs(int i) {
        visited[i] = true;
        if (cost[i] == 0 || brewing.get(i).isEmpty()) {
            return;
        } else {
            long total = 0;
            for (int j: brewing.get(i)) {
                if (visited[j]) {
                    total += cost[j];
                } else {
                    dfs(j);
                    total += cost[j];
                }
            }
            cost[i] = Math.min(cost[i], total);
        
        }
    }
}