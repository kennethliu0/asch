import java.util.*;
import java.io.*;
public class enclosure {
    static final String  op = "*-/+";
    static final String close = "{[()]}";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        ArrayList<String> exp = new ArrayList<String>();
        for (int i =0; i < s.length(); i++) {
            if (op.indexOf(s.charAt(i)) != -1 || close.indexOf(s.charAt(i)) != -1) exp.add(s.charAt(i) + "");
            else {
                int j = i;
                while (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                exp.add(s.substring(i, j));
                i = j - 1;
            }
        }
        br.close();
        if (s.indexOf("}") == -1 && s.indexOf("{") > -1) {
            System.out.println(forward("}",s ));
        } else if (s.indexOf("]") == -1 && s.indexOf("[") > -1) { 
            System.out.println(forward("]",s ));
        } else if (s.indexOf(")") == -1 && s.indexOf("(") > -1) {
            System.out.println(forward(")",s ));
        } else if (s.indexOf("{") == -1 &&  s.indexOf("}") > -1) {
            System.out.println(back("{",s ));
        } else if (s.indexOf("[") == -1 && s.indexOf("]") >  -1) { 
            System.out.println(back("[",s ));
        } else if (s.indexOf("(") == -1 && s.indexOf(")") > -1) {
            System.out.println(back("(",s ));
        }
        
    }
    static String forward(String key, String s) {
        Stack<Character> brackets = new Stack<Character>();
        String ans= "";
        boolean wasOp = false;
        for (int i = 0; i < s.length(); i++) {
            boolean num = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            while (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                i++;
            }
            if (num)  i--;
            if (! brackets.isEmpty() && brackets.peek().equals(key) && ! wasOp) ans += (i + 1) + ", ";
            wasOp = op.indexOf(s.charAt(i)) != -1;
            if (close.indexOf(s.charAt(i)) <= 2) brackets.add(s.charAt(i));
            if (close.indexOf(s.charAt(i)) >= 3) {
                if (brackets.peek().equals(s.charAt(i))){
                    brackets.pop();
                } else {
                    break;
                }
            }
        }
        return ans.substring(0, ans.length() - 2);
    }
    static String back(String key, String s) {
        return "";

    }
}