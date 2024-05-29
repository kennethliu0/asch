import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.StringJoiner;

public class nearest {
  static class Pair {
    int i;
    int x;

    public Pair(int i, int x) {
      this.i = i;
      this.x = x;
    }
    public String toString() {
      return "i: " + i + ", x: " + x;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] x = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }
    int[] ans = new int[N];
    PriorityQueue<Pair> pq = new PriorityQueue<Pair>((e1, e2) -> e2.x - e1.x);
    for (int i = N - 1; i >= 0; i--) {
      int curr = x[i];
      // if (i == 0) System.out.println(pq);
      while (! pq.isEmpty() && curr < pq.peek().x) {
        // if (i == 0) System.out.println(true);
        ans[pq.poll().i] = i + 1;
      }
      pq.offer(new Pair(i, x[i]));
    }
    StringJoiner sj = new StringJoiner(" ");
    for (int a : ans) {
      sj.add(Integer.toString(a));
    }
    System.out.println(sj);
    br.close();
  }
}
