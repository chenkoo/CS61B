package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private int[] percoThred;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        percoThred = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            percoThred[i] = p.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        int sum = 0;
        for (int i = 0; i < percoThred.length; i++) {
            sum += percoThred[i];
        }
        double m = (double) sum / percoThred.length;
        return m;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        int sum = 0;
        double m = mean();
        for (int i = 0; i < percoThred.length; i++) {
            sum += (percoThred[i] - m) * (percoThred[i] - m);
        }
        return Math.sqrt(sum / (percoThred.length - 1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double m = mean();
        double sd = stddev();
        return m - 1.96 * sd / Math.sqrt(percoThred.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double m = mean();
        double sd = stddev();
        return m + 1.96 * sd / Math.sqrt(percoThred.length);
    }

}
