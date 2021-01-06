package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;


public class PercolationStats {

    private PercolationFactory factory;
    private double[] percThreshold;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        factory = pf;
        Percolation curPerc;
        percThreshold = new double[T];
        double totalSites = N * N;
        int index = 0;
        int randRow = 0;
        int randCol = 0;
        while (T > 0) {
            curPerc = factory.make(N);
            while (!curPerc.percolates()) {
                randRow = StdRandom.uniform(0, N);
                randCol = StdRandom.uniform(0, N);
                if (!curPerc.isOpen(randRow, randCol)) {
                    curPerc.open(randRow, randCol);
                }
            }
            percThreshold[index] = (double) curPerc.numberOfOpenSites()/totalSites;
            index += 1;
            T -= 1;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percThreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / (Math.sqrt((double) percThreshold.length)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / (Math.sqrt((double) percThreshold.length)));
    }

    public static void main(String[] args) {
        PercolationFactory x = new PercolationFactory();
        Stopwatch watch;
        int index = 0;
        int index2 = 0;
        Percolation cur;
        PercolationStats stats;
        double[] timeArray = new double[20];
        double[] timeArray2 = new double[20];
        for (int i = 10; i < 1000; i *= 2) {
            watch = new Stopwatch();
            cur = x.make(i);
            timeArray[index] = watch.elapsedTime();
            index += 1;
        }
        for (int i = 0; i < timeArray.length; i++) {
            System.out.println(timeArray[i]);
        }
        System.out.println();
        for (int t = 10; t < 1000; t *= 2) {
            watch = new Stopwatch();
            stats = new PercolationStats(100, t, x);
            timeArray2[index] = watch.elapsedTime();
            index2 += 1;
        }
        for (int i = 0; i < timeArray2.length; i++) {
            System.out.println(timeArray2[i]);
        }
    }
}
