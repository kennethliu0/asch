import java.util.*;
import java.io.*;
// https://cses.fi/problemset/task/1661
public class subarray2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        HashMap<Long, Integer> sums = new HashMap<Long, Integer>();
        long sum = 0;
        long total = 0;
        for (int i: arr) {
            sum += i;
            total += sums.getOrDefault(sum - X, 0);
            if (sums.containsKey(sum)) {
                sums.put(sum, sums.get(sum) + 1);
            } else {
                sums.put(sum, 1);
            }

        }
        System.out.println(total);
        br.close();
    }
}