import java.util.*;
import java.io.*;
public class interview1 {
    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new FileReader("10.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] t= new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            t[i] =Long.parseLong(st.nextToken());
        }
        br.close();
        TreeMap<Long, HashSet<Integer>> times = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            if (! times.containsKey(t[i])) {
                times.put(t[i], new HashSet<Integer>());
            } 
            times.get(t[i]).add(i);
        }
        TreeMap<Long, HashSet<Integer>> events = new TreeMap<>((e1, e2)-> Long.compare(e2, e1));
        for (int i = K; i < N; i++) {
            if (times.firstEntry().getValue().size() > 1) {
                events.put(times.firstKey(), new HashSet<>());
                for (int j: times.firstEntry().getValue()) {
                    events.get(times.firstKey()).add(j);
                }
            }
            for (int j: times.firstEntry().getValue()) {
                if (i >= N ) break;
                if (! times.containsKey(t[i] + times.firstKey())) {
                    times.put(t[i] + times.firstKey(), new HashSet<Integer>());
                }
                times.get(t[i]  + times.firstKey()).add(j);
                i++;
            }
            i--;
            times.pollFirstEntry();
        }
        long ans = times.firstKey();
        boolean[] can_interview = new boolean[K];
        for (int i: times.firstEntry().getValue()) {
            can_interview[i] = true;
        }
        // System.out.println(Arrays.toString(can_interview));
        for (HashSet<Integer> event: events.values()) {
            boolean interviews = false;
            for (int i: event) {
                if (can_interview[i]) interviews = true;
            }
            if (interviews) {
                for (int i: event) {
                    can_interview[i] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (boolean b: can_interview) {
            sb.append(b ? "1" : "0");
        }
        System.out.println(ans);
        System.out.println(sb);
        // System.out.println(events);

    }
}