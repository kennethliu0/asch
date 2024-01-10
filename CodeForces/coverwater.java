import java.util.*;
import java.io.*;
// https://codeforces.com/contest/1900/problem/A
public class coverwater {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int N = Integer.parseInt(br.readLine());
            long count = 0;
            String s = br.readLine();
            char[] c = s.toCharArray();
            if (s.indexOf("...") >= 0) {
                System.out.println(2); // if there is any group of 3 or more, then you can create infinite water source by filling 2 cells spaced apart by one
                continue;
            }
            // assuming there is no group of 3 empty, add water to each cell
            int curr = 0;
            for (int i = 0; i < N; i++) {
                if (c[i] == '#') {
                    count += curr;
                    curr = 0;
                } else {
                    curr++;
                }
            }
            count += curr;
            System.out.println(count);
        }
        br.close();
    }
}