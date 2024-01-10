import java.util.*;
import java.io.*;
public class target2 {
    static int T,  C;
    static HashMap<Integer, Integer> targets;
    static int[] position, shots;
    static String s;
    static HashMap<Integer, Integer>[] hits;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        targets = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int[] tArr = new int[T];
        for (int i = 0; i < T; i++) {
            tArr[i] =Integer.parseInt(st.nextToken()); 
            targets.put(tArr[i], i);
        }
        s = br.readLine();
        position = new int[C]; 
        if (s.charAt(0) == 'L') position[0] = -1;
        if (s.charAt(0) == 'R') position[0] = 1;
        for (int i = 1; i < C; i++) {
            if (s.charAt(i) == 'L') position[i] = position[i-1]-1;
            if (s.charAt(i) == 'R') position[i] =  position[i-1] + 1;
            if (s.charAt(i) == 'F') {
                position[i] =  position[i-1];
            }
        }
        int[][] targetsHit = new int[5][T];
        for (int i = C - 1; i >= 0; i--) {
            if (s.charAt(i) == 'F') {
                for (int o = -2; o <= 2; o++) {
                    if (targets.containsKey(position[i] + o)) targetsHit[o + 2][targets.get(position[i] + 0)]++;
                }
            }
        }   

        br.close();
    }
}