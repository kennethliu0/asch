import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1398/problem/C
public class goodsubarrays {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            byte[] a = new byte[N + 1];
            for (int i = 1; i <= N; i++) {
                a[i] = (byte)  (s.charAt(i - 1) - '0');
            }
            // System.out.println(Arrays.toString(a));
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            int sum = 0;
            long ans =0;
            for (int i = 0; i <= N; i++) {
                sum += a[i];
                ans += map.getOrDefault(1 * (sum - i), 0);
                if (map.containsKey(sum - i)) {
                    map.put(sum - i, map.get(sum - i) + 1);
                } else {
                    map.put(sum - i, 1);
                }
            }
            System.out.println(ans);
    
        }
        br.close();
    }
}