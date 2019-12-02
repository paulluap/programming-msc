package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    private Node<T> first;

    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(first);
    }

    private class Node<T> {
        T item;
        Node<T> next;
        public Node(T item) { this.item = item; }
    }

    private class ListIterator implements  Iterator<T>{
        Node<T> current;

        ListIterator(Node<T> first) {
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


    public void push(T item) {
        Node<T> node = new Node(item);
        node.next = first;
        first = node;
        size ++;
    }

    public T pop() {
        if (first == null) throw new NoSuchElementException();
        T item = first.item;
        first = first.next;
        size --;
        return item;
    }

    public T peek(){
        if (isEmpty()) return null;
        return first.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        return CollectionUtils.toString(this);
    }

    int size(){
        return this.size;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<100; i++){
            stack.push(i);
        }
        for(int i=0; i<101; i++) {
            System.out.println(stack.pop());
        }
    }

}
