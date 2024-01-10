import java.util.*;
import java.io.*;
public class milena {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            long count = 0 ;
            for (int i = N - 1; i >= 1; i--) {
                int k= Math.ceilDiv(A[i-1] , A[i]);
                A[i-1] = Math.floorDiv(A[i-1], k);
                count += k - 1; 
            }
            System.out.println(count);
        }
        br.close();
    }
}