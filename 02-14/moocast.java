import java.util.*;
import java.io.*;
// https://usaco.org/index.php?page=viewproblem2&cpid=669
public class moocast {
    static int[][] cows;
    static class Edge implements Comparable<Edge>{
        int a; int b; int dist;
        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
            this.dist = (cows[a][0] - cows[b][0]) * (cows[a][0] - cows[b][0]) + (cows[a][1] - cows[b][1]) * (cows[a][1] - cows[b][1]);
        }
        public int compareTo(Edge e) {
            return Integer.compare(dist, e.dist);
        }
    }
    static int[] root;
    static int[] size;
    static int[] height;
    static int setSize;
    static int minCost;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        int N = Integer.parseInt(br.readLine());
        cows = new int[N][2];
        for (int i =0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i][0 ] =Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                edges.add(new Edge(i, j));
            }
        }
        root = new int[N];
        size = new int[N];
        height = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            size[i] = 1;
            height[i] = 0;
        }
        setSize = N;
        minCost = 0;
        while (setSize > 1) {
            if (merge(edges.peek().a, edges.peek().b)) {
                minCost = Math.max(minCost, edges.poll().dist);
                setSize--;
            }else {
                edges.poll();
            }
            
        }
        PrintWriter out = new PrintWriter("moocast.out");
        out.println(minCost);
        out.close();
        
    }
    public static int find(int x) {
        if (root[x ] == x) return x;
        else {
            int parent = find(root[x]);
            root[x] = parent;
            return parent;
        }

    }
    public static boolean merge(int x, int y) {
        int xparent = find(x);
        int yparent = find(y);
        if (xparent == yparent) return false;
        if (height[xparent] > height[yparent]) {
            root[y] = xparent;
            root[yparent ] = xparent;
            size[xparent] += size[yparent];
        } else {
            root[x] = yparent;
            root[xparent] = yparent;
            size[yparent] += size[xparent];
            if (height[xparent] == height[yparent]) height[yparent]++;
        } 
        return true;

    }
}