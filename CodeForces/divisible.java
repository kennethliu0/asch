import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1931/D
public class divisible {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[][] mod = new int[N][2];
            for (int i = 0; i < N; i++) {
                int x  = Integer.parseInt(st.nextToken());
                mod[i][0] = x % X;
                mod[i][1] = x % Y;
            }
            Arrays.sort(mod, (e1, e2) -> e1[1] - e2[1]);
            long ans= 0 ;
            for (int i = 0; i < N; i++) {
                HashMap<Integer, Integer> modX = new HashMap<>();
                int j = i + 1;
                modX.put(mod[i][0], 1);
                while (j < N && mod[j][1] == mod[i][1]) {
                    if (! modX.containsKey(mod[j][0])) modX.put(mod[j][0], 0);
                    modX.put(mod[j][0], modX.get(mod[j][0]) + 1);
                    j++;
                }
                long temp = 0;
                for (int k =i; k < j; k++) {
                    if (modX.containsKey((X- mod[k][0]) % X)) {
                        temp += modX.get((X- mod[k][0]) % X);
                        if (mod[k][0] == (X- mod[k][0]) % X) temp--;
                    }

                }
                ans += temp / 2;
                i = j - 1;
            }
            sj.add(Long.toString(ans));
        }
        System.out.println(sj);
        br.close();
    }
}