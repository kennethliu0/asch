import java.util.*;
import java.io.*;
public class censoring {
    static int MOD1 = (int) 1e6 + 3;
    static int MOD2 = (int) 1e6 + 33;
    static int MOD3 = (int) 1e6 + 37;
    static int[] mod1, mod2, mod3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        // PrintWriter out = new PrintWriter("censor.out");
        String S = br.readLine();
        String T = br.readLine();
        br.close();
        mod1 = new int[(int) MOD1];
        mod2 = new int[(int) MOD2];
        mod3 = new int[(int) MOD3];
        int a = 1;
        for (int i = 0; i < MOD1; i++) {
            mod1[i] = a;
            a = (a * 256) % MOD1;
        }
        a = 1;
        for (int i = 0; i < MOD2; i++) {
            mod2[i] = a;
            a = (a * 256) % MOD2;
        }a = 1;
        for (int i = 0; i < MOD3; i++) {
            mod3[i] = a;
            a = (a * 256) % MOD3;
        }
        int H1 = 0;
        for (int i = 0; i < T.length(); i++) {
            H1 = (H1 + ((int) T.charAt(i)) * mod1[T.length() - i - 1]) % MOD1;
        }
        int H2 = 0;
        for (int i = 0; i < T.length(); i++) {
            H2 = (H2 + ((int) T.charAt(i)) * mod2[T.length() - i - 1]) % MOD2;
        }
        int H3 = 0;
        for (int i = 0; i < T.length(); i++) {
            H3 = (H3 + ((int) T.charAt(i)) * mod3[T.length() - i - 1]) % MOD3;
        }
        ArrayList<Character> bucket = new ArrayList<>();
        int newH1 = 0;
        int newH2 = 0;
        int newH3 = 0;
        for (char c: S.toCharArray()) {
            bucket.add(c);
            newH1 = (newH1 + c * mod1[T.length() - bucket.size() - 2]) % MOD1;
            newH2 = (newH2 + c * mod2[T.length() - bucket.size() - 2]) % MOD2;
            newH3 = (newH3 + c * mod3[T.length() - bucket.size() - 2]) % MOD3;
            if (bucket.size() > T.length()) {
                newH1 = newH1 -  bucket.get(bucket.size() - T.length() - 1) * mod1[] + c;

            }
            boolean equals = newH1 == H1 && newH2 == H2 && newH3 == H3;

            
        }
        // out.close();

    }
}