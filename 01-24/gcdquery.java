import java.util.*;
import java.io.*;
public class gcdquery {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int K = 0;
        while (N > (1 << K)) K++;
        int[][] stable = new int[N][K+ 1];
        for (int i = 0; i < N; i++) {
            stable[i][0] = A[i];
        }
        HashMap<Integer, Integer> gcds = new HashMap<>();
        for (int h = 1; h < K; h++) {
            for (int x = 0; x + (1 << (h-1)) < N; x++) {
                stable[x][h] = gcd(stable[x][h-1], stable[x + (1 << (h-1))][h-1]);
                if (gcds.containsKey(stable[x][h])) {
                    gcds.put(stable[x][h], gcds.get(stable[x][h]) + 1);
                } else {
                    gcds.put(stable[x][h], 1);
                }
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        while (Q-->0) {
            int T = Integer.parseInt(br.readLine());
            sj.add(gcds.getOrDefault(T, 0) + "");

        }
        System.out.println(sj);
        br.close();
    }
    static int gcd(int a, int b) {
        if (a == 0 || b == 0) return Math.max(a, b);
        if (a <= b) b -= a;
        if (b <= a) a -= b;
        return gcd(a, b);
    }
}