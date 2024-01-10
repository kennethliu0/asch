import java.util.*;
import java.io.*;
public class radio {
   public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // BufferedReader br = new BufferedReader(new FileReader("radio.in"));
       PrintWriter out = new PrintWriter("radio.out");
       StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       st = new StringTokenizer(br.readLine());
       int fx = Integer.parseInt(st.nextToken());
       int fy = Integer.parseInt(st.nextToken());
       st = new StringTokenizer(br.readLine());
       int bx = Integer.parseInt(st.nextToken());
       int by = Integer.parseInt(st.nextToken());
       char[] f = br.readLine().toCharArray();
       char[] b = br.readLine().toCharArray();
       int[] pfx = new int[N], pfy = new int[N], pbx = new int[M], pby = new int[M];
       pfx[0] = fx;
       pfy[0] = fy;
       pbx[0] = bx;
       pby[0] = by;
       int x = fx;
       switch (f[0]) {
           case 'N': pfy[0]++;
           case 'S': pfy[0]--;
           case 'W': pfx[0]--;
           case 'E': pfx[0]++;
       }
       for (int i = 1; i < N; i++) {
           pfy[i] = pfy[i - 1];
           pfx[i] = pfx[i - 1];
           switch (f[i]) {
               case 'N': pfy[i]++;
               case 'S': pfy[i]--;
               case 'W': pfx[i]--;
               case 'E': pfx[i]++;
           }
       }
       switch (b[0]) {
           case 'N': pby[0]++;
           case 'S': pby[0]--;
           case 'W': pbx[0]--;
           case 'E': pbx[0]++;
       }
       for (int i = 1; i < M; i++) {
           pby[i] = pby[i - 1];
           pbx[i] = pbx[i - 1];
           switch (b[i]) {
               case 'N': pby[i]++;
               case 'S': pby[i]--;
               case 'W': pbx[i]--;
               case 'E': pbx[i]++;
           }
       }
       int[][] DP = new int[N][M];
       for (int i = 0; i < N; i++) {
           for (int j = 0; j < M; j++) {
               DP[i][j] = Integer.MAX_VALUE;
           }
       }
       DP[0][0] = 0;
       for (int i = 0; i < N - 1; i++) {
           for (int j = 0; j < M - 1; j++) {
               DP[i + 1][j] = Math.min(DP[i + 1][j], DP[i][j] + dist(pfx[i + 1], pbx[j], pfy[i + 1], pby[j]));
               DP[i + 1][j + 1] = Math.min(DP[i + 1][j + 1], DP[i][j] + dist(pfx[i + 1], pbx[j + 1], pfy[i + 1], pby[j + 1]));
               DP[i][j + 1] = Math.min(DP[i][j + 1], DP[i][j] + dist(pfx[i], pbx[j + 1], pfy[i], pby[j + 1]));
           }
       }
       System.out.println(DP[N - 1][M - 1]);
    out.close();

   }
   static int dist(int x1, int x2, int y1, int y2) {
       return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
   }


}

