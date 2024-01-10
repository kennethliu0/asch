import java.util.*;
import java.io.*;
public class target {
    static int T,  C;
    static HashSet<Integer> targets;
    static int[] position, shots;
    static String s;
    static HashMap<Integer, Integer> hits;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        targets = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            targets.add(Integer.parseInt(st.nextToken()));
        }
        s = br.readLine();
        position = new int[C]; shots = new int[C]; 
        if (s.charAt(0) == 'L') position[0] = -1;
        if (s.charAt(0) == 'R') position[0] = 1;
        Arrays.fill(shots, -1);
        for (int i = 1; i < C; i++) {
            if (s.charAt(i) == 'L') position[i] = position[i-1]-1;
            if (s.charAt(i) == 'R') position[i] =  position[i-1] + 1;
            if (s.charAt(i) == 'F') {
                position[i] =  position[i-1];
                shots[i] = C + position[i];
            }
        }   
        hits = new HashMap<>();
        ans = 0;
        dp(0, 0);
        System.out.println(ans);
        br.close();

    }
    static void dp(int i, int offset) {
        if (i == C) {
            int count = 0;
            for (int j: targets) {
                if (hits.containsKey(j)) count++;
            }
            ans = Math.max(ans, count);
            return;
        }
        // don't change anything - follow instructions
        if (s.charAt(i) == 'F') {
            addShot(shots[i] - C + offset);
            dp(i + 1, offset);
            removeShot(shots[i] - C + offset);
        } else {
            dp(i + 1, offset);
        }
        // can mix it up
        if (offset == 0) {
            if (s.charAt(i) == 'L') {
                dp(i + 1, 2); // go right
                // shoot
                addShot(position[i] + 1);
                dp(i + 1, 1);
                removeShot(position[i] + 1);
            }
            if (s.charAt(i) == 'R') {
                dp(i + 1, -2); // go left
                // shoot
                addShot(position[i] - 1);
                dp(i + 1, -1);
                removeShot(position[i] - 1);
            }
            if (s.charAt(i) == 'F') {
                dp(i + 1, -1); // go left
                dp(i + 1,1); // go right
            }
        }
    }
    static void addShot(int i) {
        if (hits.containsKey(i)) {
            hits.put(i, hits.get(i) + 1);
        } else {
            hits.put(i, 1);
        }
    }
    static void removeShot(int i ) {
        if (hits.get(i) > 1) {
            hits.put(i, hits.get(i) - 1);
        } else{
            hits.remove(i);
        }
    }
}