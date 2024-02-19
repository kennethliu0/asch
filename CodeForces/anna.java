import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1931/E
public class anna {
    static class Number implements Comparable<Number>{
        int len;
        int zeroes;
        public Number (int num) {
            len = 0;
            int temp = num;
            while (temp > 0) {
                len++;
                temp /= 10;
            }
            zeroes = 0;
            while (num % 10 ==  0) {
                zeroes++;
                num /= 10;
            }
        }
        public Number (int l, int z) {
            len = l;
            zeroes = z;
        }
        public int compareTo(Number n) {
            return Integer.compare(n.zeroes , this.zeroes);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            PriorityQueue<Number> pq = new PriorityQueue<Number>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(new Number(Integer.parseInt(st.nextToken())));
            }
            while (true) {
                Number x = pq.poll();
                x.len = x.len - x.zeroes;
                x.zeroes = 0;
                pq.offer(x);
                if (pq.size() == 1) break;
                Number a = pq.poll();
                Number b = pq.poll();
                pq.offer(new Number(a.len + b.len, b.zeroes));
            }
            if (pq.poll().len> M) {
                sj.add("Sasha");
            } else {
                sj.add("Anna");
            }
        }
        System.out.println(sj);
        br.close();

    }
    static int getTrailing(int x) {
        int count = 0;
        while (x % 10 == 0) {
            x /= 10;
            count++;
        }
        return count;
    }
}