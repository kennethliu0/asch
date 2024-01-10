import java.util.*;
import java.io.*;
public class selectedges {
   static int[] arr;
   static ArrayList<TreeMap<Integer, Integer>> adj;
   static int[][] DP;
   public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int N = Integer.parseInt(br.readLine());
       StringTokenizer st = new StringTokenizer(br.readLine());
       arr = new int[N];
       DP = new int[N][2]; // 0 = don't take node preceeding, 1 =  take node preceeding
       for (int i = 0; i < N; i++) {
           DP[i][0] = -1;
           DP[i][1] = -1;
       }
        for (int i = 0; i < N; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
           if (arr[i] == 0) {
               DP[i][0] = 0;
               DP[i][1] = 0;
           }

        /*
         * Solution:
         * 
         */
       }
       adj = new ArrayList<>();
       for (int i = 0; i < N; i++)  {
           adj.add(new TreeMap<Integer, Integer>());
       }
       for (int i = 0; i < N - 1; i++) {
           st = new StringTokenizer(br.readLine());
           int a = Integer.parseInt(st.nextToken()) - 1;
           int b = Integer.parseInt(st.nextToken()) -1;
           int w = Integer.parseInt(st.nextToken());
           adj.get(a).put(b, w);
           adj.get(b).put(a, w);
       }
       // System.out.println(DP(1, 1, 0));
       System.out.println(DP(0, 0, -1));
       br.close();
   }
   static int DP(int node, int take, int parent) {
       if (DP[node][take] >= 0) return DP[node][take];
       int x = arr[node] - take;
       /*
       TreeSet<Integer> pq = new TreeSet<>((e1, e2) -> (Math.max(DP(e1, 0, node), DP( (int) e1, 1, node) + adj.get(e1).get(node)) -Math.max(DP(e2, 0, node), DP(e2, 1, node) + adj.get(e2).get(node))));
       for (int i: adj.get(node).keySet()) {
           if (i != parent) {
               DP(i, 0, node);
               DP(i, 1, node);
               pq.add(i);
           }
       }
       ArrayList<Integer> contained = new ArrayList<Integer>();
       DP[node][take] = 0;
       while (! pq.isEmpty() && x-->0) {
           DP[node][take] += Math.max(DP(pq.last(), 0, node), DP(pq.last(), 1, node));
           contained.add(pq.pollLast());
       }
       for (int i: adj.get(node).keySet()) {
           if (i != parent && ! contained.contains(i)) {
               DP[node][take] += Math.max(DP(i, 0, node), 0);
           }
       } */
       /*
       PriorityQueue<Data> takeEdge = new PriorityQueue<>();
       PriorityQueue<Data> emptyEdge = new PriorityQueue<>();
       for (int i: adj.get(node).keySet()) {
           if (i != parent) {
               takeEdge.offer(new Data(DP(i, 1, node) + adj.get(i).get(node), i));
               emptyEdge.offer(new Data(DP(i, 0, node), i));
           }
       }
       DP[node][take] = 0;
       ArrayList<Integer> contained = new ArrayList<Integer>();
       while (! takeEdge.isEmpty() && x-->0) {
           Data temp = takeEdge.poll();
           if (temp.val <= 0) break;
           contained.add(temp.i);
           DP[node][take] += temp.val;
       }
       while (! emptyEdge.isEmpty()) {
           Data temp = emptyEdge.poll();
           if (contained.contains(temp.i)) continue;
           if (temp.val <= 0) break;
           DP[node][take] += temp.val;
       }
       return DP[node][take]; */
       PriorityQueue<Data> pq = new PriorityQueue<>();
       HashSet<Integer> contained = new HashSet<>();
       for (int i: adj.get(node).keySet()) {
           if (i != parent) {
               pq.offer(new Data(DP(i,1 , node) + adj.get(i).get(node), i, 1));
               pq.offer(new Data(DP(i, 0, node), i, 0));
           }
       }
       DP[node][take] = 0;
       while (! pq.isEmpty() && x-->0) {
           Data temp = pq.poll();
           if (contained.contains(temp.i) || temp.val < 0) continue;
           DP[node][take] += temp.val;
           contained.add(temp.i);
       }
       /*
       while (! pq.isEmpty()) {
           Data temp = pq.poll();
           if (! contained.contains(temp.i)) {
               DP[node][take] += temp.val;
           } // else we already processed and added the subtree
       } */
        for (int i: adj.get(node).keySet()) {
            if (i != parent && ! contained.contains(i) && DP[i][0] > 0) {
                DP[node][take] += DP[i][0];
            }
        }
       return DP[node][take];
   }
   static class Data implements Comparable<Data>{
       int val;
       int i;
       int t;
       public Data (int v, int a, int take) {
           val = v;
           i = a;
           t = take;
       }
       @Override
       public int compareTo(Data o) {
           return ((Data) o).val - this.val;
       }
   }
}





