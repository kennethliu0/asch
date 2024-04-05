import java.util.*;
import java.io.*;
public class sashaandcasino {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int curr = A;
            boolean ans = true;
            boolean plusOne = false;
            for (int i = 0; i < X; i++) {
                if ((A - curr + 1) % (K - 1) == 0) {
                    plusOne = true;
                }
                int bet = (A - curr + 1) / (K - 1);
                if (bet > curr || bet < 1) {
                    ans = false;
                    break;
                }
                curr -= bet;
                if (curr == 0) {
                    ans = false;
                    break;
                }
            }
            sj.add((ans && plusOne) ? "YES": "NO");
        }
        System.out.println(sj);
        br.close();
    }
}