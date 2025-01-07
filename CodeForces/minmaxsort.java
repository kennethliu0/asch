import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1792/problem/C
public class minmaxsort {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] l = new int[N + 1];
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                l[a[i]] = l[a[i] - 1] + 1;
            }
            int maxNum=1;
            for (int i = 2; i <= N; i++) {
                if (l[i] > l[maxNum]) maxNum = i;
            }
            sj.add(Math.max(N - (maxNum + 1) + 1, (maxNum - l[maxNum])) +  "");

        }
        System.out.println(sj);
        br.close();
    }
}