package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<T> implements  Iterable<T>{

    private T[] a;

    private int start = 0, end = 0, n =0;

    public ResizingArrayQueue(){
        a = (T[]) new Object[2];
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        private int i=0;

        @Override
        public boolean hasNext() {
            return i<n;
        }

        @Override
        public T next() {
            T item =  a[(start+i)%a.length];
            i ++;
            return item;
        }
    }

    public void enqueue(T item){
        if (n ==a.length){
            resize(a.length*2);
        }
        a[end] = item;
        //increment end, may wrap around
        end = (end+1) % a.length;
        n++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = a[start];
        a[start] = null;
        n--;
        start = (start + 1) % a.length; //as if start++

        //had forgotten size() > 0
        if (size()>0 && size() < a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString(this);
    }

    private void resize(int capacity) {
        T[] newA = (T[]) new Object[capacity];

        for(int i=0; i<n; i++){
            newA[i] = a[(start+i)%a.length];
        }

        this.a = newA;
        start = 0;
        end = n;
    }

    public static void main(String[] args) {
        ResizingArrayQueue<Integer> q = new ResizingArrayQueue<Integer>();
        for(int i=0; i<50; i++) {
            q.enqueue(i);
        }
        System.out.println(q);
        System.out.println(q.dequeue());
        System.out.println(q);
        System.out.println(q.dequeue());
        System.out.println(q);
        for(int i=0; i<5; i++) {
            q.enqueue(i);
        }
        System.out.println(q.dequeue());
        System.out.println(q);
        System.out.println(q.dequeue());
        System.out.println(q);
        for(int i=0; i<5; i++) {
            q.enqueue(i);
        }
        System.out.println(q.dequeue());
        for(int i=0; i<5; i++) {
            q.enqueue(i);
        }
        System.out.println(q);
        System.out.println(q.size());
    }

}
