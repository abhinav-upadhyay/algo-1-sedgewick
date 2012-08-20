public class QuickUnion {
    private int array[];

    public QuickUnion(int n) {
        this.array = new int[n];
        for (int i = 0; i < n; i++)
            this.array[i] = i;
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
        this.array[p] = qroot;
    }
}
