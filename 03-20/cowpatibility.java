import java.util.*;
import java.io.*;
public class cowpatibility {
    static class Flavor implements Comparable<Object> {
        ArrayList<Integer> arr;
        public Flavor() {
            arr = new ArrayList<>();
        }
        public int compareTo(Object o) {
            Flavor f = (Flavor) o;
            if (f.arr.size() != arr.size()) return arr.size() - f.arr.size();
            for (int i = 0; i < arr.size(); i++) {
                if (f.arr.get(i) != arr.get(i)) return Integer.compare(arr.get(i), f.arr.get(i));
            }
            return 0;
        }
        
        public boolean equals(Object o) {
            Flavor f  = (Flavor) o;
            return f.arr.equals(arr);
        }
        public int hashCode() {
            return arr.hashCode();

        }
        public String toString() {
            return arr.toString();
        }

    }
    static HashMap<Flavor, Integer> counts;
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        long N = Long.parseLong(br.readLine());
        counts = new HashMap<Flavor, Integer>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] temp = new int[5];
            for (int x= 0; x < 5; x++) {
                temp[x] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(temp);
            for (int j = 1; j < 32; j++) {
                Flavor f = new Flavor();
                for (int k = 0; k < 5; k++) {
                    if ((j & (1 << k)) == (1 << k)) {
                        f.arr.add( temp[k]);
                    }
                }
                counts.put(f, counts.getOrDefault(f,  0) + 1);
            }

        }
        /*
        Flavor first = counts.firstKey();
        long ans= N * (N-1) / 2 - (first.arr.size() % 2 == 0 ? -1 : 1) * counts.get(first)* (counts.get(first) - 1) / 2; 
        while (counts.higherKey(first) != null) {
            first = counts.higherKey(first);
            ans -= (first.arr.size() % 2 == 0 ? -1 : 1) * counts.get(first)* (counts.get(first) - 1) / 2;
        }*/
        long ans = (long) N * (N-1)/2;
        for (Map.Entry<Flavor, Integer> ent: counts.entrySet()) {
            if (ent.getValue() < 2) continue;
            ans-= ( ent.getKey().arr.size() % 2 == 0 ? -1 : 1 ) *  ((long) ent.getValue()) * (ent.getValue() - 1) / 2;
        }
        PrintWriter out = new PrintWriter("cowpatibility.out");
        out.println(ans);
        br.close();
        out.close();
    }
}