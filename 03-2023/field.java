import java.util.*;
import java.io.*;
public class field {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        for (int i = 0;i < N; i++) {
            String s = br.readLine();
            int total =0 ;
            for (char c: s.toCharArray()) {
                if (c == 'H') {
                    total ^= 1;
                }
                total = total << 1;
            }
            total = total >> 1;
            nums[i] = total;
        }
        int[] dist = new int[1 << C];
        Arrays.fill(dist, C);
        for (int x: nums) {
            dist[x] = 0;
        }
        for (int j = 0; j < C; j++) {
            for (int i= 0; i < (1 << C); i++) {
                dist[i ^ (1 << j)] = Math.min(dist[i ^ (1 << j)], dist[i] + 1);
            }
        }
        for (int x: nums) {
            System.out.println(C - dist[x ^ ((1 << C) - 1)]);
        }
        br.close();
    }
}