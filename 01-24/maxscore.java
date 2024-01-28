import java.util.*;
import java.io.*;
public class maxscore {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int k = 0;
        while ((1 << k) < N) k++;
        int[][] table = new int[N][k + 1];
        for (int i = 0; i < N; i++) {
            table[i][0] = A[i];
        }

        for (int h = 1; h < k; h++) {
            for (int x = 0; x + (1 << h) <= N; x++) {
                if ((x + (1 << h - 1)) >= N) continue;
                table[x][h] = Math.max(table[x][h-1], table[x + (1 << (h - 1))][h-1]);
            }
        }
        // System.out.println(Arrays.deepToString(table));
        while (Q-->0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int max = Integer.MIN_VALUE;
            for (int j = k; j >= 0; j--) {
                if (X + (1 << j) - 1 <= Y) {
                    max  = Math.max(table[X][j], max);
                    X += (1 << j);
                }
            }
            if (max < 0) {
                System.out.println("N/A");
            } else {
                System.out.println(max);
            }
        }
        br.close();

    }
}