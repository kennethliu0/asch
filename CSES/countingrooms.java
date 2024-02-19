import java.util.*;
import java.io.*;
public class countingrooms {
    static boolean [][] rooms;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        rooms = new boolean[N][M]; 
        visited =new boolean[N][M]; 
        for (int i= 0; i < N; i++) {
            st =  new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                rooms[i][j] = (st.nextToken().equals("#")) ? false : true;
            }
        }
        dfs(0, 0);
    }
    static int dfs(int r, int c) {
        if (visited[r][c]) return 0;
        if (rooms[r][c]) { 
            pathRoom(r, c);
        }
        visited[r][c] = true;
        int count = (rooms[r][c]) ? 1 : 0 ;
        for (int i = 0; i < 4; i++) {
            if (inRange(r + dx[i], c + dy[i])) {
                dfs(r + dx[i], c + dy[i]);
            }
        }
        return count;
    }
    static void pathRoom(int r, int c) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            if (inRange(r + dx[i], c + dy[i]))
        }
    }
    static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < rooms.length && c < rooms[r].length;
    }
}
