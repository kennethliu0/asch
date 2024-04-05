import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class target2 {
    static class Point{
        long x; long y;
        public Point(long x, long y) {
        this.x = x;
        this.y= y;
        }
    }

    static int N, X;
    static ArrayList<Point> pos, neg, undecided;
    static ArrayList<Integer> posSlope, negSlope;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            neg = new ArrayList<>();
            pos = new ArrayList<>();
            undecided = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                long y1 = Integer.parseInt(st.nextToken());
                long y2 = Integer.parseInt(st.nextToken());
                long x2 = Integer.parseInt(st.nextToken());
                pos.add(new Point(x2, y1));
                neg.add(new Point(x2, y2));
                undecided.add(new Point(X, y1));
                undecided.add(new Point(X, y2));
            }
            int[] slopes = new int[4 * N];
            posSlope = new ArrayList<>();
            negSlope = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4 * N; i++) {
                slopes[i] = Integer.parseInt(st.nextToken());
                if (slopes[i] > 0 ) posSlope.add(slopes[i]); 
                else negSlope.add(slopes[i]);
            }
            if (posSlope.size() < N || negSlope.size() < N) {
                sj.add("-1");
                continue;
            }
            Collections.sort(undecided, (e1, e2) -> Long.compare(e1.y, e2.y));
            for (int i = 0; i < negSlope.size() - N; i++) {
                neg.add(undecided.get(i));
            }
            for (int i = negSlope.size() - N; i < undecided.size(); i++) {
                pos.add(undecided.get(i));
            }
            Collections.sort(posSlope);
            Collections.sort(negSlope);
            assert pos.size() == posSlope.size() && neg.size() == negSlope.size();
            // process positive slopes first - max binary search on the min y intercept
            // check the slopes from each target point to the min y intercept and ensure it is greater than or equal to the actual slope assigned to that one
            long lo = Long.MAX_VALUE;
            long hi = Long.MAX_VALUE;
            for (Point p: pos) {
                lo = Math.min(lo, p.y - 1 * p.x * posSlope.get(posSlope.size() - 1));
                hi = Math.min(hi, p.y);
            }
            // System.out.println(checkSlope(12, posSlope, pos));
            while (lo < hi) {
                long mid = (lo + hi + 1) / 2;
                if (checkSlope(mid, posSlope, pos)) {
                    lo = mid;
                } else {
                    hi = mid - 1;
                }
            }
            long ans = lo;
            // process negative slopes - min binary search on the max y intercept
            // check slopes from each target point to the max y intercept and ensure it is greater than or euqal to the actual slope assigned to that one
            for (int i =0; i < neg.size(); i++) {
                neg.get(i).y *= -1;
            }
            for (int i = 0; i < negSlope.size(); i++) {
                negSlope.set(i, -1 * negSlope.get(i));
            }
            Collections.sort(negSlope);
            lo = Long.MAX_VALUE;
            hi = Long.MAX_VALUE;
            for (Point p: neg) {
                lo = Math.min(lo, p.y - 1 * p.x * negSlope.get(negSlope.size() - 1));
                hi = Math.min(hi, p.y);
            }
            while (lo < hi) {
                long mid = (lo + hi + 1)  / 2;
                if (checkSlope(mid, negSlope, neg)) {
                    lo = mid;
                } else {
                    hi = mid - 1;
                }
            }
            sj.add(Long.toString(- ans - lo));

        }
        System.out.println(sj);
        br.close();
    }
    static boolean checkSlope(long y, ArrayList<Integer> slopes, ArrayList<Point> points) {
        ArrayList<Long> reqSlopes = new ArrayList<>();
        for (Point p: points) {
            reqSlopes.add((p.y - y) / p.x);
        }
        Collections.sort(reqSlopes);
        Collections.sort(slopes);
        for (int i = 0; i < reqSlopes.size(); i++) {
            if (reqSlopes.get(i) < slopes.get(i)) return false;
        }
        return true;
    }
}