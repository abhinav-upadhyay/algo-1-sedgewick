/*
 * Abhinav Upadhyay
 * September 5th, 2012
 * Percolation API Implementation.
 */

public class Percolation {
    private WeightedQuickUnionUF qf;
    private int size;
    private int[][] grid;
    
    public Percolation(final int n) {
        this.size = n;
        this.qf = new WeightedQuickUnionUF(n * n + 2);
        int i = 0;
        this.grid = new int[n][n];
        
        for (i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.grid[i][j] = 0;
    }
    
    /*
     * Translate the i, j coordinates to one dimensional space.
     */
    private int translateIndex(final int i, final int j) {
        int n = this.size;
        return (i * n + j);
    }
    
    /*
     * Validate indices.
     */
    private void checkIndex(final int i, final int j) {
        int n = this.size;
        if (i < 1 || i > n)
            throw new IndexOutOfBoundsException(i + " " + j);
        
        if (j < 1 || j > n)
            throw new IndexOutOfBoundsException(i + " " + j);
    }
    
    /*
     * Open a site with coordinates(i-1, j-1)
     */
    public void open(final int x, final int  y) {
        checkIndex(x, y);
        int i = x - 1;
        int j = y -1;
        this.grid[i][j] = 1;
        int n = this.size;
        if (i == 0) {
            this.qf.union(translateIndex(i, j), n * n + 1);
        } else if (i == n - 1) {
            this.qf.union(translateIndex(i, j), n * n);
        }
        
        if (i > 0 && this.grid[i - 1][j] == 1)
            this.qf.union(translateIndex(i - 1, j),
                    translateIndex(i, j));
        
        if (j > 0 && this.grid[i][j - 1] == 1)
            this.qf.union(translateIndex(i, j -1),
                    translateIndex(i, j));
        
        if (i < n - 1 && this.grid[i + 1][j] == 1)
            this.qf.union(translateIndex(i + 1, j),
                    translateIndex(i, j));
        
        if (j < n - 1 && this.grid[i][j + 1] == 1)
            this.qf.union(translateIndex(i, j + 1),
                    translateIndex(i, j));
        
    }
    
    /*
     * Check whether the site(i, j) is open or not.
     */
    public boolean isOpen(final int i, final int j) {
        checkIndex(i, j);
        return this.grid[i - 1][j - 1] == 1;
    }
    
    /*
     * Check if a site is full or not.
     */
    public boolean isFull(final int i, final int j) {
        checkIndex(i, j);
        int n = this.size;
        return (this.qf.connected(n * n + 1, translateIndex(i - 1, j - 1)));
    }
    
    /*
     * Check if the grid percolates.
     */
    public boolean percolates() {
        int n = this.size;
        return (this.qf.connected(n * n, n * n + 1));
        
    }
}