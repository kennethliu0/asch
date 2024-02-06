
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class Driver {
    static HashMap<String, TreeSet<Double>> events;
    static TreeMap<String, TreeMap<String, Double>> students;
    static String[] dir;
    public static void main(String[] args) throws Exception {
        events = new HashMap<>();
        BufferedReader fr = new BufferedReader(new FileReader("events.txt"));
        int N = Integer.parseInt(fr.readLine());
        for (int i = 0; i < N; i++) {
            events.put(fr.readLine(), new TreeSet<Double>());
        }
        fr.close();
        students = new TreeMap<>();
        fr = new BufferedReader(new FileReader("students.txt"));
        N = Integer.parseInt(fr.readLine());
        dir = new String[N];
        for (int i =0; i < N; i++) {
            String name = fr.readLine();
            dir[i] = name;
            students.put(name, new TreeMap<String, Double>());
            int x = Integer.parseInt(fr.readLine());
            for (int j = 0; j < x; j++) {
                String s = fr.readLine();
                students.get(name).put(s.substring(0, s.lastIndexOf(" ")), Double.parseDouble(s.substring(s.lastIndexOf(" ") + 1)));

            }   
        }
        System.out.println(dfs(0, 0));

    }
    static double dfs(int x, int size) {
        if (size == 15) return getTeam();
        double temp = getTeam();
        for (Entry<String, Double> event: students.get(dir[x]).entrySet()) {
            events.get(event.getKey()).add(event.getValue());
        }
    }
    static double getTeam() {
        double ans = 0;
        for (TreeSet<Double> i: events.values()) {
            if (i.size() < 1) return -1;
            ans += i.first() + i.higher(i.first());
        }
        return ans;
    }
}