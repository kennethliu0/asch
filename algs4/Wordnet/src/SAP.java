import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class SAP {
    private Digraph G;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= G.V() || w < 0 || w >= G.V()) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bf2 = new BreadthFirstDirectedPaths(G, w);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++)
        {
            if (bf.hasPathTo(i) && bf2.hasPathTo(i))
            {
                minDist = Math.min(minDist, bf.distTo(i) + bf2.distTo(i));
            }

        }
        return (minDist == Integer.MAX_VALUE) ? -1 : minDist;
    }
 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= G.V() || w < 0 || w >= G.V()) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bf2 = new BreadthFirstDirectedPaths(G, w);
        int minDist = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < G.V(); i++)
        {
            if (bf.hasPathTo(i) && bf2.hasPathTo(i))
            {
                int dist = bf.distTo(i) + bf2.distTo(i);
                if (dist < minDist)
                {
                    minDist = dist;
                    ancestor = i;
                }
            }

        }
        return ancestor;
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        Iterator<Integer> it = v.iterator();
        Iterator<Integer> it2 = w.iterator();
        if (! it.hasNext() || ! it2.hasNext()) return -1;
        while (it.hasNext())
        {
            if (it.next() == null) throw new IllegalArgumentException();
        }
        while (it2.hasNext())
        {
            if (it2.next() == null) throw new IllegalArgumentException();
        }
        


        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        
        BreadthFirstDirectedPaths bf2 = new BreadthFirstDirectedPaths(G, w);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++)
        {
            if (bf.hasPathTo(i) && bf2.hasPathTo(i))
            {
                minDist = Math.min(minDist, bf.distTo(i) + bf2.distTo(i));
            }

        }
        return (minDist == Integer.MAX_VALUE) ? -1 : minDist;
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bf2 = new BreadthFirstDirectedPaths(G, w);
        int minDist = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < G.V(); i++)
        {
            if (bf.hasPathTo(i) && bf2.hasPathTo(i))
            {
                int dist = bf.distTo(i) + bf2.distTo(i);
                if (dist < minDist)
                {
                    minDist = dist;
                    ancestor = i;
                }
            }

        }
        return ancestor;
    }
 
    // do unit testing of this class
    public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
     while (!StdIn.isEmpty()) {
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        int length   = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}
 }