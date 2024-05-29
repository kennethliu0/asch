import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class foursum {
    static class Pair {
        int x; int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a =new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        HashMap<Integer, Pair> map = new HashMap<>();
        String ans = "IMPOSSIBLE";
        out:
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map.containsKey(X - a[i] - a[j])) {
                    Pair p = map.get(X- a[i] - a[j]); 
                    if (! (p.x == i || p.y ==i || p.x == j || p.y == j) ) {
                        ans = (1 + p.x) + " " + (1 + p.y) + " " + (1+  i) + " " + (1 + j);
                        break out;
                    }
                }
                if (! map.containsKey(a[i] + a[j])) {
                    map.put(a[i] + a[j], new Pair(i, j));
                }
            }
        }
        System.out.println(ans);
    }
}