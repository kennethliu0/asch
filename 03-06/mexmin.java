import java.util.*;
import java.io.*;
public class mexmin {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a= new int[N + 1];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int[] count = new int[N + 1];
        TreeSet<Integer> unused = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            unused.add(i);
        }
        for (int i = 1; i <= M; i++) {
            count[a[i]]++;
            if (count[a[i]] == 1) unused.remove(a[i]);
        }
        int min = unused.first();
        for (int i = 1; i <= N - M; i++) {
            count[a[i]]--;
            if (count[a[i]] == 0) unused.add(a[i]);
            count[a[i + M]]++;
            if (count[a[i + M]] == 1) unused.remove(a[i + M]);
            min = Math.min(min, unused.first());
        }
        System.out.println(min);
        
    }
}