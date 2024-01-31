import java.util.*;
import java.io.*;
public class cowmptency {
    static class Pair {
        int a;
        int h;
        public Pair (int a, int h) {
            this.a = a;
            this.h = h;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] cows = new int[N];
            for (int i = 0; i < N; i++) {
                cows[i] = Integer.parseInt(st.nextToken());
            }
            Pair[] mem = new Pair[Q];
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                mem[i] = new Pair (Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1) ;
            }
            Arrays.sort(mem, (e1, e2) -> (e1.h - e2.h == 0) ? e1.a - e2.a : e1.h - e2.h);
            boolean possible = true;
            int max = 1;
            int maxJ = 0;
            int[] pmax = new int[N];
            Arrays.fill(pmax, 1);
            int pindex = 0;
            ArrayDeque<Integer> empty = new ArrayDeque<Integer>();
            for (int i = 0; i < Q; i++) {
                for (; maxJ < mem[i].h; maxJ++) {
                    max = Math.max(max, cows[maxJ]);
                }
                maxJ--;
                for (; pindex <= mem[i].a; pindex++) {
                    if (cows[maxJ] == 0) {
                        empty.addFirst(maxJ);
                    }
                    if (pindex == 0) pmax[0] = Math.max(1, cows[0]);
                    else pmax[pindex] = Math.max(pmax[pindex-1], cows[pindex]);
                }
                pindex--;
                if (pmax[mem[i].a] != max) {
                    if (empty.isEmpty()) {
                       possible = false;
                        break;
                    }
                    for (int j = empty.getFirst(); j <= mem[i].a; j++) {
                        pmax[j] = max;
                    }
                    cows[empty.removeFirst()] = max;
                }
                if (cows[mem[i].h] == 0)
                    cows[mem[i].h] = max + 1;
                if (cows[mem[i].h] > C || cows[mem[i].h] < max + 1)  {
                    possible = false;
                    break;
                }
                
            }
            Arrays.sort(mem, (e1, e2) -> e1.a - e2.a);
            max = 1;
            maxJ = 0;
            for (int i = 0; i < Q; i++) {
                while (maxJ < mem[i].a) max = Math.max(max, cows[maxJ++]);
                for (int j = mem[i].a + 1; j < mem[i].h; j++) {
                    if (cows[j] > max)  {
                        // possible = false;
                        break;
                    }
                }
                max = Math.max(max, cows[maxJ]);
            }
            if (possible) {
                StringJoiner sj = new StringJoiner(" ");
                for (int i: cows ) sj.add(Integer.toString(Math.max(i, 1)));
                System.out.println(sj);
            } else{
                System.out.println(-1);
            }
        }
        br.close();
    }
}