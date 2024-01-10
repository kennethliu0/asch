import java.util.*;
import java.io.*;
public class moosubarray {
    static class Rect implements Comparable<Rect> {
        int adj;
        int width;
        public  Rect(int a, int w) {
            adj = a;
            width = w;
        }
        public int compareTo(Rect r) {
            return r.width - this.width;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        int[][][] pf = new int[10][M][N];
        for (int i = 0; i < M; i++) {
            pf[arr[i][0]][i][0] = 1;
            for (int j = 1; j < N; j++) {
                pf[arr[i][j]][i][j] = pf[arr[i][j]][i][j - 1] + 1;
            }
        }
        int ans = 0;
        for (int digit = 0; digit < 10; digit++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Rect> pq = new PriorityQueue<>();
                int adj = 0;
                for (int i = 0; i < M; i++) {
                    if (arr[i][j] != digit) {
                        while (! pq.isEmpty()) {
                            ans = Math.max(ans, pq.peek().width * (i +  pq.poll().adj));
                        }
                        // pq.clear();
                        adj--;
                        continue;
                    }
                    int len = pf[digit][i][j];
                    int pastadj = adj;
                    while (! pq.isEmpty() && pq.peek().width > len) {
                        pastadj = Math.max(pastadj, pq.peek().adj);
                        ans = Math.max(ans, pq.peek().width * (i + pq.poll().adj));
                    }
                    if (pq.isEmpty() || pq.peek().width < len) {
                        pq.offer(new Rect(pastadj, len));
                    }
                    adj--;
                }
                while (!pq.isEmpty()) {
                    ans = Math.max(ans, pq.peek().width * (M+ pq.poll().adj));
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}