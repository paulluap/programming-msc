package datastructure;

import java.util.NoSuchElementException;

public class IndexMinPQ<Item extends Comparable<Item>> {

    private int n;
    private int pq[];
    private int qp[]; //qp[pq[i]] == qp[pq[i]] = i
    private Item keys[];

    public IndexMinPQ(int maxN){
        pq =  new int[maxN + 1];
        qp =  new int[maxN + 1];
        keys = (Item[]) new Comparable[maxN + 1];
        n = 0;
        for(int i=0; i<=maxN; i++) qp[i] = -1;
    }
    /**
     * insert item, associate it with k
     */
    public void insert(int k, Item item){
        n++;
        pq[n]=k;
        qp[k]=n;
        keys[k] = item;
        swim(n);
    }

    /**
     * change the item associated with k to item
     */
    public void change(int k, Item item){
        keys[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    /**
     * is k associated with some item
     */
    public boolean contains(int k){
        return qp[k]!=-1;
    }

    /**
     *
     * @return a minimal item
     */
    public Item min(){
        return keys[pq[1]];
    }

    /**
     *
     * @return a minimal item's index
     */
    public int minIndex(){
        return pq[1];
    }

    /**
     *
     * remove a minimal item and return its index
     */
    public int delMin(){
        int item = pq[1];
        exch(1, n--);
        sink(1);
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;
        return item;
    }

    /**
     * is the priority queue empty ?
     */
    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return n;
    }

    private void swim(int k){
        while (k>1 && greater(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    public void sink(int k){
        while(k<=n/2){
            int j = k*2;
            if (j<n && greater(j, j+1)) j++; //get the smaller one
            if (!greater(k,  j)) break; //more optimized than the commented code below
//            if (greater(k, j)) exch(k, j);
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j){
        int k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public void delete(int i){
        int index = qp[i];
        if (!contains(i)) throw new NoSuchElementException("");
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    private boolean greater(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public static void main(String[] args) {
        String[] strings = {"it", "was", "the", "best", "of", "times",
                "it", "was", "the", "worst"};
        IndexMinPQ<String> pq = new IndexMinPQ<>(strings.length);
        for(int i=0; i<strings.length; i++){
            pq.insert(i, strings[i]);
        }
        while(!pq.isEmpty()){
            int i = pq.delMin();
            System.out.println(i + " " + strings[i]);
        }
    }

}
