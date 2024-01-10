import java.util.*;
import java.io.*;
public class zelda2 {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            adj = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken()) - 1;
                int B = Integer.parseInt(st.nextToken()) - 1;
                adj.get(A). add(B);
                adj.get(B).add(A);
            }
            visited = new boolean[N];
            count = 0;

            dfs(0);
            sj.add(Integer.toString(count));
        }
        System.out.println(sj);
        br.close();
    }
    static void dfs(int i) {
        if (visited[i]) return;
        visited[i] = true;
        int x = 0;
        int bigJ = -1;
        int big = 0;
        for (int j: adj.get(i)) {
            if (visited[j]) continue;
            if (adj.get(j).size() > big) {
                big = adj.get(j).size();
                bigJ = j;
            }
        }
        count += x / 2;
    }
}