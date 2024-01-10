import java.util.*;
import java.io.*;
public class maxorder {
    static double[][] DP;
    static ArrayList<Integer> primes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        int[] n = new int[Q];
        for (int i = 0; i < Q; i++) {
            n[i] = Integer.parseInt(br.readLine());
        }
        primes = sieve(Q + 1);
        DP = new double[Q][primes.size()];
        for (double[] arr: DP) {
            Arrays.fill(arr, -1);
        }
        System.out.println(Math.pow(Math.E, DP(Q - 1, primes.size() - 1)));
    }
    public static double DP(int i, int j) {
        if (DP[i][j] != -1) return DP[i][j];
        DP[i][j] = DP(i, j - 1);
        double max = 0;
        for (int k = 1; pow(primes.get(j), k) <= i; k++) {
            max = Math.max(max, DP(i - pow(primes.get(j), k), j - 1) + k * Math.log(primes.get(j)));
        }
        DP[i][j] = Math.max(DP[i][j], max);
        return DP[i][j];
    }
    public static int pow(int i, int j){
        int res = 1;
        while (j-->0) {
            res *= i;
        }
        return res;
    }
    public static ArrayList<Integer> sieve(int Q) {
        boolean[] A = new boolean[Q];
        Arrays.fill(A, true);
        for (int i = 2; i <= (int) (Math.sqrt(Q)) + 1; i++) {
            if (A[i]) {
                for (int j = i * i; j < Q; j += i) {
                    A[j] = false;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            if (A[i]) primes.add(i);
        }
        return primes;
    }
}