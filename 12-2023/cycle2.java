import java.util.*;
import java.io.*;
public class cycle2 {
    static int K;
    static boolean[] visited;
    static int[] a, b;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[K];
        b = new int[K];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> uniques = new HashSet<Integer>();
        for (int i = 0; i < K; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            uniques.add(a[i]);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            uniques.add(b[i]);
        }
        int ans = N - uniques.size();
        visited = new boolean[N];
        for (int i = 0; i < K; i++) {
            if (! visited[a[i]])
            ans += dfs(a[i]);
        }
        for (int j = 0; j < K; j++) {
            if (! visited[b[j]])
            ans += dfs(b[j]);
        }
        System.out.println(ans);
        br.close();

    }
    static int dfs(int x) {
        if (visited[x]) return 0;
        visited[x] = true;
        return -1;
    }
    static int getNext(int i) {
        if (i == K - 1) return 0;
        else return i + 1;
    }
    static int getPrev(int i) {
        if (i == 0) return K - 1;
        else return i - 1;
    }
}