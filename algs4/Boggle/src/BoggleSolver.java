import java.util.HashSet;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver
{
    private TrieNode root;
    private class TrieNode
    {
        char c;
        TrieNode[] adj;
        boolean hasWord;
        TrieNode prev;
        TrieNode(char c, TrieNode p)
        {
            this.c = c;
            hasWord = false;
            adj = new TrieNode[26];
            prev = p;
        }
    }

    public BoggleSolver(String[] dictionary)
    {
        root = new TrieNode(' ', null);
        for (int i = 0; i < dictionary.length; i++)
        {
            TrieNode curr = root;
            for (int j = 0; j < dictionary[i].length(); j++)
            {
                char c = dictionary[i].charAt(j);
                if (curr.adj[c - 'A'] == null) {
                    curr.adj[c - 'A'] = new TrieNode(c, curr);
                }
                curr = curr.adj[c-'A'];
            }
            curr.hasWord = true;
        }
    }
    private HashSet<String> potentialWords;
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        potentialWords = new HashSet<>();
        for (int i = 0; i < board.rows(); i++)
        {
            for (int j = 0; j < board.cols(); j++)
            {
                char c = board.getLetter(i, j);
                if (root.adj[c - 'A'] != null)
                {
                    if (c == 'Q') {
                        if (root.adj['Q'-'A'].adj['U' - 'A'] == null) {
                            continue;
                        }
                        dfs(root.adj['Q'-'A'].adj['U'-'A'], new boolean[board.rows()][board.cols()], i, j, board);
                    } else {
                        dfs(root.adj[c-'A'], new boolean[board.rows()][board.cols()], i, j, board);

                    }


                }
                
            }
        }
        class BoggleIterable implements Iterable<String>
        {
            public Iterator<String> iterator()
            {
                return potentialWords.iterator();
            }

        }
        return new BoggleIterable();
    }   
    private final int[] dx = {0,-1,-1,-1,0,1,1,1};
    private final int[] dy = {1,1,0,-1,-1,-1,0,1};
    private void dfs(TrieNode t, boolean[][] visited, int i, int j, BoggleBoard board)
    {
        visited[i][j] = true;
        if (t.hasWord) 
        {
            TrieNode temp = t;
            StringBuffer sb = new StringBuffer();
            while (temp.c != ' ')
            {
                sb.append(temp.c);
                temp = temp.prev;
            }
            String s = sb.reverse().toString();
            if (s.length() >= 3)
            {
                potentialWords.add(s);

            }
        }

        for (int a = 0; a < 8; a++) {
            int x = i + dx[a];
            int y = j + dy[a];
            if (x >= visited.length  || x < 0 || y < 0|| y >= visited[x].length ||  visited[x][y]) continue;
            else{
                
                char c = board.getLetter(x, y);
                if (t.adj[c - 'A'] == null) 
                {
                    continue;
                }
                if (c == 'Q') {
                    if (t.adj['Q'-'A'].adj['U'-'A'] != null) {
                        dfs(t.adj['Q'-'A'].adj['U'-'A'], visited, x, y, board);
                    }
                } else
                {

                    dfs(t.adj[c - 'A'], visited, x, y, board);
                }

            }
        }
        visited[i][j] = false;
        
    }


    public int scoreOf(String word)
    {
        TrieNode t = root;
        for (char c: word.toCharArray())
        {
            if (t.adj[c - 'A'] == null) return 0;
            t = t.adj[c-'A'];
        }
        if (! t.hasWord) return 0;

        int n = word.length();
        if (n <= 2) return 0;
        switch (n)
        {
            case 7: return 5;
            case 6: return 3;
            case 5: return 2;
            case 4: return 1;
            case 3: return 1;
            default: return 11;
        }
        
    }

    public static void main(String[] args) {
    In in = new In("dictionary-algs4.txt");

    String[] dictionary = in.readAllStrings();
    BoggleSolver solver = new BoggleSolver(dictionary);
    /* long startTime = System.nanoTime();
    long duration = 1_000_000_000L;
    int count = 0;
    while (System.nanoTime() - startTime <= duration) {
        
        BoggleBoard board = new BoggleBoard(4, 4);
        solver.getAllValidWords(board);
        
        count++;
    }
    StdOut.println(count); */
    BoggleBoard board = new BoggleBoard("board-9q.txt");
    int score = 0;
    for (String word : solver.getAllValidWords(board)) {
        StdOut.println(word);
        score += 1;
    }
    StdOut.println("Score = " + score);
    StdOut.println(solver.scoreOf("POPPED"));

}


}

