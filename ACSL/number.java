import java.util.*;
import java.io.*;
public class number {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            long N = in.nextLong();
            String s = N + "";
            int P = s.length() - in.nextInt();
            String ans = "";
            for (int x = s.length() - 1; x >= 0; x--) {
                if (x < P) {
                    ans = (s.charAt(x) - '0' + s.charAt(P) - '0') + ans;
                } else if (x == P) {
                    ans = findPrimes(N) + ans;
                } else {
                    ans = Math.abs(s.charAt(x) - '0' - s.charAt(P) + '0') + ans;
                }
            }
            System.out.println(ans);
        }
        in.close();
    }
    
    static long findPrimes(long N) {
        long count = 0;
        if (N % 2 == 0) {
            count++;
            while (N % 2 == 0) N /= 2;
        }
        for (int k = 3; k <= (long) Math.sqrt(N); k += 2) {
            if (N % k == 0) {
                count++;
                while (N % k == 0) N /= k;
            }
        }
        return count; 
    } 

}