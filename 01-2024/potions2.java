import java.util.*;
import java.io.*;
public class potions2 {
    static ArrayList<ArrayList<Integer>> adj;
    static int[] p;
    static int N, ans, trav;
    static boolean[] visited;
    static HashMap<Integer, ArrayList<Integer>> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p =new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i =0; i < N; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken()) - 1;
            map.get(p[i]).add(i);
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
         trav = child(0);
        visited = new boolean[N];
        ans = 0;
        assign (0);
        System.out.println(ans);
        br.close();

    }
    static int assign(int x) {
        if (visited[x]) return 0;
        visited[x] = true;
        if (x != 0 && adj.get(x).size() == 1) {
            if (map.containsKey(x) && map.get(x).get(0) < trav) {
                ans++;
                return 0;
            }
            else return 1;
        }
        int count= 0 ;
        for (int i: adj.get(x)) {
            count += assign(i);
        }
        if (count > 0 && map.containsKey(x) && map.get(x) < trav){
            count --;
            ans++;
        }
        return count;

    }

    static int child(int x) {
        if (visited[x]) return 0;
        visited[x] = true;
        if (x != 0 && adj.get(x).size() == 1) {
            return 1;
        }
        int count =0 ;
        for (int i: adj.get(x)) {
            count += child(i);
        }
        return count;
    }
}