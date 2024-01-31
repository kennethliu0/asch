import java.util.*;
import java.io.*;
public class closest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            TreeMap<Integer, Integer> left = new TreeMap<>();
            TreeMap<Integer, Integer> right = new TreeMap<>();
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            int index = 0;
            left.put(0, 1);
            for (int i = 1; i < N - 1; i++) {
                if (A[i] - A[i-1] < A[i + 1] - A[i]) {
                    index = i;
                } else {
                    if (! left.containsKey(index)) {
                        left.put(index, index + 1);
                    } else {
                        left.put(index, left.get(index) + 1);
                    }  
                }
            }
            index = N - 1;
            right.put(N-1, N-2);
            for (int i = N - 2; i > 0; i--) {
                if (A[i] - A[i-1] > A[i + 1] - A[i]) {
                    index = i;
                } else{
                    if (! right.containsKey(index)) {
                        left.put(index, index + 1);
                    } else {
                        right.put(index, right.get(index) - 1);
                    }
                }
            }
            // left.put(index, left.get(index) + 1);
            // System.out.println(left);
            // System.out.println(right);
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                if (x < y) {
                    int cost= 0 ;
                    while (x < y) {
                        Integer intKey = left.floorKey(x);
                        if (intKey == null) {
                            cost += A[y] - A[x];
                            break;
                        }
                        if (intKey < x && left.get(intKey) > x) {
                            cost += left.get(intKey) - x;
                            x = left.get(intKey);
                            continue;
                        }
                        if (intKey < x) intKey = left.ceilingKey(x);
                        if (intKey == null) {
                            cost += A[y] - A[x];
                            break;
                        }
                        cost += A[intKey] - A[x];
                        cost += left.get(intKey) - intKey;
                        x = left.get(intKey);
                    }
                    System.out.println(cost);
                } else {
                    int cost= 0 ;
                    while (x >  y) {
                        Integer intKey = right.floorKey(x);
                        if (intKey == null) {
                            cost += A[x] - A[y];
                            break;
                        }
                        if (intKey > x && right.get(intKey) < x) {
                            cost += x - right.get(intKey);
                            x = right.get(intKey);
                            continue;
                        }
                        if (intKey > x) intKey = left.floorKey(x);
                        if (intKey == null) {
                            cost += A[x] - A[y];
                            break;
                        }
                        cost += A[x] - A[intKey];
                        cost += intKey - right.get(intKey);
                        x = right.get(intKey);
                    }
                    System.out.println(cost);
                }

            }
        }
        br.close();
    }
}