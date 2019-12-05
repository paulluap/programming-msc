package cases;

public class UF {
    //datastructure + efficient query
    //site indexed array

    private int id[];

    //size of component
    private int size[];
    private int count;

    public static void main(String[] args) {
        UF uf = new UF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(8,9);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        uf.union(6,7);
        System.out.println(uf.connected(1,8));
        System.out.println(uf.connected(1,6));
    }


    public UF(int N){
        this.id = new int[N];
        this.size = new int[N];
        count = N;
        //initialize each site in its own component
        for(int i=0; i<N; i++){ id[i] = i; }
        for(int i=0; i<N; i++) { size[i] = 1; }
    }

    /**
     * components identifier for p
     * @param p
     * @return
     */
    public int find(int p) {
        while(id[p] != p){
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        //link smaller tree to larger tree
        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] = size[pRoot] + size[qRoot];
        }else{
            id[qRoot] = pRoot;
            size[pRoot] = size[qRoot] + size[pRoot];
        }
        count --;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * number of components
     * @return
     */
    public int count(){
        return count;
    }
}
