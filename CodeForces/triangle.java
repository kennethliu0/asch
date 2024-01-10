import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1906/M
public class triangle {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long M = 0;
        long S = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long a = Long.parseLong(st.nextToken());
            M = Math.max(M, a);
            S+= a;
        }
        if (M > 2 * (S - M)) System.out.println(S - M);
        else System.out.println(S / 3);
    }
}