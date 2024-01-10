import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1900/problem/B
public class laura {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // if 2 values sum is even then they can be cancelled out no matter what
            String ans = "";
            if ((b + c) % 2 == 0) {
                ans+= "1 ";
            } else {
                ans += "0 ";
            }
            if ((a + c) % 2 == 0) {
                ans+= "1 ";
            } else {
                ans += "0 ";
            }
            if ((b + a) % 2 == 0) {
                ans+= "1";
            } else {
                ans += "0";
            }
            sj.add(ans);
        }
        System.out.println(sj);
        br.close();
    }
}