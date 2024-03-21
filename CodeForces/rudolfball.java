import java.util.*;
import java.io.*;
public class rudolfball {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            ArrayList<TreeSet<Integer>> dp = new ArrayList<>();
            for (int i = 0; i < M + 1; i++) {
                dp.add(new TreeSet<>());
            }
            dp.get(0).add(X);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int thrown = Integer.parseInt(st.nextToken());
                int c = st.nextToken().charAt(0);
                if (c == '0') {
                    for (int j: dp.get(i)) {
                        dp.get(i + 1).add(getC(j, thrown));
                    }   
                } else if (c == '1') {
                    for (int j: dp.get(i)) {
                        dp.get(i + 1).add(getCC(j, thrown));
                    }   
                } else {
                    for (int j: dp.get(i)) {
                        dp.get(i + 1).add(getC(j, thrown));
                    } 
                    for (int j: dp.get(i)) {
                        dp.get(i + 1).add(getCC(j, thrown));
                    }   
                }
            }
            sj.add(Integer.toString( dp.get(M).size()));
            StringJoiner temp = new StringJoiner(" ");
            for (int i: dp.get(M)) temp.add(Integer.toString(i));
            sj.add(temp.toString());
        }
        System.out.println(sj);
        br.close();
    }
    static int getC (int y, int thrown) {
        int next = (y +thrown) % N;
        return next == 0 ? N : next;
    }
    static int getCC(int y, int thrown) {
        int next = (y - thrown + N) % N;
        return next == 0 ? N: next;
    }
}