import java.io.File;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Scanner;
public class TeamConfig {
    private HashMap<String, TreeSet<Double>> events;
    private int numEvents;
    public TeamConfig() {
        this(23);
    }
    public TeamConfig(int numEvents) {
        events = new HashMap<String, TreeSet<Double>>(23);
    }
    public TeamConfig(String file) throws Exception {
        this(23);
        Scanner in = new Scanner(new File(file));
        numEvents = in.nextInt();
        in.nextLine(); // flush buffer
        for (int i = 0; i < numEvents; i++) {
            events.put(in.nextLine(), new TreeSet<Double>());
        }
    }
    public boolean remove(String event, double score) {
        return events.get(event).remove(score);
    }
    public boolean add(String event, double score) {
        return events.get(event).add(score);
    }
    public double getTotal() {
        double ans = 0;
        for (TreeSet<Double> i: events.values()) {
            if (i.size() < 1) return -1;
            ans += i.first() + i.higher(i.first());
        }
        return ans / events.size();
    }

}