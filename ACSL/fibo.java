import java.util.*;
import java.io.*;
public class fibo {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            char[] c = (" " + in.nextLine()).toCharArray();
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            int[] arr = {0, 1,1,2,3,5};
            for (int j = 1; j <= 5; j++) {
                if (c[j] == 'R' || c[j] == 'Y' || c[j] == 'M') hours += arr[j];
                if (c[j] == 'G' || c[j] == 'Y' || c[j] == 'C') minutes += arr[j];
                if (c[j] == 'B' || c[j] == 'M' ||c[j] == 'C') seconds += arr[j];
            }
            // System.out.printf("%2d:%2d:%2d", hours, minutes, seconds);
            minutes *= 5;
            seconds *= 5;
            minutes += seconds / 60;
            seconds %= 60;
            hours += minutes / 60;
            minutes %= 60;
            hours %= 12;
            if (hours < 10) System.out.print("0");
            System.out.print(hours + ":");
            if (minutes < 10) System.out.print("0");
            System.out.print(minutes + ":");
            if (seconds < 10) System.out.print("0");
            System.out.print(seconds);
            System.out.println();
        }
        in.close();

    }
}