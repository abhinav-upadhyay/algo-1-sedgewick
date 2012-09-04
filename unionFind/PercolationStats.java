/*
 * Abhinav Upadhyay
 * September 5th, 2012.
 * PercolationStats implementation.
 */
public class PercolationStats {
    private int numExperiment;
    private int size;
    private double[] threshHold;
    
    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException();
        
        this.size = n;
        this.numExperiment = t;
        this.threshHold = new double[t];
    }
    
    
    /*
     * Return the mean
     */
    public double mean() {
        return StdStats.mean(this.threshHold);
    }
    
    /*
     * Return the standard deviation.
     */
    public double stddev() {
        return StdStats.stddev(this.threshHold);
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
             
        PercolationStats ps = new PercolationStats(n, t);
        for (int k = 0; k < t; k++) {
            Percolation p = new Percolation(n);
            int count = 0;
            int[][] table = new int[n][n];
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                
                if (table[i-1][j-1] == 1)
                    continue;
                table[i-1][j-1] = 1;
                p.open(i, j);
                count++;
            }
            ps.threshHold[k] = (count * 1.0)/(n * n);
        }
 
        double mean = ps.mean();
        double stddev = ps.stddev();
        System.out.println(("mean = " + mean));
        System.out.println(("stddev =" + stddev));
    }
}
