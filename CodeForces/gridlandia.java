import java.util.*;
import java.io.*;
public class gridlandia {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sj.add( Integer.toString (Math.min(N, M) + Math.abs(M - N)));
        }
        System.out.println(sj);
        br.close();
    }
}