import java.util.*;
import java.io.*;
public class veitch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int w = 0; w < 5; w++) {
            String s = br.readLine();
            boolean[][] grid = new boolean[4][4];
            for (int i = 0; i < 4; i++) {
                int num = s.charAt(i) >= 'A' ? s.charAt(i) - 'A' + 10 : s.charAt(i) - '0';
                for (int j = 3; j >= 0; j--) {
                    grid[i][j]= num % 2 == 0;
                    num /= 2;
                }
            }
            StringJoiner sj = new StringJoiner("+");

            // r0 r1
            if (Arrays.toString(grid[0]).equals("[true, true, true, true]") && Arrays.toString(grid[1]).equals("[true, true, true, true]")) {
                for (int j = 0; j < 4; j++) {
                    grid[0][j] = false;
                    grid[1][j] = false;
                }
                sj.add("B");
            }
            // r1 r2
            if (Arrays.toString(grid[1]).equals("[true, true, true, true]") && Arrays.toString(grid[2]).equals("[true, true, true, true]")) {
                for (int j = 0; j < 4; j++) {
                    grid[1][j] = false;
                    grid[2][j] = false;
                }
                sj.add("D");
            }
            // r2 r3
            if (Arrays.toString(grid[2]).equals("[true, true, true, true]") && Arrays.toString(grid[3]).equals("[true, true, true, true]")) {
                for (int j = 0; j < 4; j++) {
                    grid[2][j] = false;
                    grid[3][j] = false;
                }
                sj.add("~B");
            }
            // c0 c1
            boolean works = true;
            out:
            for (int i = 0; i < 4; i++) {
                for (int j =0; j < 2; j++) {
                    if (!grid[i][j]) {
                        works = false;
                        break out;
                    }
                }
            }
            if (works) {
                for (int i = 0; i < 4; i++) {
                    for (int j =0; j < 2; j++) {
                        grid[i][j] = false;
                    }
                }
                sj.add("A");
            }
            // c1 c2
            works = true;
            out:
            for (int i = 0; i < 4; i++) {
                for (int j =1; j < 3; j++) {
                    if (!grid[i][j]) {
                        works = false;
                        break out;
                    }
                }
            }
            if (works) {
                for (int i = 0; i < 4; i++) {
                    for (int j =1; j < 3; j++) {
                        grid[i][j] = false;
                    }
                }
                sj.add("C");
            }
            // c2 c3
            works = true;
            out:
            for (int i = 0; i < 4; i++) {
                for (int j =2; j < 4; j++) {
                    if (!grid[i][j]) {
                        works = false;
                        break out;
                    }
                }
            }
            if (works) {
                for (int i = 0; i < 4; i++) {
                    for (int j =2; j < 4; j++) {
                        grid[i][j] = false;
                    }
                }
                sj.add("~A");
            }
            // r0 r3
            if (Arrays.toString(grid[0]).equals("[true, true, true, true]") && Arrays.toString(grid[3]).equals("[true, true, true, true]")) {
                for (int j = 0; j < 4; j++) {
                    grid[0][j] = false;
                    grid[3][j] = false;
                }
                sj.add("~D");
            }
            // c0 c3
            works = true;
            out:
            for (int i = 0; i < 4; i++) {
                if (! grid[i][0] || ! grid[i][3]) {
                    works = false;
                    break out;
                } 
            }
            if (works) {
                for (int i = 0; i < 4; i++) {
                    grid[i][0] = false;
                    grid[i][3] = false;
                }
                sj.add("~C");
            }
            if (Arrays.toString(grid[0]).equals("[true, true, true, true]")) {
                sj.add("B~D");
                grid[0] = new boolean[] {false, false, false, false};
            }
            if (Arrays.toString(grid[1]).equals("[true, true, true, true]")) {
                sj.add("BD");
                grid[1] = new boolean[] {false, false, false, false};
            }
            if (Arrays.toString(grid[2]).equals("[true, true, true, true]")) {
                sj.add("~BD");
                grid[2] = new boolean[] {false, false, false, false};
            }
            if (Arrays.toString(grid[3]).equals("[true, true, true, true]")) {
                sj.add("~B~D");
                grid[3] = new boolean[] {false, false, false, false};
            }
            String[] vert = new String[] {"A~C", "AC", "~AC", "~A~C"};
            for (int i = 0; i < 4; i++) {
                if (grid[0][i] && grid[1][i] && grid[2][i] && grid[3][i]) {
                    grid[0][i] = false;
                    grid[1][i] = false;
                    grid[2][i] = false;
                    grid[3][i] = false;
                    sj.add(vert[i]);
                }
            }
            String[] twobytwo = new String[] {"AB", "~AB", "A~B", "~A~B"};
            for (int i = 0; i < 3; i += 2) {
                for (int j = 0; j <= 3; j += 2) {
                    if (grid[i][j] && grid[i][j+1] && grid[i+1][j] && grid[i+1][j+1]) {
                        grid[i][j] = false;
                        grid[i][j+1] = false;
                        grid[i+1][j] = false;
                        grid[i+1][j+1] = false;
                        sj.add(twobytwo[i * 2 + j]);
                    }
                }
            }


        }
    }
}