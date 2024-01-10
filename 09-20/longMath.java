import java.util.*;
import java.io.*;


public class longMath {
   static class Point {
       int index;
       int val;
       public Point (int i, int v) {
           index = i;
           val = v;
       }
   }
   public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int N = Integer.parseInt(br.readLine());
       StringTokenizer st = new StringTokenizer(br.readLine());
       int[] A  =new int[N];
       HashSet<Integer> set = new HashSet<>();
       int mn = Integer.MAX_VALUE;
       int mx = Integer.MIN_VALUE;
       for (int i = 0; i < N; i++) {
           A[i] = Integer.parseInt(st.nextToken());
       }
       /* 
        for (int curr = 0; curr < N; curr++) {
            for (int inc = 1; inc < mx - mn; inc++) {
               Integer last = map.get(A[curr] - inc);
               if (last != null)
                   DP[curr][inc] = DP[last][inc] + 1;
           }
       } */
       /*
       for (int i = 0 ; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            DP[i][j] = A[i] - A[j];
        }
       }
       for (int i = 0; i < N; i++) {
        
       }
       for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
               max  =Math.max(max, DP[i][j]);
           }
       }
       for (int[] arr: DP) {
           System.out.println(Arrays.toString(arr));
       } */
       System.out.println(longestArithSeqLength(A));
   }
   public static int longestArithSeqLength(int[] A) {
    int res = 2, n = A.length;
    HashMap<Integer, Integer>[] dp = new HashMap[n];
    for (int j = 0; j < A.length; j++) {
        dp[j] = new HashMap<>();
        for (int i = 0; i < j; i++) {
            int d = A[j] - A[i];
            dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
            res = Math.max(res, dp[j].get(d));
        }
    }
    return res;
}
}

