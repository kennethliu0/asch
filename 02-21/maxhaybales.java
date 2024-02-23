import java.util.*;
import java.io.*;
public class maxhaybales {
    // idea - implement gcd to reduce slope fractions and use hashmap instead of treemap
    static class Slope implements Comparable<Slope> {
        long dy;
        long dx;
        public Slope(long dy, long dx) {
            this.dy = dy;
            this.dx = dx;
        }
        public boolean equals(Slope s) {
            return this.dy * s.dx == this.dx * s.dy;
        }
        public int compareTo(Slope s) {
            return Long.compare(this.dy * s.dx, s.dy * this.dx);
        }
        public int hashCode() {
            return Long.hashCode(dy) + Long.hashCode(dx);
        }
    }
    static int gcd(int x, int y) {
        if (x % y == 0) return y;
        if (y % x == 0) return x;
        return gcd(Math.max(x, y) - Math.min(x, y), Math.min(x, y));
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] bales = new long[N][2];
        for (int i =0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bales[i][0] = Long.parseLong(st.nextToken());
            bales[i][1] = Long.parseLong(st.nextToken());
        }
        br.close();
        Arrays.sort(bales, (e1, e2) -> Long.compare(e1[0], e2[0]));
        int ans = 0;
        // vertical lines
        for (int i = 0; i < N - 1; i++) {
            int j = i + 1;
            while (bales[j][0] == bales[i][0]) j++;
            ans = Math.max(ans, j - i);
            i = j - 1;
        }
        for (int i = 0; i < N; i++) {
            TreeMap<Slope, Integer> slopes = new TreeMap<>();
            for (int j = i + 1; j < N; j++) {
                Slope slope= new Slope( (bales[j][1] - bales[i][1]) , (bales[j][0] - bales[i][0]));
                if (!slopes.containsKey(slope)) {
                    slopes.put(slope, 1);
                } else{
                    slopes.put(slope, slopes.get(slope) + 1);
                }
            }
            
            for (Integer e: slopes.values()) {
                ans = Math.max(ans, e + 1);
            }
        }
        System.out.println(ans);
    }
}

