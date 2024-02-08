import java.util.*;
import java.io.*;
public class potions2 {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adj;
    static int ans;
    static HashMap<Integer, Integer> map;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] p = new int[N];
        for (int i= 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i= 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        visited = new boolean[N];
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {map.put(i, 0);}
        int trav = child(0);
        ans = 0;
        for (int i= 0 ; i < trav; i++) {
            map.put(p[i], map.get(p[i]) + 1);
        }
        visited=  new boolean[N];
        solve(0);
        System.out.println(ans);
        br.close();

    }
    static int solve(int x) {
        if (visited[x] ) return 0;
        visited[x] = true;
        if (x != 0 && adj.get(x).size() == 1) {
            if (map.get(x) > 0) {
                ans++; return 0;
            } else{
                return 1;
            }
        }
        int count = 0;
        for (int i: adj.get(x)) {
            count += solve(i);
        }
        ans += Math.min(map.get(x), count);
        return count - Math.min(map.get(x), count);
        
    }
    static int child(int x ) {
        if (visited[x]) return 0;
        visited[x] = true;
        if (x != 0 && adj.get(x).size() == 1) return 1;
        int count = 0;
        for (int i: adj.get(x)) {
            count += child(i);
        }
        return count;
    }
}   
