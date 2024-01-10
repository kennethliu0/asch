import java.util.*;
import java.io.*;
public class base {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        for (int x = 0; x < 5; x++) {
        int N = in.nextInt();
        int B = in.nextInt();
        String S = in.next().toUpperCase();
        long start = Long.parseLong(convertInt(S, B, 10));
        int[] count = new int['F' + 1];
        for (int i =0 ; i < N; i++) {
            String num = convertInt(start + i + "", 10, B).toUpperCase();
            // System.out.println(num);
            for (int j = '0'; j <= 'F'; j++) {
                count[j] += occur(num, (char) j);
            }
        }
        Arrays.sort(count);

        System.out.println(count['F']);

        }
        in.close();
    }
    static String convertInt(String num, int oldBase, int newBase) {
        return Long.toString(Long.parseLong(num, oldBase), newBase);
    }
    static int occur(String s, char c) {
        int count = 0;
        for (char x: s.toCharArray()) {
            if (x == c) count++;
        }
        return count;
    }
}