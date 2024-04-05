import java.util.*;
import java.io.*;
public class moorbles {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] a =new int[M][K];
            int[] evenCosts = new int[M];
            int[] oddCosts = new int[M];
            Arrays.fill(evenCosts, Integer.MAX_VALUE);
            Arrays.fill(oddCosts, Integer.MAX_VALUE);
            // anticipate the worst possible outcome for each guess type at each turn
            for (int i =0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < K; j++) {
                    a[i][j] = Integer.parseInt(st.nextToken());
                    if (a[i][j] % 2 == 0) {
                        oddCosts[i] = Math.min(oddCosts[i], -a[i][j]);
                        evenCosts[i] = Math.min(evenCosts[i], a[i][j]);
                    } else {
                        oddCosts[i] = Math.min(oddCosts[i], a[i][j]);
                        evenCosts[i] = Math.min(evenCosts[i], -a[i][j]);
                    }
                }
            }
            // calculate the minimum possible number of marbles to end the game with a nonnegative number of marbles - therefore must end with 1
            int[] minSuffix = new int[M + 1];
            minSuffix[M] = 1;
            for (int i = M - 1; i >= 0; i--){ 
                minSuffix[i] = Math.max(1, minSuffix[i + 1] - Math.max(evenCosts[i], oddCosts[i]));
            }
            if (minSuffix[0] > N) {
                sj.add("-1");
                continue;
            }
            // check if even (which is lexographically smaller) is possible at each step
            StringJoiner ans = new StringJoiner(" ");
            for (int i =0; i < M; i++) {
                if (N + evenCosts[i] >= minSuffix[i+1]) {
                    N += evenCosts[i];
                    ans.add("Even");
                } else {
                    N += oddCosts[i];
                    ans.add("Odd");
                }
            }
            sj.add(ans.toString());
            
        }
        br.close();
        System.out.println(sj);
    }
}