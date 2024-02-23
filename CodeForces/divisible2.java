import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1931/problem/D
public class divisible2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            long ans= 0 ;
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                int xx = num % X;
                int yy = num % Y;
                if (map.containsKey((X - xx) % X) && map.get((X - xx) % X).containsKey(yy))
                    ans += map.get((X - xx) % X).get(yy);
                if (! map.containsKey(xx)) map.put(xx, new HashMap<>());
                if (! map.get(xx).containsKey(yy)) {
                    map.get(xx).put(yy, 0);
                }
                map.get(xx).put(yy, map.get(xx).get(yy) + 1);
            }
            sj.add(Long.toString(ans));
        }
        System.out.println(sj);
        br.close();
    }
}