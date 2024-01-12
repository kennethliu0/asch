import java.util.*;
import java.io.*;
public class goodtriples {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            long count= 1 ;
            while ( N > 0) {
                long mult = 0;
                int d = N % 10;
                N /= 10;
                for (int i = 0; i <= d; i++) {
                    for (int j = 0; j  <= d; j++) {
                        if (d - i- j >= 0) mult++;
                    }
                }
                count *= mult;
            }
            System.out.println(count);
        }
        br.close();
    }
}