import java.util.*;
import java.io.*;
public class angrycowsgold {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        // PrintWriter out = new PrintWriter("angry.out");
        int N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        Arrays.sort(x);
        
    }
}