package datastructure;


public class MinPQ <T extends Comparable<T>>{

    //heap order for minPQ, key in node <= 2 children
    //starting from 1, for k, parent: k/2, children : 2k, 2k+1
    private T[] heap;
    //size
    private int n;

    public MinPQ(){
        //TODO new Object[initial_capacity + 1]
        heap = (T[]) new Comparable[2];
    }

    public void insert(T v){
        //TODO resize
        if (n == heap.length - 1) resize(2 * heap.length);
        n = n+1;
        heap[n] = v;
        swim(n);
    }

    public T delMin(){
        T min = heap[1];
        exch(1, n--);
        sink(1);
        heap[n+1] = null;
//        if (n < heap.length / 2) resize(heap.length/2);
        if ((n > 0) && (n == (heap.length - 1) / 4)) resize(heap.length / 2);
        return min;
    }

    public int size(){
        return n;
    }

    public void swim(int k){
        while(k>1 && greater(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }
    }

    public void showHeap(){
        showHeap(1, 0);
    }

    private void showHeap(int i, int ident){
        if (i>=n) return;
        //root => left, right
        showHeap(i*2+1, ident + 1);
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<ident; k++) sb.append(".");
        sb.append(heap[i]);
        System.out.println(sb);
        showHeap( i*2, ident + 1);
    }

    private void sink(int k){
        while(2*k<=n){
            int j = k*2;
            if (j<n /*when last child is single*/&& greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }

    }

    private boolean greater(int i, int j){
        return heap[i].compareTo(heap[j]) > 0;
    }

    private void exch(int i, int j){
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize(int capacity){
        T[] newHeap = (T[]) new Comparable[capacity];
        for(int i=1; i<=n; i++){
            newHeap[i] = heap[i];
        }
        this.heap = newHeap;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

}
