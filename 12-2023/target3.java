import java.util.*;
import java.io.*;
public class target3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] targets = new int[T];
        HashSet<Integer> targetsSet = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
            targetsSet.add(targets[i]);
        }
        String s = br.readLine();
        int[] position = new int[C];
        if (s.charAt(0) == 'L') position[0] = -1;
        if (s.charAt(0) == 'R') position[0] = 1;
        for (int i = 1; i < C; i++) {
            if (s.charAt(i) == 'L') position[i] = position[i-1]-1;
            if (s.charAt(i) == 'R') position[i] =  position[i-1] + 1;
            if (s.charAt(i) == 'F') {
                position[i] =  position[i-1];
            }
        }
        int[][] numTargetsHit = new int[C][5];
        HashSet<Integer>[] targetsHit = new HashSet[5];
        for (int i = 0; i < 5; i++) {
            targetsHit[i] = new HashSet<Integer>();
        }
        for (int i = C - 1; i >= 0; i--) {
            if (s.charAt(i) == 'F') {
                for (int j = 0; j < 5; j++) {
                    if (targetsSet.contains(position[i] + j - 2)) {
                        if (! targetsHit[j].contains(position[i] + j  - 2)) {
                            targetsHit[j].add(position[i] + j - 2);
                            if (i != C - 1)
                                numTargetsHit[i][j] = numTargetsHit[i+1][j] + 1;
                            else 
                                numTargetsHit[i][j] = 1;
                        }
                    }
                }
            } else {
                if (i == C - 1) continue;
                for (int j = 0; j < 5; j++) {
                    numTargetsHit[i][j] = numTargetsHit[i+1][j];
                }
            }
        }
        int ans = numTargetsHit[0][2];
        for (int i = 0; i < 5; i++) {
            ans = Math.max(ans, numTargetsHit[C-1][i]);
        }
        for (int i = 1; i < C - 2; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.max(ans, numTargetsHit[i][j] + numTargetsHit[0][2] - numTargetsHit[i][2]);
            }
        }
        System.out.println(ans);
        br.close();
    }
}