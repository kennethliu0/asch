import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Stack;
public class SAP {
    private Digraph G;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G;
    }
 
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        ArrayList<Integer> vparents = new ArrayList<Integer>();
        boolean[] visitedV = new boolean[G.V()];
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(v);
        while (! stk.isEmpty()) {
            visitedV[stk.peek()] = true;
            vparents.add(stk.peek());
            for (int x: G.adj(stk.pop())) {
                if (! visitedV[x]) stk.push(x);
            }
        }
        boolean[] visitedW = new boolean[G.V()];
        stk = new Stack<>();
        stk.push(w);
        while (! stk.isEmpty()) {
            visitedV[stk.peek()] = true;
            vparents.add(stk.peek());
            for (int x: G.adj(stk.pop())) {
                if (! visitedW[x]) stk.push(x);
            }
        }
        for (int i = 0; i < G.V(); i++) {
            
        }


    }

 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
 
    // do unit testing of this class
    public static void main(String[] args)
 }