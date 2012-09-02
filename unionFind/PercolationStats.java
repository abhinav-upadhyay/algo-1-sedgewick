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
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		System.out.println(N + " " + T);
		Percolation p1 = new Percolation(N);
		System.out.println(p1.percolates());
			
		PercolationStats ps = new PercolationStats(N, T);
		for (int k = 0; k < T; k++) {
			Percolation p = new Percolation(N);
			int count = 0;
			int table[] = new int[N*N];
			while (p.percolates() != true) {
				int index = StdRandom.uniform(N * N);
				
				if (table[index] == 1)
						continue;
				table[index] = 1;
				
				int i = index / (N);
				int j = index % (N); 
				p.open(i, j);
				count++;
			}
			ps.threshHold[k] = (count * 1.0 )/ (N*N);
			System.out.println(ps.threshHold[k]);
		}
		
		System.out.println("mean = " + StdStats.mean(ps.getThreshhold()));
		System.out.println("stddev =" + StdStats.stddev(ps.getThreshhold()));
	}

	public double[] getThreshhold() {
		return this.threshHold;
	}
	
	
	

}
