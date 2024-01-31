import java.util.*;
import java.io.*;
public class potions {
    static  int[] p;
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visitedp;
    static boolean[] visitedc;
    static HashSet<Integer> childs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        p = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        visitedp = new boolean[N];
        visitedc = new boolean[N];
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            adj.get(A).add(B);
            adj.get(B).add(A);
        }
        childs = new HashSet<Integer>();
        visitedp[0] = true;
        visitedc[0] = true;
        int count = 0;
        for (int i = 0; i < child(0); i++) {
            if (childs.contains(p[i])) count++;
        }
        System.out.println(count);
        br.close();
    }
    static int child(int x) {
        if (adj.get(x).size() == 1) {
            childs.add(x);
            return 1;
        }
        int count = 0;
        visitedc[x] = true;
        for (int i: adj.get(x)) {
            if (visitedc[i]) continue;
            count += child(i);
        }
        return count;
    }
}