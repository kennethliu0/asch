import java.util.*;
import java.io.*;
public class ninetynine {
    static int total;
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String[] moves = in.nextLine().split(", ");
        for (int i = 1; i < 6; i++) {
            total = 0;
            String[] cards = in.nextLine().split(", ");
            int total = Integer.parseInt(cards[0]);
            String[] one = new String[5];
            String[] two = new String[5];
            for (int j = 0; j < 5; j++) {
                one[j] = (moves[j] + "");
                two[j] = (moves[j + 5] + "");
            }
            boolean isOne = true;
            int index1 = 1;
            int index2 = 2;
            Arrays.sort(one, (e1, e2) -> getValue(e1) - getValue(e2));
            Arrays.sort(two, (e1, e2) -> getValue(e1) - getValue(e2));
            while (total <= 99) {
                int tmp = total;
                if (isOne) {
                    if (one[one.length / 2].equals("7")) {
                        if (total <= 92) total += 7;
                        else total += 1;
                    } else if (one[one.length / 2].equals("T")) {
                        total -= 10;
                    } else if (!one[one.length / 2].equals("9")) {
                        total += getValue(one[one.length / 2]);
                    } 
                    if (index1 > 10) one[one.length / 2] = "-1";                
                    else one[one.length / 2] = cards[index1];
                    index1 += 2;
                    Arrays.sort(one, (e1, e2) -> getValue(e1) - getValue(e2));
                    isOne = false;
                } else {
                    if (two[two.length / 2].equals("7")) {
                        if (total <= 92) total += 7;
                        else total += 1;
                    } else if (two[two.length / 2].equals("T")) {
                        total -= 10;
                    } else if (!two[two.length / 2].equals("9")) {
                        total += getValue(two[2]);
                    }   
                    if (index2 > 10) two[two.length / 2] = "-1";
                    else two[two.length / 2] = cards[index2];
                    index2 += 2;
                    Arrays.sort(two, (e1, e2) -> getValue(e1) - getValue(e2));
                    isOne = true;
                }
                if ((tmp >= 34 && total <= 33) || (tmp <= 33 && total >= 34 ) || (tmp >= 56 && total <= 55) || (tmp <= 55 && total >= 56) || (tmp >= 78 && total <= 77) || (tmp <= 77 && total >= 78)) 
                    total += 5;
            }
            System.out.print(total + ", ");
            System.out.println((isOne) ? "Player #1" : "Player #2");
        }
        in.close();
    }
    static int getValue(String s) {
        s = s.strip();
        if (s.equals("T")) return 10;
        if (s.equals("Q")) return 12;
        if (s.equals("K")) return 13;
        if (s.equals("J")) return 11;
        if (s.equals("A")) return 14;
        return Integer.parseInt(s);
    }
}