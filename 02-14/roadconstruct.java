import java.util.*;
import java.io.*;
public class roadconstruct {
    static int[] root;
    static int[] size;
    static int[] height;
    static int maxSize;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        root = new int[N]; size = new int[N]; height = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            size[i] = 1;
            height[i] = 0;
        }
        count = N;
        maxSize = 1;
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            merge(A, B);
            sj.add(count + " " + maxSize);
        }
        System.out.println(sj);
        br.close();
    }
    public static int find(int x) {
        if (root[x ] == x) return x;
        else {
            int parent = find(root[x]);
            root[x] = parent;
            return parent;
        }

    }
    public static void merge(int x, int y) {
        int xparent = find(x);
        int yparent = find(y);
        if (xparent == yparent) return;
        count--;
        if (height[xparent] > height[yparent]) {
            root[y] = xparent;
            root[yparent ] = xparent;
            size[xparent] += size[yparent];
            maxSize = Math.max(maxSize, size[xparent]);
        } else {
            root[x] = yparent;
            root[xparent] = yparent;
            size[yparent] += size[xparent];
            if (height[xparent] == height[yparent]) height[yparent]++;
            maxSize = Math.max(maxSize, size[yparent]);
        }

    }
}