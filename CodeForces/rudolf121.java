import java.util.*;
import java.io.*;
public class rudolf121 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int next = 0;
            int nextnext =0 ;
            String ans = "yes";
            for (int i = 0; i + 2 < N; i++) {
                if (a[i] < 0) {
                    ans = "no";break;
                }
                next = a[i] * 2;
                nextnext = a[i];
                a[i] = 0;
                a[i+1] -= next;
                a[i+2] -= nextnext;
            }
            for (int i = 0; i < N; i++) {
                if (a[i] != 0) ans = "no";
            }   
            sj.add(ans);
        }
        System.out.println(sj);
        br.close();

    }
}