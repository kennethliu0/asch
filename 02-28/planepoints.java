import java.util.*;
import java.io.*;
public class planepoints {
    static class Point implements Comparable<Object>{
        int x, y, id, pos ;
        public Point (int x, int y , int pos){
            this.x = x;
            this.y = y;
            this.pos = pos;
            id = x / 1000;
        }
        public int compareTo(Object obj) {
            Point p = (Point) obj;
            return this.id == p.id ? (this.id % 2 == 0 ? Integer.compare(p.y, this.y) : Integer.compare(this.y, p.y) ) : Integer.compare(this.id, p.id);
        }   
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] altPoints = new int[N][4];
        long totalDist = 0;
        // Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st  =new StringTokenizer(br.readLine());
           //  points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
            
            altPoints[i][0] = Integer.parseInt(st.nextToken());
            altPoints[i][1] = Integer.parseInt(st.nextToken());
            altPoints[i][2] = i;
            altPoints[i][3] = altPoints[i][0] / 1000;
            
        }
        br.close();
        
        Arrays.sort(altPoints, (e1, e2) -> e1[3] == e2[3] ? (e1[3] % 2 == 0 ? e2[1] - e1[1] : e1[1] - e2[1]) : e1[3] - e2[3]);
        StringJoiner sj = new StringJoiner("\n");
        for (int[] i: altPoints) sj.add(Integer.toString(i[2] + 1));
        for (int i =0; i < altPoints.length - 1; i++) {
            totalDist += Math.abs(altPoints[i+ 1][1] -altPoints[i][1]) + Math.abs(altPoints[i+ 1][0] - altPoints[i][0]);
        }
        /*
        // Arrays.sort(points);
        for (Point p: points) sj.add(Integer.toString(p.pos + 1));
        for (int i = 0; i < points.length - 1; i++) {
            totalDist += Math.abs(points[i+ 1].y - points[i].y) + Math.abs(points[i + 1].x - points[i].x);
        }
        */
        assert totalDist <= (long) 2e9;
        System.out.println(sj);
    }
}