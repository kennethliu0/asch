import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
    private int[] w, l, r;
    private String[] t;
    private int[][] g;
    private int n;
    private HashMap<String, Integer> names;
    private int[][] gameIndex;
    private HashMap<Integer, Integer[]> indexToTeam;
    public BaseballElimination(String filename) {
        In in = new In(filename);
        n = in.readInt();
        w = new int[n];
        l = new int[n];
        r = new int[n];
        t = new String[n];
        g = new int[n][n];
        names=  new HashMap<>(n);
        for (int i = 0; i < n; i++)
        {
            t[i] = in.readString();
            w[i] = in.readInt();
            l[i] = in.readInt();
            r[i] = in.readInt();
            names.put(t[i], i);
            for (int j = 0; j < n; j++)
            {
                g[i][j] = in.readInt();
            }
        }
        gameIndex = new int[n][n];
        int startingIndex = n;
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                gameIndex[i][j] = startingIndex++;
            }

        }
        

    }
    public int numberOfTeams()
    {
        return n;
    }
    public Iterable<String> teams()
    {
        class BaseballIterable implements Iterable<String> {

            @Override
            public Iterator<String> iterator() 
            {
                return Arrays.asList(t).iterator();
            }
            
        }
        return new BaseballIterable();

    }
    public int wins (String team)
    {
        if (team == null || ! names.containsKey(team)) throw new IllegalArgumentException();
        return w[names.get(team)];
    }
    public int losses(String team)
    {
        if (team == null || ! names.containsKey(team)) throw new IllegalArgumentException();
        return l[names.get(team)];
    }
    public int remaining (String team)
    {
        if (team == null || ! names.containsKey(team)) throw new IllegalArgumentException();
        return r[names.get(team)];
    }
    public int against (String team1, String team2)
    {
        if (team1 == null || team2 == null || ! names.containsKey(team1) || ! names.containsKey(team2)) throw new IllegalArgumentException();
        return g[names.get(team1)][names.get(team2)];
    }

    public boolean isEliminated (String team)
    {
        if (team == null || ! names.containsKey(team)) throw new IllegalArgumentException();
        FlowNetwork f = new FlowNetwork(2 + n * (n-1) / 2 + n);
        int s = n * (n - 1) / 2 + n;
        int t = n * (n - 1) / 2 + n + 1;
        // teams are in 0 - n
        // i - j is
        int teamIndex = names.get(team);
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                f.addEdge(new FlowEdge(s, gameIndex[i][j], g[i][j]));
                f.addEdge(new FlowEdge(gameIndex[i][j], i, Double.POSITIVE_INFINITY));
                f.addEdge(new FlowEdge(gameIndex[i][j], j, Double.POSITIVE_INFINITY));
            }
            if (w[teamIndex] + r[teamIndex] - w[i] < 0) return true;
            if (i != teamIndex)
                f.addEdge(new FlowEdge(i, t, w[teamIndex] + r[teamIndex] - w[i]));
            else
                f.addEdge(new FlowEdge(i, t, r[teamIndex]));
        }
        FordFulkerson ff = new FordFulkerson(f, s, t);
        for (FlowEdge e: f.adj(s))
        {
            if (e.flow() != e.capacity()) return true;
        }
        return false;

    }
    public Iterable<String> certificateOfElimination(String team)
    {
        if (team == null || ! names.containsKey(team)) throw new IllegalArgumentException();

        Bag<String> edgesInCut = new Bag<>();
        class CertificateIterable implements  Iterable<String>
        {

            @Override
            public Iterator<String> iterator() {
               return edgesInCut.iterator();
            }
            
        }
        FlowNetwork f = new FlowNetwork(2 + n * (n-1) / 2 + n);
        int s = n * (n - 1) / 2 + n;
        int t = n * (n - 1) / 2 + n + 1;
        // teams are in 0 - n
        // i - j is
        int teamIndex = names.get(team);
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                f.addEdge(new FlowEdge(s, gameIndex[i][j], g[i][j]));
                f.addEdge(new FlowEdge(gameIndex[i][j], i, Double.POSITIVE_INFINITY));
                f.addEdge(new FlowEdge(gameIndex[i][j], j, Double.POSITIVE_INFINITY));
            }
            if (w[teamIndex] + r[teamIndex] - w[i] < 0) {
                edgesInCut.add(this.t[i]);
                return new CertificateIterable();
            }
            if (i != teamIndex)
                f.addEdge(new FlowEdge(i, t, w[teamIndex] + r[teamIndex] - w[i]));
            else
                f.addEdge(new FlowEdge(i, t, r[teamIndex]));
        }
        FordFulkerson ff = new FordFulkerson(f, s, t);
        boolean eliminated = false;
        for (FlowEdge e: f.adj(s))
        {
            if (e.flow() != e.capacity()) {
                eliminated = true;
                break;
            }
        }
        
        
        if (eliminated)
        {
            for (int i = 0; i < n; i++)
            {
                if (ff.inCut(i)) {
                    edgesInCut.add(this.t[i]);
                }
            }
            return new CertificateIterable();
        } else {
            return null;
        }
        
            



    }
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }   
    }
   
}
