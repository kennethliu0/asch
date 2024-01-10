import java.util.*;
import java.io.*;
public class cowdance {
    static int[][] shows;
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("02.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        shows = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            shows[i][0] = Integer.parseInt(st.nextToken());
            shows[i][1] = Integer.parseInt(st.nextToken());
            shows[i][2] = Integer.parseInt(st.nextToken());
        }
        int[][] DP = new int[N][K + 1];
        Arrays.sort(shows, (e1, e2) -> (e1[0] == e2[0]) ? (e1[1] - e2[1]) : (e1[0] - e2[0]));
        System.out.println(DP(0, 0));
        br.close();
        }
    
    static int DP(int i, int k) {
        if (k == K) return 0;
        if (i == N) return 0;
        //if (i == N)
        int res = DP(i + 1, k);
        int lo = 0;
        int hi = N - 1;
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (shows[mid][0] > shows[i][1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        if (shows[lo][0] > shows[i][1])
            res = Math.max(res, DP(lo, k + 1) + shows[i][2]);
        
        return res;
    }
    
}