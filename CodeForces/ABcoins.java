import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1873/G
public class ABcoins {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            String s = br.readLine();
            ArrayList<Integer> arr = new ArrayList<>();
            int index = 0;
            arr.add(0);
            for (char c: s.toCharArray()) {
                if (c == 'B') {
                    arr.add(0);
                    index++;
                } if (c == 'A') {
                    arr.set(index, arr.get(index) +  1);
                }
            }  
             // System.out.println(arr);
            if (arr.size() == 1) {
                sj.add("0");
                continue;
            }
            int ans= 0 ;
            if (arr.get(0) == 0 || arr.get(arr.size() - 1) == 0) {
                for (int i: arr ){
                    ans += i;
                }
            sj.add(Integer.toString(ans));
            continue;
            }
            if (s.indexOf("BB") > -1) {
                for (int i: arr) {
                    ans += i;
                }
            sj.add(Integer.toString(ans));
            continue;
            }
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for (int i = 0; i < arr.size(); i++) {
                min = Math.min(min, arr.get(i));
                sum += arr.get(i);
            }
            sj.add(Integer.toString(sum - min));
        }   
        System.out.println(sj);
        br.close();
    }
}