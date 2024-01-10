import java.util.*;
import java.io.*;
public class cyclesol {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] a = new int[K];
        int[] b = new int[K];
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> uniques = new HashSet<Integer>();
        HashMap<Integer, Integer> positionInA = new HashMap<>();
        for (int i = 0; i < K; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            positionInA.put(a[i], i);
            uniques.add(a[i]);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            uniques.add(b[i]);
        }
        int[] maxInside = new int[K];
        for (int i = 0; i < K; i++) {
            if (positionInA.containsKey(b[i])) {
                if (positionInA.get(b[i]) >= i) {
                    maxInside[positionInA.get(b[i]) - i]++;
                } else {
                    maxInside[positionInA.get(b[i]) - i + K]++;
                }
                
            }
                
        }
        
        int max = 0;
        for (int i: maxInside) {
            max = Math.max(i, max);
        }
        maxInside = new int[K];
        for (int i = 0; i < K / 2; i++) {
            int temp = b[i];
            b[i] = b[K - 1- i];
            b[K - 1 - i] = temp;
        }
        for (int i = 0; i < K; i++) {
            if (positionInA.containsKey(b[i])) {
                if (positionInA.get(b[i]) >= i) {
                    maxInside[positionInA.get(b[i]) - i]++;
                } else {
                    maxInside[positionInA.get(b[i]) - i + K]++;
                }
                
            }
                
        }
        for (int i: maxInside) {
            max = Math.max(i, max);
        }
        System.out.println(max + N - uniques.size());
        br.close();
    }
}