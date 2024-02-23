import java.util.*;
import java.io.*;
public class testtubes2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            ArrayDeque<Integer> f = new ArrayDeque<>();
            ArrayDeque<Integer> s = new ArrayDeque<>();
            int ans=  0;
            StringJoiner sj1 = new StringJoiner("\n");
            for (char c: br.readLine().toCharArray()) {
                if (! f.isEmpty() && (c - '1') == f.peek()) continue;
                f.push(c - '1');
            }
            for (char c: br.readLine().toCharArray()) {
                if (! s.isEmpty() && (c - '1') == s.peek()) continue;
                s.push(c - '1');
            }
            ArrayDeque<Integer> lg =  f;
            ArrayDeque<Integer> sm = f;
            int lgnum = 1;
            int smnum = 1;
            if (f.size() > s.size()) {
                smnum = 2;
                sm = s;
            } else{
                lgnum = 2;
                lg = s;
            }
            ArrayDeque<Integer> t = new ArrayDeque<>();
            while (sm.size() > 1) {
                if (sm.peek() == lg.peek()) {
                    sm.poll();
                    sj1.add(smnum + " " + lgnum);
                    ans++;
                } else {
                    if (t.isEmpty()) t.push(sm.poll());
                    else sm.poll();
                    sj1.add(smnum + " " + 3);
                    ans++;
                }
            }
            if (sm.peek() == t.peek()) {
                sm.poll();
                sj1.add(smnum + " " + 3);
                ans++;
                sm.push(1 - t.peek()); // we need S to be opposite wastebin of T
            }
            while (lg.size() > 1) {
                if (lg.peek() == sm.peek()) {
                    lg.poll();
                    sj1.add(lgnum + " " + smnum);
                    ans++;
                } else {
                    if (t.isEmpty()) t.push(lg.poll());
                    else lg.poll();
                    sj1.add(lgnum + " 3");
                    ans++;
                }
            }
            if (lg.peek() == sm.peek()) {
                sm.poll();
                sj1.add(smnum + " " + lgnum);
                ans++;
            }
            if (! t.isEmpty()) {
                if (t.peek() == sm.peek() || sm.isEmpty()) {
                    t.poll();
                    sj1.add("3 " + smnum);
                    ans++;
                } else if (t.peek() == lg.peek() || lg.isEmpty()){
                    t.poll();
                    sj1.add("3 " + lgnum);
                    ans++;
                }
            }
    
            sj.add(Integer.toString(ans));
            if (P > 1) sj.add(sj1.toString());


        }
        System.out.println(sj);
        br.close();
    }
}