import java.util.*;
import java.io.*;
// https://cses.fi/problemset/task/1636/
public class coin {
	static final int MOD = (int) 1e9 + 7;
	/*
	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 10; i++) {
			solve(i);
		}
	} */
	// public static void solve(int k) throws Exception {
	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new FileReader((k == 10) ? "10.in" : "0" + k + ".in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		// long[][] DP = new long[X + 1][N];
		long[] DP = new long[X + 1];
		int[] coins = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
			/*
			if (coins[i] <= X) {}
				DP[coins[i]][i] = 1;
			*/
		}
		/*
		for (int i = 0; i < X; i++) {
			for (int c = 0; c < N; c++) {
				if (i + coins[c] <= X) {
					long total = 0;
					for (int  j= 0; j <= c; j++) {
						total += DP[i][j];
					}
					DP[i + coins[c]][c] = (DP[i + coins[c]][c] % MOD+ total % MOD + MOD) % MOD;
				}
			}
		} 
		long ans= 0 ;
		for (int i = 0; i < N; i++) {
			ans = (ans % MOD + DP[X][i] % MOD) % MOD;
		} */
		DP[0] = 1;
		for (int c = 0; c < N; c++) {
			for (int w = 0; w <= X; w++) {
				if (w - coins[c] >= 0) {
					DP[w ] = (DP[w - coins[c]] % MOD + DP[w] % MOD) % MOD;
				}
			}
		}
		System.out.println(DP[X]);
		br.close();
	}
}