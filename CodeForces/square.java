import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1921/problem/A
public class square {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int ans = Integer.MAX_VALUE;
            for (int i =0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                ans = Math.min(ans, getArea(x, Integer.parseInt(st.nextToken()), y, Integer.parseInt(st.nextToken())));
            }
            System.out.println(ans);
        }
        br.close();
    }
    static int getArea(int x1, int x2, int y1, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2-y1) * (y2-y1);
    }
}