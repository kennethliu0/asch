import java.util.*;
import java.io.*;
public class interview {
    static class Timing{
        int id;
        int multiplicity;
        public Timing(int id, int multiplicity) {
            this.id = id;
            this.multiplicity = multiplicity;
        }
        
    }
    
    static class UF {
        int[] unionfind;
        int[] sizes;
        public UF (int size) {
            unionfind = new int[size];
            sizes = new int[size];
            for (int i = 0; i < size; i++) {
                unionfind[i] = i;
                sizes[i] = 1;
            }
        }
        public void merge(int x, int y) {
            while (unionfind[y] != y) y = unionfind[y];
            while (unionfind[x] != x) x = unionfind[x];
            if (x == y) return;
            if (sizes[x] > sizes[y]) {
                unionfind[y] = unionfind[x];
                sizes[x] += sizes[y];
            } else {
                unionfind[x] = unionfind[y];
                sizes[y] += sizes[x];
            }
        }
        public boolean[] getComp(int x) {
            boolean[] b = new boolean[unionfind.length];
            while (unionfind[x] != x) x = unionfind[x];
            for (int i = 0; i < unionfind.length; i++) {
                int temp = i;
                while (unionfind[temp] != temp) temp = unionfind[temp];
                if (temp == x) b[i] = true;
            }
            return b;
        }
    } 
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] cows = new long[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            cows[i] = Long.parseLong(st.nextToken());
        }
        TreeMap<Long, Timing> tm = new TreeMap<>();
        UF comp = new UF(K);
        for (int j =0; j < K; j++) {
            if (tm.containsKey(cows[j])) {
                comp.merge(tm.get(cows[j]).id, j);
                tm.get(cows[j]).multiplicity++;
            } else {
                tm.put(cows[j], new Timing(j, 1));

            }
        }
        boolean[] farmers = new boolean[K];
        for (int i = K; i < N; i++) {
            while (tm.firstEntry().getValue().multiplicity == 0) {
                tm.pollFirstEntry();
            }
            long time = tm.firstKey();
            int farmer = tm.get(time).id;
            tm.get(time).multiplicity--;
            if (tm.get(time).multiplicity == 0) tm.remove(time);
            if (tm.containsKey(time + cows[i])) {
                tm.get(time +cows[i]).multiplicity++;
                comp.merge(tm.get(time + cows[i]).id, farmer);
            } else {
                tm.put(time + cows[i], new Timing(farmer, 1));
            }
            
        }
        long ans = tm.firstKey();
        farmers = comp.getComp(tm.firstEntry().getValue().id);
        StringBuilder sb = new StringBuilder();
        for (boolean b: farmers) {
            sb.append(b ? "1" :  "0");
        }
        System.out.println(ans); 
        System.out.println(sb);
    }
}