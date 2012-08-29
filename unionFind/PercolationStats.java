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
		System.out.println(N + " " + T);
		Percolation p1 = new Percolation(N);
		System.out.println(p1.percolates());
			
		PercolationStats ps = new PercolationStats(N, T);
		for (int k = 0; k < T; k++) {
			Percolation p = new Percolation(N);
			int count = 0;
			while (p.percolates() != true) {
				int index = StdRandom.uniform(N * N);
				//index++;
				int i = index / (N) + 1;
				int j = index % (N) + 1; 
				p.open(i, j);
				count = count + 1;
				System.out.println(count);
			}
			ps.threshHold[k] = count * 1.0 / N;
			System.out.println(count);
		}
		
		System.out.println("mean = " + StdStats.mean(ps.getThreshhold()));
		System.out.println("stddev =" + StdStats.stddev(ps.getThreshhold()));
	}

	public double[] getThreshhold() {
		return this.threshHold;
	}
	
	
	

}
