import java.util.*;
import java.io.*;
public class skyline {
    static int[] arr;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        arr = new int[1001];
        Arrays.fill(arr, -1);
        arr[0] = 1;
        while (Q-->0) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(DP(N));
        }
    }
    static int DP(int x) {
        if (arr[x] > -1) return arr[x];
        int total = 0;
        for (int i= 1; i <= x; i++) {
            total +=DP(i-1) * (DP(x - i));
            total %= MOD;
        }
        arr[x] = total;
        return arr[x];
    }

}