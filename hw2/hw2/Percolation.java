package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int dimension;
    private int openSites = 0;
    private boolean[][] map;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private  WeightedQuickUnionUF ufNoBottom;
    private int[][] surroundings = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        dimension = N;
        map = new boolean[N][N];  //initialized false for all elements
        top = N * N;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    private int xyto1D(int x, int y) {
        return dimension * x + y;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > dimension - 1 || col < 0 || col > dimension - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (map[row][col]) {
            return;
        }
        map[row][col] = true;
        openSites += 1;
        if (row == 0) {
            uf.union(xyto1D(row, col), top);
            ufNoBottom.union(xyto1D(row, col), top);
        }
        if (row == dimension - 1) {
            uf.union(xyto1D(row, col), bottom);
        }
        for (int[] s : surroundings) {
            int adjrow = row + s[0];
            int adjcol = col + s[1];
            if (adjrow >= 0 && adjrow < dimension && adjcol >= 0 && adjcol < dimension) {
                uf.union(xyto1D(row, col), xyto1D(adjrow, adjcol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > dimension - 1 || col < 0 || col > dimension - 1) {
            throw new IndexOutOfBoundsException();
        }
        return map[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > dimension - 1 || col < 0 || col > dimension - 1) {
            throw new IndexOutOfBoundsException();
        }
        return uf.connected(xyto1D(row, col), top) && uf.connected(xyto1D(row, col), bottom)
                && ufNoBottom.connected(xyto1D(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }

}
