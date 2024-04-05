import java.util.*;
import java.io.*;
public class slidingmedian {
    static class multiset {
        TreeMap<Integer, Integer> s1, s2;
        int s1size,  s2size,  size1,  size2;
        public multiset(int k) {
            s1size = (k + 1) / 2;
            s2size = k / 2;
            size1 = 0;
            size2 = 0;
            s1 = new TreeMap<>();
            s2 = new TreeMap<>();
        }
        public void add(int x) {
            if (size1 == 0 && size2 == 0) {
                s1.put(x, 1);
                size1++;
                return;
            }
            if (x > getMedian()) {
                s2.put(x, s2.getOrDefault(x, 0) + 1);
                size2++;
                
            } else{
                s1.put(x, s1.getOrDefault(x, 0) + 1);
                size1++;
            }
            fix();
        }
        public void remove(int y) {
            if (y > getMedian()) {
                if (s2.get(y) == 1) {
                    s2.remove(y);
                } else{
                    s2.put(y, s2.get(y) - 1);
                }
                size2--;
            } else {
                if(s1.get(y) == 1) {
                    s1.remove(y);
                } else{
                    s1.put(y, s1.get(y) - 1);
                }
                size1--;
            }
        }
        public int getMedian () {
            return s1.lastKey();
        }
        public void fix() {
            if (size2 > s2size) {
                int y = s2.firstKey();
                if (s2.get(y) == 1) {
                    s2.remove(y);
                } else {
                    s2.put(y, s2.get(y) - 1);
                }
                size2--;
                size1++;
                s1.put(y, s1.getOrDefault(y, 0) + 1);
            }
            if (size1 > s1size) {
                int y = s1.lastKey();
                if (s1.get(y) == 1) {
                    s1.remove(y);
                } else {
                    s1.put(y, s1.get(y) - 1);
                }
                size2++;
                size1--;
                s2.put(y, s2.getOrDefault(y, 0) + 1);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] x= new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i =0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        StringJoiner sj = new StringJoiner(" ");
        if (K == 1){
            for (int i = 0; i < N; i++) {
                sj.add(Integer.toString(x[i]));
            }
        } else if (K == 2) {
            for (int i = 0; i + 1< N; i++) {
                sj.add(Integer.toString(Math.min(x[i], x[i + 1])));
            }
        } else {
            multiset m = new multiset(K);
            for  (int i = 0; i < K; i++) {
                m.add(x[i]);
            }
            sj.add(Integer.toString(m.getMedian()));
            for (int i = 0; i < N - K; i++) {
                m.remove(x[i]);
                m.add(x[i + K]);
                sj.add(Integer.toString(m.getMedian()));
            }
        }
    
        System.out.println(sj);

    }
}