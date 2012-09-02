public class Percolation {
	private QuickFindUF qf;
	private int size;
	private int grid[][];
	public Percolation(int N) {
		this.size = N;
		this.qf = new QuickFindUF(N * N + 2);
		int i = 0;
		this.grid = new int[N][N];
		
		for (i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				this.grid[i][j] = 0;
		i = 0;
		while (i < N)
			qf.union(N * N, i++);
		
		for (i = N * (N -1) -1; i < N * N; i++)
			qf.union(i, N * N + 1);
	}
	
	private int translateIndex(int i, int j) {
		return (i + 1) * (j + 1) - 1;
	}
	
	
	public void open(int i, int  j) {
		this.grid[i][j] = 1;
		int N = this.size;
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
		return (this.grid[i][j]) == 1;
	}
	
	public boolean isFull(int i, int j) {
		int N = this.size;
		return (this.qf.connected(N * N, translateIndex(i, j)));
	}
	
	public boolean percolates() {
		int N = this.size;
		return (this.qf.connected(N * N, N * N + 1));
		
	}
}