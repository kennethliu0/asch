import java.util.*;
import java.io.*;
public class bincount {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            System.out.println(solution(s));
        }
        br.close();
    }
    static int solution (String s) {
        String x = "";
        for (char c: s.toCharArray()) {
            String y = Integer.toBinaryString(c + 0);
            while (y.length() < 8) {
                y = "0" + y;
            }
            x += y;
        }
        
        int j = 0;
        String bString = Integer.toBinaryString(j);
        // System.out.println(x);
        while (x.indexOf(bString) != -1) {
            // System.out.println(bString);
            x = x.substring(0, x.indexOf(bString)) + x.substring(x.indexOf(bString) + bString.length());
            if (x.lastIndexOf(bString) != -1) {
                x = x.substring(0, x.lastIndexOf(bString)) + x.substring(x.lastIndexOf(bString) + bString.length());
            }
            
            j++;
            bString = Integer.toBinaryString(j);
            // System.out.println(x);
        }
         System.out.println(x);
        // System.out.println(x);
        while (x.length() % 3 != 0) {
            x = "0" + x;
        }
        String o = "";    
        for (int i = 0; i < x.length(); i += 3) {
            o += Integer.toOctalString( (Integer.parseInt(x.substring(i, i + 3), 2)));
        }
        System.out.println(o);
        j = 0;
        x = o;
        String oString = Integer.toOctalString(j);
        while (x.indexOf(oString) != -1) {
            x = x.substring(0, x.indexOf(oString)) + x.substring(x.indexOf(oString) + oString.length());
            if (x.lastIndexOf(oString) != -1) {
                x = x.substring(0, x.lastIndexOf(oString)) + x.substring(x.lastIndexOf(oString) + oString.length());
            }
            j++;
            oString = Integer.toOctalString(j);
        }
        return j - 1;
    }
}