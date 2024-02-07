package Week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 && trials <= 0){
            throw new IllegalArgumentException();
        }
        fractions = new double[trials];
        for(int i  = 0; i < trials; i ++){
//            System.out.println("current trial: " + i);
            Percolation grid = new Percolation(n);
            int count = 0;
            while(!grid.percolates()){
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                if (!grid.isOpen(row, col))
                    grid.open(row, col);
            }
            count = grid.numberOfOpenSites();
            fractions[i] = count * 1.0 / (n * n);
//            System.out.println(fractions[i]);
//            System.out.println("This trial completed!");
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats temp = new PercolationStats(n, trials);

        System.out.println("mean                            =" + temp.mean());
        System.out.println("stddev                          =" + temp.stddev());
        System.out.println("955 confidence interval         =[" + temp.confidenceLo() + ", " + temp.confidenceHi() + "]");
    }

}
