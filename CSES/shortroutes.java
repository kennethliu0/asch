import java.util.*;
import java.io.*;
public class shortroutes {
    static class Node implements Comparable<Object>{
        int cost;
        int b;
        public Node(int cost, int b) {
            this.cost = cost;
            this.b = b;
        }

        public int compareTo(Object obj) {
            Node n = (Node) obj;
            return Integer.compare(this.cost, n.cost);
        }

    }
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(null, "System.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer[]>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer[]>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(new Integer[] {b, Integer.parseInt(st.nextToken())});
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        boolean[] visitedArr = new boolean[N];
        
        int[] mincost = new int[N];
        while (visited.size() != N) {
            while (visitedArr[pq.peek().b]) {
                pq.poll();
            }
            Node n = pq.poll();
            visitedArr[n.b] = true;
            visited.add(n.b);
            for (Integer[] i: adj.get(n.b)) {
                if (! visitedArr[i[0]]) {
                    pq.offer(new Node(i[0], i[1] + n.cost));
                }
            }
        }
    }
}
