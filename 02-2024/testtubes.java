import java.util.*;
import java.io.*;
public class testtubes {
    static class Color {
        int color;
        int length;
        public Color(int c, int l) {
            color = c;
            length = l;
        }
        public String toString() {
            return "("  + color + "=" + length + ")";
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            ArrayDeque<Color> first = new ArrayDeque<>();
            for (char c: br.readLine().toCharArray()) {
                if (first.isEmpty()) {
                    first.addFirst(new Color(c - '0', 1));
                } else {
                    if (first.peekFirst().color == c - '0') {
                        first.peekFirst().length++;
                    } else {
                        first.addFirst(new Color(c - '0', 1));
                    }
                }
            }
            ArrayDeque<Color> sec = new ArrayDeque<>();
            for (char c: br.readLine().toCharArray()) {
                if (sec.isEmpty()) {
                    sec.addFirst(new Color(c - '0', 1));
                } else {
                    if (sec.peekFirst().color == c - '0') {
                        sec.peekFirst().length++;
                    } else {
                        sec.addFirst(new Color(c - '0', 1));
                    }
                }
            }
            // System.out.println(first + "\n" + sec);
            ArrayDeque<Color> third = new ArrayDeque<>();

            int ans = 0;
            StringJoiner sj1 = new StringJoiner("\n");
            if (first.size() == 2) {
                third.addFirst(first.pollFirst());
                ans++;
                sj1.add("1 3");
                while (sec.size() > 1) {
                    if (sec.getFirst().color == first.getFirst().color) {
                        sj1.add("2 1");
                    } else {
                        sj1.add("2 3");
                    }
                    sec.pollFirst();
                    ans++;
                }
                if (third.getFirst().color == sec.getFirst().color){ 
                    third.pollFirst();
                    sj1.add("3 2");
                    ans++;
                } else {
                    sj1.add("2 1");
                    sj.add("3 2");
                    ans += 2;
                }

            }
            while (first.size() > 1) {
                if (first.getFirst().color == sec.getFirst().color) {
                    sec.getFirst().length += first.pollFirst().length;
                    sj1.add("1 2");
                } else {
                    if (third.isEmpty() ) {
                        third.addFirst(first.pollFirst());
                    }
                    else 
                        third.getFirst().length += first.pollFirst().length;
                    sj1.add("1 3");
                }
                ans++;
            }
            if (first.getFirst().color == sec.getFirst().color) {
                first.getFirst().length += sec.pollFirst().length;
                ans++;
                sj1.add("2 1");
            } else {
                first.getFirst().length += third.pollFirst().length;
                ans++;
                sj1.add("3 1");
            }
            while (sec.size() > 1) {
                if (sec.getFirst().color == first.getFirst().color) {
                    first.getFirst().length += sec.pollFirst().length;
                    sj1.add("2 1");
                }
                else {
                    if (third.isEmpty() )
                        third.addFirst(sec.pollFirst());
                    else {
                        if (third.getFirst().color != sec.getFirst().color) {
                            first.getFirst().length += third.pollFirst().length;
                            third.addFirst(sec.pollFirst());
                            sj1.add("3 1");
                            ans++;
                        } else {
                            third.getFirst().length += sec.pollFirst().length;

                        }

                    }
                    sj1.add("2 3");
                }
                ans++;
            }
            if (! third.isEmpty()) {
                if (third.getFirst().color == first.getFirst().color) {
                    first.getFirst().length += third.pollFirst().length;
                    sj1.add("3 1");
                } else {
                    sec.getFirst().length += third.pollFirst().length;
                    sj1.add("3 2");
                }
                ans++;
            }

           
            sj.add(Integer.toString(ans));
            if (P > 1) sj.add(sj1.toString());
        }
        System.out.println(sj);
        br.close();
    }
}