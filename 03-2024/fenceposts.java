import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class fenceposts {
    static int N, P;
    static int[][] fences, sortbyx, sortbyy;
    static int totalDX, totalDY;
    static int[] dx, dy;
    static ArrayList<Integer[]> fenceCC;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        fences = new int[P][2];
        sortbyx = new int[P][2];
        sortbyy = new int[P][2];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            fences[i][0] = Integer.parseInt(st.nextToken());
            sortbyx[i][0] = fences[i][0];
            sortbyy[i][0] = fences[i][0];
            fences[i][1] = Integer.parseInt(st.nextToken());
            sortbyx[i][1] = fences[i][1];
            sortbyy[i][1] = fences[i][1];
        }
        Arrays.sort(sortbyx, (e1, e2) -> e1[0] == e2[0] ? e1[1] - e2[1] : e1[0] - e2[0]);
        Arrays.sort(sortbyy, (e1, e2) -> e1[1] == e2[1] ? e1[0] - e2[0] : e1[1] - e2[1]);
        fenceCC = new ArrayList<Integer[]>();
        int currX = sortbyx[0][0]; int currY = sortbyx[0][1];
        boolean horizontal = true;
        fenceCC.add(new Integer[] {currX, currY});
        while (currX != sortbyx[P-1][0] || currY != sortbyx[P-1][1]) {
            if (horizontal) {
                int pos = bsearchfory(currX, currY);
                assert(sortbyy[pos + 1][1] == currY);
                currX = sortbyy[pos + 1][0];
                currY = sortbyy[pos + 1][1];
                fenceCC.add(new Integer[] {currX, currY});
            } else {
                int pos = bsearchforx(currX, currY);
                assert(sortbyx[pos + 1][0] == currX);
                currX = sortbyx[pos + 1][0];
                currY = sortbyy[pos + 1][1];
                fenceCC.add(new Integer[] {currX, currY});   
            }
            horizontal = ! horizontal;
        }
        // System.out.println(horizontal);
        while (currX != sortbyx[0][0] || currY != sortbyy[0][1]) {
            if (horizontal) {
                int pos = bsearchfory(currX, currY);
                assert(sortbyy[pos-1][1] == currY);
                currX = sortbyy[pos -1][0];
                currY = sortbyy[pos-1][1];
                fenceCC.add(new Integer[] {currX, currY});
            } else {
                int pos = bsearchforx(currX, currY);
                assert(sortbyx[pos - 1][0] == currX);
                currX = sortbyx[pos - 1][0];
                currY = sortbyy[pos - 1][1];
                fenceCC.add(new Integer[] {currX, currY});   
            }
            horizontal = ! horizontal;
            
        }
        fenceCC.remove(P);    
        for (Integer[] i: fenceCC) System.out.print(Arrays.toString(i));
        totalDX = 0;
        totalDY = 0;
        dx = new int[P]; // distance from 0
        dy = new int[P];
        for (int i = 0; i < fenceCC.size() - 1; i++) {
            dx[i + 1] = Math.abs(fenceCC.get(i + 1)[0] - fenceCC.get(i)[0]);
            totalDX += dx[i+1];
            dy[i + 1] = Math.abs(fenceCC.get(i + 1)[1] - fenceCC.get(i)[1]);
            totalDY += dy[i+1];
        }
        HashMap<Integer, Integer> hashToIndexCC = new HashMap<>();
        for (int i = 0; i < fenceCC.size(); i++) {
            hashToIndexCC.put(Arrays.deepHashCode(fenceCC.get(i)), i);
        }
        // for (int i = 0; i < N; i++) {
        //     st = new StringTokenizer(br.readLine());
        // }
        
    }
    static int getDist(int a, int b) {
        return Math.abs(dx[a] - dx[b]) + Math.abs(dy[a] - dy[b]);
    }
    static int bsearchforx(int x, int y) {
        int lo = 0;
        int hi = P-1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sortbyx[mid][0] < x) {
                lo = mid + 1;
            } else if (sortbyx[mid][0] > x){ 
                hi = mid - 1;
            } else {
                if (sortbyx[mid][1] > y) {
                    hi = mid - 1;
                } else  if (sortbyx[mid][1] < y) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;

    }
    static int bsearchfory(int x, int y) {
        int lo = 0;
        int hi = P-1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sortbyy[mid][1] < y) {
                lo = mid + 1;
            } else if (sortbyy[mid][1] > y){ 
                hi = mid - 1;
            } else {
                if (sortbyy[mid][0] > x) {
                    hi = mid - 1;
                } else  if (sortbyy[mid][0] <x) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;

    }
}