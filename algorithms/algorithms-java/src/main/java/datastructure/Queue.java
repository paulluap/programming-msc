package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements  Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(this.first);
    }

    private class Node<T> {
        T item;
        Node<T> next;
        Node(T item) { this.item = item;}
    }

    private class ListIterator implements Iterator<T>{

        private Node<T> current;

        ListIterator(Node<T> first ) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }


    public void enqueue(T item)  {
        //unlike stack or bag, for queue we grow from the last
        Node<T> node = new Node(item);
        if (isEmpty()) { last = node; first = last; }
        else           { last.next = node; last = node;}
        size ++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = first.item;
        first  = first.next;
        size --;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public boolean isEmpty()  {
        return this.first == null;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString(this);
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for(int i=0; i<50; i++) {
            q.enqueue(i);
        }
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q);
    }
}
