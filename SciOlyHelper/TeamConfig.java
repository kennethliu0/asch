import java.io.File;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Scanner;
public class TeamConfig {
    private HashMap<String, TreeSet<Double>> events;
    private int numEvents;
    public TeamConfig() {
        TeamConfig(23);
    }
    public TeamConfig(int numEvents) {
        events = new HashMap<String, TreeSet<Double>>(23);
    }
    public TeamConfig(String file) throws Exception {
        Scanner in = new Scanner(new File(file));
        numEvents = in.nextInt();
        
    }
}