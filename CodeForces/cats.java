import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1921/problem/B
public class cats {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            String f = br.readLine();
            int add= 0;
            int remove = 0;
            for (int i = 0; i < N; i++) {
                if (s.charAt(i) == f.charAt(i)) continue;
                if (s.charAt(i) == '1') remove++;
                else add++;
            }
            System.out.println(Math.max(add, remove));
        }
        br.close();
    }
}