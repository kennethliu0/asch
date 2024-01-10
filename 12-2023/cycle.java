import java.util.*;
import java.io.*;
public class cycle {
    static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] a = new int[K];
        int[] b = new int[K];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> uniques = new HashSet<Integer>();
        for (int i = 0; i < K; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            uniques.add(a[i]);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            uniques.add(b[i]);
        }
        int[][] adj = new int[N + 1][2];
        for (int i = 0; i < K; i++) {
            adj[a[i]][0] = a[getPrev(i)];
            adj[a[i]][1] = a[getNext(i)];
        }
        int leftSide = 0;
        int rightSide = 0;
        // reversed
        /*
        out:
        for (int i = 0; i < K; i++) {
            int x = b[i];
            int prev = b[getPrev(i)];
            int next = b[getNext(i)];
            /*
            if (adj[x][1] == prev && adj[x][0] == next && adj[adj[x][0]][0] == b[getNext(getNext(i))] && adj[adj[x][1]][1] == b[getPrev(getPrev(i))] ) {
                ans++;
                continue;
            } 
            if (adj[x][1] == prev && adj[adj[x][0]][0] == b[getNext(getNext(i))]) {
                ans++;
                continue;
            }
            if (adj[x][0] == next && adj[adj[x][1]][1] == b[getPrev(getPrev(i))]) {
                ans++;
                continue;
            } 
            int anext = adj[x][1];
            int aprev = adj[x][0];
            int bnext = getNext(i);
            int bprev = getPrev(i);
            while (bnext != i) {
                if (b[bnext] != aprev) continue out;
                if (b[bprev] != anext) continue out;
                bnext = getNext(bnext);
                bprev = getPrev(prev);
                anext = adj[anext][1];
                aprev = adj[aprev][0];
            }
            ans++;
            /*
            if (adj[adj[x][0]][0] == b[getNext(getNext(i))] && adj[adj[x][1]][1] == b[getPrev(getPrev(i))]) {
                ans++;
                continue;
            } 
        }
        int ans1 = N - uniques.size();
        // right way
        out:
        for (int i = 0; i < K; i++) {
            int x = b[i];
            int prev = b[getPrev(i)];
            int next = b[getNext(i)];
            /*
            if (adj[x][0] == prev && adj[x][1] == next) {
                ans1 ++;
                continue;
            }
            if (adj[x][1] == next && adj[adj[x][0]][0] == b[getPrev(getPrev(i))]) {
                ans1++;
                continue;
            }
            if (adj[x][0] == prev && adj[adj[x][1]][1] == b[getNext(getNext(i))]) {
                ans1++;
                continue;
            } */
            /* 
            if (adj[adj[x][0]][0] == b[getPrev(getPrev(i))] && adj[adj[x][1]][1] == b[getNext(getNext(i))]) {
                ans1++;
                continue;
            } 
            int anext = adj[x][1];
            int aprev = adj[x][0];
            int bnext = getNext(i);
            int bprev = getPrev(i);
            while (bnext != i) {
                if (b[bnext] != anext) continue out;
                if (b[bprev] != aprev) continue out;
                bnext = getNext(bnext);
                bprev = getPrev(prev);
                anext = adj[anext][1];
                aprev = adj[aprev][0];
            }
            ans++;
        } 
        */
        int ans = N;
        int ans1 = N;
        int anext = adj[b[0]][1];
        int aprev = adj[adj[b[0]][0]][1];
        while (ans > 0 && ans1 > 0 && (anext == 0 || aprev == 0)) {
            ans--;
            ans1--;
            aprev = anext;
            anext = adj[anext][1];
        }
        int bprev = 0;
        int bnext = getNext(0);
        while (bnext != 0) {
            if (b[bnext] != anext || b[bprev] != aprev)  {
                ans--;
            }
            if(b[bnext] != aprev || b[bprev] != anext) {
                ans1--;
            }
            bprev = bnext;
            bnext = getNext(bnext);
            aprev = anext;
            anext = adj[anext][1];
        }
        
        System.out.println(Math.max(0, Math.max(ans, ans1)));
        br.close();
    }
    static int getNext(int i) {
        if (i == K - 1) return 0;
        else return i + 1;
    }
    static int getPrev(int i) {
        if (i == 0) return K - 1;
        else return i - 1;
    }
}