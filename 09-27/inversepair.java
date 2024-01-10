import java.util.*;
import java.io.*;
public class inversepair {
    final static int MOD = (int) (1e9 + 7);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }    
        // TreeMap<Integer, Integer> dist = new TreeMap<Integer, Integer>();
        int[][] pmax=  new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    pmax[i][j] = (A[i] > A[j])? 1: 0;
                } else {
                    pmax[i][j] = pmax[i - 1][j] + ((A[i] > A[j]) ? 1 : 0);
                }
            }
        }
        int[][] DP = new int[N][N];
        for (int i = 0; i < N; i++) {
            /*
            dist = new TreeMap<Integer, Integer>();
            if (! dist.containsKey(A[i]))
                    dist.put(A[i], 1);
                else
                    dist.put(A[i], dist.get(A[i]) + 1);
            */
            for (int j = i + 1; j < N; j++) {
                int total = 0;
                /*
                if (! dist.containsKey(A[j]))
                    dist.put(A[j], 1);
                else
                    dist.put(A[j], dist.get(A[j]) + 1);
                Integer max = A[j];
                while (dist.higherKey(max) != null) {
                    max = dist.higherKey(max);
                    total += dist.get(max);
                } 
                DP[i][j] = DP[i][j-1] + total;  */
                if (i != 0)
                    DP[i][j] = DP[i][j-1] +  pmax[j][j] - pmax[i - 1][j];
                else
                    DP[i][j] = DP[i][j-1] +  pmax[j][j];
            }
            /*
            dist.put(A[i], dist.get(A[i]) - 1);
            if (dist.get(A[i]) == 0)
                dist.remove(A[i]); */
        }
        int total = 0;
        while (Q-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            total += (DP[a][b]);
            total %= MOD;
        }
        System.out.println(total);
        br.close();
    }
}