package datastructure;

import java.util.Iterator;

public class Bag<T> implements  Iterable<T>{

    private Node<T> first;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<T>{
        private Node<T> current;
        ListIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            T item = this.current.item;
            this.current = this.current.next;
            return item;
        }
    }

    private class Node<T>{
        private Node(T item){
            this.item = item ;
        }
        T item;
        Node<T> next;
    }

    public void add(T o) {
        //new node becomes the new first
        Node<T> node = new Node(o);
        node.next = first;
        first = node;
        size ++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString(this);
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<Integer>();
        for(int i=0; i<100; i++){
            bag.add(i);
        }
        System.out.println(bag);
        System.out.println("size is: " + bag.size());
    }

}
