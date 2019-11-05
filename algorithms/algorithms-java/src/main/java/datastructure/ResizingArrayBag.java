package datastructure;

import java.util.Collection;
import java.util.Iterator;

public class ResizingArrayBag<T> implements Iterable<T>{

    private final static int DEFAULT_INITIAL_CAPACITY = 2;

    private T[] a;

    //private int size = 0;
    //private int current = 0;
    //instead of keep both the size field and the current index field, kwe can use one n to represent size
    private int n = 0;

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public T next() {
            return a[index++];
        }
    }


    public ResizingArrayBag(int capacity) {
        this.a = (T[]) new Object[capacity];
    }

    public ResizingArrayBag(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public void add(T item) {
        if (a.length == n){
            resize(2*a.length);
        }
        a[n++] = item;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size() {
        return this.n;
    }

    private void resize(int capacity){
        //double the length
        T[] newA = (T[]) new Object[capacity];
        //copy to new Array
        for(int i=0; i<n; i++){
            newA[i] = a[i];
        }
        this.a = newA;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString((this));
    }

    public static void main(String[] args) {
        ResizingArrayBag<Integer> bag = new ResizingArrayBag<Integer>(2);
        System.out.println(bag);
        for(int i=0; i<100; i++){
            bag.add(i);
        }
        System.out.println(bag);
    }

}
