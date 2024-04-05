import java.util.*;
import java.io.*;
public class tree {
    static class Node {
        Node l, r; char val;
        public Node(char value) {
            val = value;
        }
        
    }
    static class BST {
        Node parent;
        public BST() {
        }
        public void add(char val) {
            if (parent == null) {
                parent = new Node(val);
                return;
            }
            Node p = parent;
            while (true) {
                if (val > p.val) {
                    if (p.r == null) {
                        p.r = new Node(val);
                        break;
                    }
                    p = p.r;
                } else {
                    if (p.l == null) {
                        p.l = new Node(val);
                        break;
                    }
                    p = p.l;
                }
            }
        }
        public String preOrder(Node n) {
            return n == null ? "" : "" +  n.val + preOrder(n.l) + preOrder(n.r);
        }
        public String postOrder(Node n) {
            return n == null ? "" : "" + postOrder(n.l) + postOrder(n.r) + n.val;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            BST b = new BST();
            for (char c: in.nextLine().toCharArray()) {
                b.add(c);
            } 
            System.out.println(b.preOrder(b.parent) + " " + b.postOrder(b.parent));
        }
        in.close();
    }
}