import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringJoiner;
import java.io.File;
import java.util.ArrayList;
public class WordNet {
    private HashMap<String, Integer> nouns;
    private ArrayList<ArrayList<String>> synsets;
    private Digraph digraph;
    private SAP sap;
    private int source;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws Exception {
        nouns = new HashMap<String, Integer>();
        In syn = new In(synsets);
        this.synsets = new ArrayList<ArrayList<String>>();
        int lastSynset = 0;
        while (syn.hasNextLine()) {
            String[] s = syn.readLine().split(",");
            int id = Integer.parseInt(s[0]);
            String[] ns = s[1].split(" ");
            this.synsets.add(new ArrayList<String>());
            for (String n: ns) {
                nouns.put(n, id);
                this.synsets.get(id).add(n);
            }
            lastSynset = id;
        }
        syn.close();
        In hyp = new In(new File(hypernyms));
        digraph = new Digraph(lastSynset + 1);
        while (hyp.hasNextLine()) {
            String[] hyps = hyp.readLine().split(",");
            int curr = Integer.parseInt(hyps[0]);
            for (int i = 1; i < hyps.length; i++) {
                digraph.addEdge(curr, Integer.parseInt(hyps[i]));
            }
            if (hyps.length == 1) source = curr;
        }
        hyp.close();
        // sap = new SAP(digraph);
        // System.out.println(nouns.get("zymosis"));
    }
 
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        class WordNetIterable<String> implements Iterable<String> {

            @Override
            public Iterator<String> iterator() {
                return (Iterator<String>) nouns.keySet().iterator();
            }
            
        }
        return new WordNetIterable();
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
        //return sap.length(nouns.get(nounA), nouns.get(nounB));
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return "";
        /* 
        StringJoiner sj =new StringJoiner(" ");

        for (String s: synsets.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)))) {
            sj.add(s);
        }
        return sj.toString();
        */

    }
 
    // do unit testing of this class
    public static void main(String[] args) throws Exception {
        WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
    }
 }
