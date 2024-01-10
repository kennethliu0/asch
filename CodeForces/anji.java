import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1900/problem/C
public class anji {
    static int[][] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            tree = new int[N + 1][3];
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                tree[i][0] = Integer.parseInt(st.nextToken()); // Left Node
                tree[i][1]=  Integer.parseInt(st.nextToken()); // Right Node
                tree[i][2] = s.charAt(i - 1); // Direction
            }
            /*
            for (int[] a: tree) {
                System.out.println(Arrays.toString(a));
            }
            */
            System.out.println(DP(1)); // Tree is 1 indexed
        }
        br.close();
    }
    static int DP(int i) {
        if (i == 0) return 500000; // no node here -> max value
        if (tree[i][0] == 0 && tree[i][1] == 0) return 0; // no children means no more moves
        int ans = 500000;
        if (tree[i][0] > 0) { // check tht left exists
            if (tree[i][2] == 76) { // left is free
                ans = Math.min(ans, DP(tree[i][0]));
            } else {
                ans = Math.min(ans, DP(tree[i][0]) + 1);
            }
        }
        if (tree[i][1] > 0) { // check that right exists
            if (tree[i][2] == 82) { // right is free
                ans = Math.min(ans, DP(tree[i][1]));
            } else{
                ans = Math.min(ans, DP(tree[i][1]) + 1);            
            }
        }
        return ans;
    }
}