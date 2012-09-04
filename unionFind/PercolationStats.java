import java.lang.Math;

public class PercolationStats {
	private int numExperiment;
	private int size;
	private double threshHold[];
	
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		this.size = N;
		this.numExperiment = T;
		this.threshHold = new double[T];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumExperiment() {
		return this.numExperiment;
	}
	
	public double mean() {
		return StdStats.mean(this.threshHold);
	}
	
	public double stddev() {
		return StdStats.stddevp(this.threshHold);
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		System.out.println(N + " " + T);
		Percolation p1 = new Percolation(N);
		System.out.println(p1.percolates());
			
		PercolationStats ps = new PercolationStats(N, T);
		for (int k = 0; k < T; k++) {
			Percolation p = new Percolation(N);
			int count = 0;
			int table[][] = new int[N][N];
			while (p.percolates() != true) {
				int i = StdRandom.uniform(1, N + 1);
				int j = StdRandom.uniform(1, N + 1);
				
				
				if (table[i-1][j-1] == 1)
					continue;
				table[i-1][j-1] = 1;
				p.open(i, j);
				count++;
			}
			ps.threshHold[k] = (count * 1.0 )/ (N * N);
			//System.out.println(ps.threshHold[k]);
		}
		double mean = ps.mean();
		double stddev = ps.stddev();
		long end = System.nanoTime();
		System.out.println(("mean = " + mean));
		System.out.println(("stddev =" + stddev));
		System.out.println((end - start) * Math.pow(10.0, -9.0) + " s");
	}

	public double[] getThreshhold() {
		return this.threshHold;
	}
	
}
