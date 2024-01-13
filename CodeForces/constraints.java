import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1920/problem/A
public class constraints {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            int low = Integer.MIN_VALUE;
            int high = Integer.MAX_VALUE;
            TreeSet<Integer> except = new TreeSet<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    low = Math.max(low, x);
                    while (! except.isEmpty() && except.first() < low) {
                        except.remove(except.first());
                    }
                } else if (a == 2) {
                    high = Math.min(high, x);
                    while (! except.isEmpty() && except.last() > high) {
                        except.remove(except.last());
                    }
                } else if (a == 3) {
                    if (low <= x && high >= x) {
                        except.add(x);
                    }
                }
            }
            sj.add(Math.max(0, high - low + 1 - except.size() ) + "");
            
        }
        System.out.println(sj);
        br.close();
    }
}