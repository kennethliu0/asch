import java.util.*;
import java.io.*;
public class dishes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("dishes.in"));
        // PrintWriter out = new PrintWriter("dishes.out");
        int N = Integer.parseInt(br.readLine());
        int maxClean = 0;
        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        stacks.add(new Stack<Integer>());
        int ans = N;
        for (int j = 1; j < N; j++) {
            int x = Integer.parseInt(br.readLine());
            if (x < maxClean) {
                ans = j;
                break;
            }
            int i = 0;
            stacks.get(i).add(x);
            
        }
        System.out.println(ans);
        br.close();
        // out.close();
    

    }
}