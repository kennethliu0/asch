import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class maxhaybales {
    // idea - implement gcd to reduce slope fractions and use hashmap instead of treemap
    static class Slope implements Comparable<Object> {
        long dy;
        long dx;
        public Slope(long dy, long dx) {
            long gcd = gcd(Math.max(dx, dy), Math.min(dx, dy));
            this.dy = dy / gcd;
            this.dx = dx / gcd;
        }
        public boolean equals(Object s) {// make sure this is alkways Object
            return this.dy == ((Slope) s).dy && this.dx == ((Slope) s).dx;
        }
        public int compareTo(Object s) {
            assert s instanceof Slope;
            Slope tmp = (Slope)s;
            return Long.compare(this.dy * tmp.dx, tmp.dy * this.dx);
        }
        @Override
        public int hashCode() {
            return Long.hashCode(dy * 1003L + dx);
        }
    }
    static long gcd(long x, long y) {
        return y ==0 ? x: gcd(y, x % y);

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
        int ans = 0;
        for (int i = 0; i < N; i++) {
            HashMap<Slope, Integer> slopes = new HashMap<>();
            for (int j = i + 1; j < N; j++) {
                Slope slope= new Slope( (bales[j][1] - bales[i][1]) , (bales[j][0] - bales[i][0]));
                slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                ans = Math.max(ans, slopes.get(slope));
            }
        }
        System.out.println(ans + 1);
    }
}

