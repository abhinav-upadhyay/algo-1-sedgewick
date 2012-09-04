public class Percolation {
	private WeightedQuickUnionUF qf;
	private int size;
	private int grid[][];
	
	public Percolation(final int N) {
		this.size = N;
		this.qf = new WeightedQuickUnionUF(N * N + 2);
		int i = 0;
		this.grid = new int[N][N];
		
		for (i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				this.grid[i][j] = 0;
	}
	
	private int translateIndex(final int i, final int j) {
		int N = this.size;
		return (i * N + j);
	}
	
	private void checkIndex(final int i, final int j) {
		int n = this.size;
		if (i < 1 || i > n)
			throw new IndexOutOfBoundsException(i + " " + j);
		
		if (j < 1 || j > n)
			throw new IndexOutOfBoundsException(i + " " + j);
	}
	
	public void open(int i, int  j) {
		checkIndex(i, j);
		i--;
		j--;
		this.grid[i][j] = 1;
		int N = this.size;
		if (i == 0) {
			this.qf.union(translateIndex(i, j), N * N + 1);
		} else if (i == N - 1) {
			this.qf.union(translateIndex(i, j), N * N);
		}
		
		if (i > 0 && this.grid[i - 1][j] == 1)
			this.qf.union(translateIndex(i - 1, j), translateIndex(i, j));
		
		if (j > 0 && this.grid[i][j - 1] == 1)
			this.qf.union(translateIndex(i, j -1), translateIndex(i, j));
		
		if (i < N - 1 && this.grid[i + 1][j] == 1)
			this.qf.union(translateIndex(i + 1, j), translateIndex(i, j));
		
		if (j < N - 1 && this.grid[i][j + 1] == 1)
			this.qf.union(translateIndex(i, j + 1), translateIndex(i, j));
		
	}
	
	public boolean isOpen(int i, int j) {
		checkIndex(i, j);
		i--;
		j--;
		return this.grid[i][j] == 1;
	}
	
	public boolean isFull(int i, int j) {
		checkIndex(i, j);
		i--;
		j--;
		int N = this.size;
		return (this.qf.connected(N * N + 1, translateIndex(i, j)));
	}
	
	public boolean percolates() {
		int N = this.size;
		return (this.qf.connected(N * N, N * N + 1));
		
	}
}