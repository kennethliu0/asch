import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
public class moviefestival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] movies = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            movies[i][0 ] = Integer.parseInt(st.nextToken());
            movies[i][1 ] = Integer.parseInt(st.nextToken());   
        }
        br.close();
        Arrays.sort(movies, (e1, e2) -> e1[1] - e2[1]);
        int ans =0;
        int curr = 0;
        for (int i = 0; i < N; i++) {
            if (curr <= movies[i][0]) {
                curr = movies[i][1];
                ans++;
            }
        }
        System.out.println(ans);
    }
}
