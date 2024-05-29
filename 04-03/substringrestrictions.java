import java.util.*;
import java.io.*;
public class substringrestrictions {
    static class DSU {
        int[] size;
        int[] root;
        public DSU(int x) {
            size = new int[x];
            root = new int[x];
            for (int i = 0; i < x; i++) {
                size[i] = 0;
                root[i] = i;
            }
        }
        public  int root(int x) {
            while (root[x] != x)  x = root[x];
            return x;
        }
        public void merge(int x, int y) {
            x = root(x); y = root(y);
            if (size[y] > size[x]) {
                size[y] += size[x];
                root[x] = y;
            } else {
                size[x] += size[y];
                root[y] = x;
            }
        }
        public int size() {
            HashSet<Integer> set = new HashSet<>();
            for (int i: root) {
                set.add(root(i));
            }
            return set.size();
        }
    }
    static final int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        DSU d = new DSU(N);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 0; j < L; j++) {
                d.merge(x, y);
            }
        }
        long res = 1;
        for (int i = 0; i < d.size(); i++) {
            res = (res * 26) % MOD;
        }
        System.out.println(res);
        br.close();
    }
}