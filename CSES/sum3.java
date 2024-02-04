import java.util.*;
import java.io.*;
// https://cses.fi/problemset/task/1641
public class sum3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (map.containsKey(a[i])) {
                map.get(a[i]).add(i);
            } else {
                map.put(a[i], new ArrayList<Integer>());
                map.get(a[i]).add(i);
            }
        }
        String ans = "IMPOSSIBLE";
        out:
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map.containsKey(X - a[i] - a[j])) {
                    for (int y: map.get(X- a[i] - a[j])) {
                        if (y != i && y != j) {
                            ans = ((i + 1) + " " + (j + 1)+ " " + (y+1));
                            break out;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}
