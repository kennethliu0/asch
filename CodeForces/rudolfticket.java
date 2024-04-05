import java.util.*;
import java.io.*;
public class rudolfticket {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> b = new ArrayList<Integer>();
            for (int i =0 ; i < N; i++) {
                b.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(b);
            st = new StringTokenizer(br.readLine());
            int ans= 0 ;
            for (int i = 0; i < M; i++) {
                int j= 0 ;
                int x = Integer.parseInt(st.nextToken());
                for (; j < N; j++) {
                    if (b.get(j) + x > K) break;
                }
                ans += j;
            }
            sj.add(Integer.toString(ans));
        }
        br.close();
        System.out.println(sj);
    }
}