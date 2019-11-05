package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements  Iterable<T>{

    private final static int DEFAULT_INITIAL_CAPACITY = 2;

    private T[] a;

    private int n = 0;

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    //had not used reverse
    private class ReverseArrayIterator implements Iterator<T>{

        private int index = n-1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            return a[index--];
        }
    }

    public ResizingArrayStack() {
        this.a = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T item){
        if (a.length == n) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public T pop(){
        if (isEmpty()) throw new NoSuchElementException();
        //I made a mistack: a[n--]
        //still, has loitering problems
        //T item = a[--n];

        //: below is from the alg book
        T item = a[n-1];
        a[n-1] = null; //to avoid loitering
        n--;

        //previously implemneted as
        //if (n < a.length / 2){ resize(a.length/2); }

        //below from the book
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    private void resize(int capacity) {
        T[] newA = (T[]) new Object[capacity];
        for(int i=0; i<n; i++){
            newA[i] = a[i];
        }
        a = newA;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString(this);
    }

    public static void main(String[] args) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>();
        for(int i=0; i<50; i++){
            stack.push(i);
        }

        System.out.println(stack);
        System.out.println(stack.size());
        for(int i=0; i<48; i++) {
            System.out.println(stack.pop());
        }
        System.out.println(stack);
        System.out.println(stack.size());
    }
}
