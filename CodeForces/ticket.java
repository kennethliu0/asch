import java.util.*;
import java.io.*;
public class ticket {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(br.readLine());
        String[] S = new String[N];
        int[][] count = new int[6][46];
        for (int i = 0; i < N; i++) {
            S[i] = st.nextToken();
            int total = 0;
            for (char c: S[i].toCharArray()) {
                total += (int) (c - 48);
            }
            count[S[i].length()][total]++;
        }
        int total = 0;
        for (String T: S) {
            for (int len = 2; len <= 10; len += 2) {
                if (len <= T.length()) continue;
                if (T.length() / 2 >= len) {
                    int sumI = 0;
                    char[] c = T.toCharArray();
                    for (int i = 0; i < len / 2; i++) {
                        sumI += c[i] - 48;
                    }
                    int sumJ = 0;
                    for (int j = len / 2; j < T.length(); j++) {
                        sumJ += c[j] - 48;
                    }
                    total += count[len - T.length()][sumI - sumJ];
                } else if (T.length() / 2 <= len) {

                }
            }
        }
    }
}