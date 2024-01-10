import java.util.*;
import java.io.*;
public class cyclebrute {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
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
        int ans = N - uniques.size();
        for (int ai = 0; ai < K; ai++) {
            for (int bi = 0; bi < K; bi++) { 
                for (int x = 0; x < K; x++) {
                    if (a[(ai + x) % K] != b[(bi + x) % K]) break;
                    ans = Math.max(ans, N - uniques.size() + x + 1);
                }
            }
        }
        // reverse
        for (int i = 0; i < b.length / 2; i++) {
            int tmp = b[i];
            b[i] = b[b.length - i - 1];
            b[b.length - i - 1] = tmp;
        }        
        for (int ai = 0; ai < K; ai++) {
            for (int bi = 0; bi < K; bi++) { 
                for (int x = 0; x < K; x++) {
                    if (a[(ai + x) % K] != b[(bi + x) % K]) break;
                    ans = Math.max(ans, N - uniques.size() + x + 1);
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}