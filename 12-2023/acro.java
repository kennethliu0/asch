import java.util.*;
import java.io.*;
public class acro {
    static class Cow implements Comparable<Cow> {
        int count;
        int val;
        public Cow (int v, int c) {
            count = c;
            val = v;
        }
        public int compareTo(Cow c) {
            return c.val - this.val;
        }
        public String toString() {
            return val + " " + count;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Cow> unused = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            unused.offer(new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        // System.out.println(pq);
        PriorityQueue<Cow> towers = new PriorityQueue<>();
        int towerCount = 0;
        long cowCount = 0;
        while (! unused.isEmpty()) {
            Cow e = unused.poll();
            while(! unused.isEmpty() && e.val == unused.peek().val) {
                unused.peek().count += e.count;
                e = unused.poll();
            }
            // starting the towers
            if (towerCount < M) {
                if (e.count + towerCount <= M) {
                    towers.offer(new Cow(e.val, e.count));
                    cowCount += e.count;
                    towerCount += e.count;
                } else if (e.count + towerCount > M) {
                    towers.offer(new Cow(e.val, M - towerCount));
                    unused.offer(new Cow(e.val, e.count - M + towerCount));
                    towerCount = M;
                    cowCount = M;
                }
                continue;
            }
            // all towers have been started
            if (towerCount == M) {
                Cow u = towers.poll();
                while(! towers.isEmpty() && u.val == towers.peek().val) {
                    towers.peek().count += u.count;
                    u = towers.poll();
                }
                if (u.val - K < e.val)  {
                    towers.offer(u);
                    continue;
                }
                if (u.count > e.count) {
                    cowCount += e.count;
                    towers.offer(new Cow(e.val, e.count)); // # of cows changed limited to e.count
                    towers.offer(new Cow(u.val, u.count - e.count)); // # of cows unchanged
                } else if (u.count < e.count) {
                    cowCount += u.count;
                    e.count -= u.count;
                    unused.offer(e); // unused cows
                    towers.offer(new Cow(e.val, u.count)); // all cows get a new value;
                } else { // counts are equal
                    cowCount += u.count;
                    towers.offer(new Cow(e.val, u.count));
                }
            }
            
        }
        System.out.println(cowCount);
        br.close();

    }
}