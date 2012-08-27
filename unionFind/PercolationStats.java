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
		double mean = 0.0;
		double sum = 0.0;
		for (int i = 0; i < this.getSize(); i++ ) {
			sum += this.threshHold[i];
		}
		mean = sum / this.getNumExperiment();
		return mean;
	}
	
	public double stddev() {
		double stddev = 0.0;
		double mean = this.mean();
		int size = this.getSize();
		double sum = 0.0;
		for (int i = 0; i < size; i++) {
			sum += Math.pow(this.threshHold[i] - mean, 2.0);
		}
		
		stddev = sum / (this.getNumExperiment() - 1);
		return stddev;
	}
	
	public static void main(String[] args) {
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		double count = 0.0;
		PercolationStats ps = new PercolationStats(N, T);
		for (int k = 0; k < T; k++) {
			Percolation p = new Percolation(N);
			while (p.percolates() != true) {
				int index = StdRandom.uniform(1, N);
				int i = index / N;
				int j = index % N;
				p.open(i, j);
				count++;
			}
			ps.threshHold[k] = count / N;
		}
		
		System.out.println("mean = " + StdStats.mean(ps.getThreshhold()));
		System.out.println("stddev =" + StdStats.stddev(ps.getThreshhold()));
	}

	public double[] getThreshhold() {
		return this.threshHold;
	}
	
	
	

}