import java.util.*;
import java.io.*;
public class targetsol {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        HashSet<Integer> targets = new HashSet<Integer>();
        st = new StringTokenizer(br.readLine());
        for (int i =0; i < T; i++) {
            targets.add(Integer.parseInt(st.nextToken()));
        }
        String commands = br.readLine();
        int finalPosition = 0;
        TreeMap<Integer, Integer> before = new TreeMap<>();
        for (char c: commands.toCharArray()) {
            if (c == 'L') finalPosition--;
            if (c == 'R') finalPosition++;
            if (c == 'F' && targets.contains(finalPosition)) {
                if (before.containsKey(finalPosition)) {
                    before.put(finalPosition, before.get(finalPosition) + 1);
                } else {
                    before.put(finalPosition, 1);
                }
            }
        }
        TreeMap<Integer, Integer>[] after = new TreeMap[5];
        for (int i = 0; i < 5; i++) {
            after[i] = new TreeMap<Integer, Integer>(); // offset by 2
        }
        TreeSet<Integer>[] bAndA = new TreeSet[5];
        for (int i =0; i < 5; i++) {
            bAndA[i] = new TreeSet<Integer>();
        }
        int ans = before.size();
        for (int i = commands.length() - 1; i >= 0; i--) {
            if (commands.charAt(i) == 'F') {
                if (before.containsKey(finalPosition)) {
                    if (before.get(finalPosition) > 1) {
                    before.put(finalPosition, before.get(finalPosition) - 1);
                } else {
                    before.remove(finalPosition);
                    for (int j = 0; j < 5; j++) {
                        bAndA[j].remove(finalPosition);
                    }
                }
                }
                
                for (int j = 0; j < 5; j++) {
                    int shot = j - 2 + finalPosition;
                    if (targets.contains(shot)) {
                        if (after[j].containsKey(shot)) {
                            after[j].put(shot, after[j].get(shot) + 1);
                        } else{
                            after[j].put(shot, 1);
                        }
                        if (before.containsKey(shot)) {
                            bAndA[j].add(shot);
                        }
                    }
                }
                ans = Math.max(ans, before.size() + after[1].size() - bAndA[1].size()); // left
                ans = Math.max(ans, before.size() + after[3].size() - bAndA[3].size()); // right

            } else if (commands.charAt(i) == 'R') {
                finalPosition--;
                ans = Math.max(ans, before.size() + after[0].size() - bAndA[0].size()); // left
                // fire
                if (targets.contains(finalPosition) && ! before.containsKey(finalPosition) && ! after[1].containsKey(finalPosition)) {
                    ans = Math.max(ans, before.size() + after[1].size() - bAndA[1].size() + 1);
                } else {
                    ans = Math.max(ans, before.size() + after[1].size() - bAndA[1].size());
                }

            } else { // Left
                finalPosition++;
                ans = Math.max(ans, before.size() + after[4].size() - bAndA[4].size()); // right
                // fire
                if (targets.contains(finalPosition) &&  ! before.containsKey(finalPosition) && ! after[3].containsKey(finalPosition)) {
                    ans = Math.max(ans, before.size() + after[3].size() - bAndA[3].size() + 1);
                } else {
                    ans = Math.max(ans, before.size() + after[3].size() - bAndA[3].size());
                }

            }
            
        }
        for (int i = 1; i <=3; i++) {
            ans = Math.max(ans, after[i].size());
        }
        System.out.println(ans);
        br.close();

    }
}