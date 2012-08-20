class QuickFind():

    def __init__(self, n):
        self.nodes = []
        for i in range(n):
            self.nodes.append(i)

    def connected(self, p, q):
        if self.nodes[p] == self.nodes[q]:
            return True
        else:
            return False

    def union(self, p, q):
        pid = self.nodes[p]
        qid = self.nodes[q]
        for index, node in enumerate(self.nodes):
            if node == pid:
                self.nodes[index] = qid


def main():
    qf = QuickFind(10)
    qf.union(4, 6)
    qf.union(0, 3)
    qf.union(8, 3)
    qf.union(5, 7)
    qf.union(4, 3)
    qf.union(5, 8)

    expected_output = '3 1 2 3 3 3 3 3 3 9'
    output = ' '.join(str(x) for x in qf.nodes)
    assert(expected_output == output)

if __name__ == '__main__':
    main()
