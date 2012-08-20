public class QuickFind {
    int array[];

    public QuickFind(int n) {
        this.array = new int[n];
        for (int i = 0; i < n; i++)
            this.array[i] = i;
    }

    public boolean connected(int p, int q) {
        if (this.array[p] == this.array[q]) {
            return true;
        } else {
            return false;
        }
    }

    public void union(int p, int q) {
       int  pid = this.array[p];
       int  qid = this.array[q];
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == pid) {
                this.array[i] = qid;
            }
        }
    }

    public static void main(String args[]) {
        QuickFind qf = new QuickFind(10);
        qf.union(4,6);
        qf.union(0,3);
        qf.union(8,3);
        qf.union(5,7);
        qf.union(4,3);
        qf.union(5,8);

        for (int i =0; i < qf.array.length; i++)
            System.out.print(new Integer(qf.array[i]).toString() + ' ');
        System.out.print('\n');
           
    }
}
