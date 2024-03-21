import java.util.*;
import java.io.*;
public class rudolfuglystring {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int ans=  0;
            for (int i = 0; i + 2 < N; i++) {
                if (s.substring(i, i + 3).equals("map") ||s.substring(i, i + 3).equals("pie") ) {
                    ans++;
                }
                if (i + 5 <= N && s.substring(i, i + 5).equals("mapie")) {
                    ans--;
                }
            }
            sj.add(Integer.toString(ans));
        }
        System.out.println(sj);
        br.close();
    }
}