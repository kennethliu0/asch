import java.util.*;
import java.io.*;
public class verydiff {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            int[] b = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++ ) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            Arrays.sort(b);
            int startA = 0, endA = N - 1, startB = M - 1, endB = 0;
            while (startA < endA) {

            }
            if (startA == endA) {
                
            }
        }
    }
}