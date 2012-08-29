//import QuickFindUF;


public class Percolation {
	private int grid[][];
	private static int BLOCKED = 0;
	private static int OPEN = 1;
	private static int FULL = 2;
	private int size;
	private QuickFindUF qf;
	
	public Percolation(int N) {
		this.size = N;
		this.grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.grid[i][j] = this.BLOCKED;
			}
		}
		this.qf = new QuickFindUF(N * N);
	}
	
	private int getSize() {
		return this.size;
	}
	
	private void checkIndices(int i, int j) {
		
		if (i < 1 || i > this.getSize()) 
			throw new IndexOutOfBoundsException(i + " " + j);
		
		if (j < 1 || j > this.getSize())
			throw new IndexOutOfBoundsException(i + " " + j);
	}
	
	private int translateIndices(int i, int j) {
		return (i * j);
	}
	
	public void open(int i, int j) {
		checkIndices(i, j);
		i--;
		j--;
		this.grid[i][j] = this.OPEN;
		
		if ( i > 0 && this.grid[i-1][j] == this.OPEN)
			this.qf.union(this.translateIndices(i - 1, j),
					this.translateIndices(i, j));
		
		if (i < this.getSize()  - 1 && this.grid[i + 1][j] == this.OPEN)
			this.qf.union(this.translateIndices(i + 1, j),
					this.translateIndices(i, j));
		
		if (j > 0 && this.grid[i][j - 1] == this.OPEN)
			this.qf.union(this.translateIndices(i, j - 1), this.translateIndices(i, j));
		
		if (j < this.getSize() - 1 && this.grid[i][j + 1] == this.OPEN)
			this.qf.union(this.translateIndices(i, j + 1), this.translateIndices(i, j));
		
	}
	
	public boolean isOpen(int i, int j) {
		checkIndices(i, j);
		i--;
		j--;
		if (this.grid[i][j] == this.OPEN)
			return true;
		else
			return false;
	}
	
	public boolean isFull(int i, int j) {
		checkIndices(i, j);
		//i--;
		//j--;
		int index = this.translateIndices(i, j);
		index--;
		for (int firstRowIndex = 0; firstRowIndex < index; firstRowIndex++) {
			if (this.qf.connected(firstRowIndex, index))
				return true;
			else
				continue;
		}
		return false;
		
	}
	
	public boolean percolates() {
		int N = this.getSize();
		//for (int lastRowIndex = N * (N - 1); lastRowIndex < N * N; lastRowIndex++) {
			for (int firstRowIndex = 1; firstRowIndex <= N; firstRowIndex++) {
				//System.out.println(lastRowIndex + " " + firstRowIndex);
				/*if (this.qf.connected(lastRowIndex, firstRowIndex))
					return true;
				else
					continue;*/
				if (this.isFull(N, firstRowIndex))
					return true;
				else
					continue;
					
			}
		//}
		return false;
	}

}