public class WeightedQuickUnion {
    private int array[];
    private int sz[];

    public WeightedQuickUnion(int n) {
        this.array = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = i;
            this.sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != this.array[i]) {
            i = this.array[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        if (root(p) == root(q))
            return true;
        else
            return false;
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (sz[proot] < sz[qroot]) {
            array[proot] = array[qroot];
            sz[qroot] += sz[proot];
        } else {
            array[qroot] = array[proot];
            sz[proot] += sz[qroot];
        }
    }

    public static void main(String args[]) {
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        wqu.union(9, 5);
        wqu.union(8, 2);
        wqu.union(8, 9);
        wqu.union(7, 4);
        wqu.union(6, 3);
        wqu.union(4, 3);
        wqu.union(4, 1);
        wqu.union(3, 2);
        wqu.union(1, 0);

        for (int i = 0; i < 10; i++)
            System.out.print(new Integer(wqu.array[i]).toString() + ' ');
        System.out.print('\n');
    }
}
