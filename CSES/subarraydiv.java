import java.util.*;
import java.io.*;
// https://cses.fi/problemset/task/1662
public class subarraydiv {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) % N;
        }
        long[] sums = new long[N];
        long sum = 0;
        long total = 0;
        for (int i: arr) {
            sum += i;
            sum = ((sum) % N + N ) % N;
            total += sums[(int) sum];
            sums[(int)sum]++;
        }
        System.out.println(total);
        br.close();
    }
}