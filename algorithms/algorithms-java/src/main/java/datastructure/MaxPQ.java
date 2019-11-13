package datastructure;

import java.util.NoSuchElementException;

//TODO: add resize
public class MaxPQ <T extends Comparable<T>>{

    // a binary tree is heap-ordered if the key in each node >= 2 children
    //represented in level order in the array
    //children: 2i, 2i + 1
    //parent: i/2
    private T[] a;

    private int n;

    public MaxPQ(){
        a = (T[]) new Comparable[2];
        n = 0;
    }

    public void insert(T item){
        //how about array index outof bound ?
        //start from 1
        System.out.println("n: " + n + ": " + (a.length -1));
        if (n == a.length - 1) {
            resize(a.length*2); //do not use n*2, when n == 1 == a.length - 1, n*2 = 2, array is still size 2
        }

        a[++n] = item;
        swim(n);
    }

    public T delMax(){
        if (isEmpty()) throw new NoSuchElementException();
        //exchange keys with root
        exch(n, 1);
        T item = a[n];

        //remove
        a[n--] = null;

        //reheap
        sink(1);

//      my version:  if (n<a.length/4){, TODO: think about why?
        if ((n > 0) && (n == (a.length - 1) / 4)){
            resize(a.length/2);
        }

        return item;
    }

    private void resize(int capacity){
        T[] newA = (T[]) new Comparable[capacity];
        for(int i=1; i<=n; i++){
            newA[i] = a[i];
        }
        this.a = newA;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size(){
        return n;
    }

    private boolean less(int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //swim and sink to reheapify
    private void swim(int i) {
        //if parent less, exchange with parent
        while (i>1 && less(i/2, i)){
            exch(i, i/2);
            i = i/2;
        }
    }

    private void sink(int i) {
        //while has children
        while(i*2 <= n){ //why need = , size can be odd
            //if any child larger, exchange with the larger child
            int childIndex = i*2;
            //get the larger child
            if (childIndex<n /*forgot this part*/ && less(childIndex, childIndex+1)) childIndex++;

            //if no less than any child, stop
            if (!less(i, childIndex)) break;

            exch(i, childIndex);
            i = childIndex;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(a[i]);
            if (i<n) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        pq.insert("p");
        pq.insert("a");
        pq.insert("u");
        pq.insert("l");
        pq.insert("a");
        pq.insert("n");
        pq.insert("d");
        pq.insert("s");
        pq.insert("h");
        pq.insert("i");
        pq.insert("n");
        pq.insert("y");
        Stack<String> stack = new Stack<String>();

        while(!pq.isEmpty()) stack.push(pq.delMax());
//        System.out.println(pq.a.length);
        System.out.println(stack);

    }

}
