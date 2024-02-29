import java.util.*;
import java.io.*;
public class holes {
static int N, M;
static int[] p, last, jumps;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p =new int[N + 1];
        st = new StringTokenizer(br.readLine());
        last = new int[N + 1];
        jumps = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i <= N / 330; i++) {
            initial(i);
        }
        StringJoiner sj = new StringJoiner("\n");
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int a = Integer.parseInt(st.nextToken());
                int numSteps = 0;
                int l = a;
                while (a <= N) {
                    numSteps += jumps[a];
                    l = last[a];
                    a = l + p[l];
                }
                sj.add((l) + " " + numSteps);
            } else { // type 0
                int x = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                p[x] = b;
                initial(x  / 330);
            }
        }
        System.out.println(sj);
        br.close();

    }
    static void initial(int x) {
        int left = Math.max(1, x * 330);
        int right = Math.min(N + 1, (x + 1)  * 330);
        for (int i = right - 1; i >= left; i--) {
            if (i + p[i] >= right) {
                last[i] = i;
                jumps[i] = 1;
            } else{
                last[i] = last[i + p[i]];
                jumps[i]= jumps[i + p[i]] + 1;
            }
        }
    }
}