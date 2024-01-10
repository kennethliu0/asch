import java.util.*;
import java.io.*;
public class pareidolia {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c = br.readLine().toCharArray();
        long[] arr = new long[6];
        long total = 0;
        long ans=  0;
        for (int i = 0; i < c.length; i++) {
            arr[0]++;
            if (c[i] == 'b') {
                arr[1] += arr[0];
                arr[0] = 0;
            } else if (c[i] == 'e') {
                arr[2] += arr[1];
                arr[0] += arr[5];
                ans+= arr[5];
                arr[5] = 0;
                arr[1] = 0;
            } else if (c[i] == 's') {
                arr[4] += arr[3];
                arr[3] = arr[2];
                arr[2] = 0;
            } else if (c[i] == 'i') {
                arr[5] += arr[4];
                arr[4] = 0;
            }
            total += ans;
            // System.out.println(Arrays.toString(arr) + " "+ ans +  " " + total);
        }
        System.out.println(total);
    }
}