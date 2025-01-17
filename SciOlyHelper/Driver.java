
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class Driver {
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
        System.out.println(dfs(0, 0, 0));

    }
    static Result dfs(long x, int size, int j) {
        double temp = getTeam();
        if (size == 15) return temp; // team is full
        int index = 34 - j;
        for (Entry<String, Double> event: students.get(dir[j]).entrySet()) {
            events.get(event.getKey()).add(event.getValue());
        }
        double newTemp = dfs();
        for (Entry<String, Double> event: students.get(dir[j]).entrySet()) {
            events.get(event.getKey()).add(event.getValue());
        }
    }
}