import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import edu.princeton.cs.algs4.Stack;
public class SeamCarver {

    private Picture picture;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }
 
    // current picture
    public Picture picture() {
        return new Picture(picture);
    }
 
    // width of current picture
    public int width() {
        return picture.width();
    }
 
    // height of current picture
    public int height() {
        return picture.height();
    }
 
    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) throw new IllegalArgumentException();
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) return 1000;
        Color left = picture.get(x-1, y);
        Color right = picture.get(x + 1, y);
        Color up = picture.get(x, y - 1);
        Color down = picture.get(x, y + 1);
        return Math.sqrt(
            Math.pow(left.getRed() - right.getRed(), 2) + Math.pow(left.getBlue() - right.getBlue(), 2) + Math.pow(left.getGreen() - right.getGreen(), 2) + 
            Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue() - down.getBlue(), 2) + Math.pow(up.getGreen() - down.getGreen(), 2)
        );
    }
 
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int width = width();
        int height = height();
        double[][] distTo = new double[width][height];
        int[][] previousY = new int[width][height];
        double[][] energy = new double[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                energy[i][j] = energy(i, j);
            }

        }

        for (int i = 1; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                distTo[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < height; i++)
        {
            distTo[0][i] = 1000;
            previousY[0][i] = -1;
        }
        for (int i = 0; i < width - 1; i++)
        {
            for (int k = 0; k <= 1; k++)
            {
                if (k >= height) continue;
                if (distTo[i][0] + energy[i + 1][k] <= distTo[i + 1][k])
                {
                    distTo[i + 1][k] = distTo[i][0] + energy[i + 1][k];
                    previousY[i + 1][k] = 0;
                }
            }
            for (int j = 1; j < height - 1; j++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    if (distTo[i][j] + energy[i + 1][j + k] <= distTo[i + 1][j + k])
                    {
                        distTo[i + 1][j + k] = distTo[i][j] + energy[i + 1][j + k];
                        previousY[i + 1][j + k] = j;
                    }
                }
            }
            for (int k = -2; k <= -1; k++)
            {
                if (height + k < 0) continue;
                if (distTo[i][height - 1] + energy[i + 1][height + k] <= distTo[i + 1][height + k])
                {
                    distTo[i + 1][height + k] = distTo[i][height - 1] + energy[i + 1][height + k];
                    previousY[i + 1][height + k] = height - 1;
                }
            }

        }
        double minEnergy = Double.POSITIVE_INFINITY;
        int minIndex = -1;
        for (int i = 0; i < height; i++)
        {   
            if (minEnergy > distTo[width - 1][i])
            {
                minEnergy = distTo[width - 1][i];
                minIndex = i;
            }
        }
        Stack<Integer> stk = new Stack<Integer>();
        int x = width - 1;
        int y = minIndex;
        while (previousY[x][y] != -1)
        {
            stk.push(y);
            y = previousY[x--][y];
        }
        stk.push(y);
        int[] ans = new int[width];
        for (int i = 0; i < width; i++)
        {
            ans[i] = stk.pop();
        }
        return ans;

        
    }
 
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int width = width();
        int height = height();
        double[][] distTo = new double[width][height];
        int[][] previousX = new int[width][height];
        double[][] energy = new double[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                energy[i][j] = energy(i, j);
            }

        }
        for (int i = 0; i < width; i++)
        {
            for (int j = 1; j < height; j++)
            {
                distTo[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < width; i++)
        {
            distTo[i][0] = 1000;
            previousX[i][0] = -1;
        }
        for (int i = 0; i < height - 1; i++)
        {
            for (int k = 0; k <= 1; k++)
            {
                if (k >= width) continue;
                if (distTo[0][i] + energy[k][i + 1] <= distTo[k][i + 1])
                {
                    distTo[k][i + 1] = distTo[0][i] + energy[k][i + 1];
                    previousX[k][i + 1] = 0;
                }
            }
            for (int j = 1; j < width - 1; j++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    if (distTo[j][i] + energy[j + k][i + 1] <= distTo[j + k][i + 1])
                    {
                        distTo[j + k][i + 1] = distTo[j][i] + energy[j + k][i + 1];
                        previousX[j + k][i + 1] = j;
                    }
                }
            }
            for (int k = -2; k <= -1; k++)
            {
                if (width + k < 0) continue;
                if (distTo[width - 1][i] + energy[width + k][i + 1] <= distTo[width + k][i + 1])
                {
                    distTo[width + k][i + 1] = distTo[width - 1][i] + energy[width + k][i + 1];
                    previousX[width + k][i + 1] = width - 1;
                }
            }

        }
        double minEnergy = Double.POSITIVE_INFINITY;
        int minIndex = -1;
        for (int i = 0; i < width; i++)
        {   
            if (minEnergy > distTo[i][height - 1])
            {
                minEnergy = distTo[i][height - 1];
                minIndex = i;
            }
        }
        Stack<Integer> stk = new Stack<Integer>();
        int y = height - 1;
        int x = minIndex;
        while (previousX[x][y] != -1)
        {
            stk.push(x);
            x = previousX[x][y--];
        }
        stk.push(x);
        int[] ans = new int[height];
        for (int i = 0; i < height; i++)
        {
            ans[i] = stk.pop();
        }
        return ans;

    }
 
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (height() <= 1 || seam == null || seam.length != width()) throw new IllegalArgumentException();
        Picture temp = new Picture(width(), height() - 1);
        int prev = seam[0];
        for (int x = 0; x < width(); x++)
        {
            if (seam[x] < 0 || seam[x] >= height() || Math.abs(prev - seam[x]) > 1)
            {
                throw new IllegalArgumentException();
            }

            prev = seam[x];

            for (int y = 0; y < seam[x]; y++)
            {
                temp.set(x, y, picture.get(x, y));
            }
            for (int y = seam[x] + 1; y < height(); y++)
            {
                temp.set(x, y - 1,  picture.get(x, y));
            }
        }
        picture  = temp;
    }
 
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (width() <= 1 || seam == null || seam.length != height()) throw new IllegalArgumentException();
        Picture temp = new Picture(width() - 1, height());
        int prev = seam[0];
        for (int y = 0; y < height(); y++)
        {
            if (seam[y] < 0 || seam[y] >= width() || Math.abs(prev - seam[y]) > 1)
            {
                throw new IllegalArgumentException();
            }
            prev = seam[y];
            for (int x = 0; x < seam[y]; x++)
            {
                temp.set(x, y, picture.get(x, y));
            }
            for (int x = seam[y] + 1; x < width(); x++)
            {
                temp.set(x - 1, y, picture.get(x, y));
            }
        }
        picture  = temp;
    }
 
 
 }