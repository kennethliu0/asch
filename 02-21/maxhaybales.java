import java.util.*;
import java.io.*;
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
            return hashCode() == s.hashCode();
        }
        public int compareTo(Object s) {
            assert s instanceof Slope;
            Slope tmp = (Slope)s;
            return Long.compare(this.dy * tmp.dx, tmp.dy * this.dx);
        }
        @Override
        public int hashCode() {
            return Long.hashCode(dy * 1000000001 + dx);
        }
    }
    static long gcd(long x, long y) {
        while (y != 0) {
            long tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;

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
            HashMap<Slope, Integer> slopes = new HashMap<>();
            for (int j = i + 1; j < N; j++) {
                Slope slope= new Slope( (bales[j][1] - bales[i][1]) , (bales[j][0] - bales[i][0]));
                if (!slopes.containsKey(slope)) {
                    slopes.put(slope, 1);
                } else{
                    int val = slopes.get(slope) + 1;
                    slopes.put(slope, val);
                    ans = Math.max(ans, val);
                }
            }
        }
        System.out.println(ans);
    }
}

