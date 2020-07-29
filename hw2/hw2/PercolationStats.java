package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private double[] percoThred;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        percoThred = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            percoThred[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < percoThred.length; i++) {
            sum += percoThred[i];
        }
        return sum / percoThred.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0.0;
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
