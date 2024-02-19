import java.util.*;
import java.io.*;
// https://codeforces.com/gym/104114/problem/N
public class nusret {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] s = new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N - 1; i++) {
            s[i  + 1] = Math.max(s[i] - M, s[i + 1]);
        }
        for (int i =N - 1; i > 0; i--) {
            s[i-1] = Math.max(s[i] - M, s[i - 1]);

        }
        StringJoiner sj = new StringJoiner(" ");
        for (int i: s) {
            sj.add(Integer.toString(i));
        }
        System.out.println(sj);
    }
}