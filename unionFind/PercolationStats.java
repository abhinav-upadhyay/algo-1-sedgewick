import java.lang.Math;

public class PercolationStats {
	private Percolation p;
	private int numExperiment;
	private int size;
	private double threshHold[];
	
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		this.size = N;
		this.numExperiment = T;
		this.p = new Percolation(N);
		this.threshHold = new double[N];
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
		
	}
	
	
	

}
