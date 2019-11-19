package datastructure;

import java.util.NoSuchElementException;

public class BST <K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;

        Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return "(" + key + ":" + value + ")";
        }
    }

    private Node root;

    public void put(K k, V v) {
        this.root = put(root, k, v);
    }

    public Node put(Node node, K k, V v) {
        //either return the passed in node, or create and return a new node is passed in node is null
        if (node == null) return new Node(k, v, 1); //insert new node

        int cmp = k.compareTo(node.key);

        if      (cmp < 0) node.left  = put(node.left, k, v);
        else if (cmp > 0) node.right = put(node.right, k, v);
        else              node.value = v; //override

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public V get(K k) {
        return get(root, k);
    }

    private V get(Node node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) return get(node.left, k);
        else if (cmp > 0) return get(node.right, k);
        return node.value;
    }

    /**
     * @return the min key
     */
    public K min() {
        if (root == null) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }


    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(final Node node) {
        if (node.left == null) return node.right; //not null, but node.right !
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K max(){
        return max(root);
    }

    private K max(Node node){
        if (node == null) return null;
        if (node.right == null) return node.key;
        return max(node.right);
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    public void showTree(){
        Queue<Node> q = new Queue<Node>();
        if(root == null) return;
        q.enqueue(root);
        while(!q.isEmpty()){
            Node node = q.dequeue();
            System.out.println(node + " -> (" + node.left + ", " + node.right + ")");
            if (node.left != null) q.enqueue(node.left);
            if (node.right != null) q.enqueue(node.right);
        }
    }

    public void delete(K k){
        root = delete(root, k);
    }

    private Node delete(final Node node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if      (cmp < 0)   node.left = delete(node.left, k);
        else if (cmp > 0)   node.right = delete(node.right, k);
        else {
            if(node.right == null) return node.left;
            if(node.left == null)  return node.right;
            //tricky here
            Node n = min(node.right);
            //must update right first
            n.right = deleteMin(node.right);
            n.left = node.left;
            n.size = size(n.left) + size(n.right) + 1;
            return n;
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<K> keys(){
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }


    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        keys(root, q, lo, hi);
        return q;
    }

    public void keys(Node node, Queue<K> q, K lo, K hi) {
        if (node == null) return;

        int loLess = lo.compareTo(node.key);
        int hiLarger = hi.compareTo(node.key);

        //key not less than low, maybe elements in left subtree
        if (loLess < 0) keys(node.left, q, lo, hi);
        if (loLess <= 0 && hiLarger >= 0) q.enqueue(node.key);
        //key not larger than hi, maybe elements in right subtree
        if (hiLarger > 0) keys(node.right, q, lo, hi);
    }

    public int size(Node node){
        if (node == null) return 0;
        return node.size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size(root);
    }


    public static void main(String[] args) {
        BST<String, String> bst = new BST<>();
        bst.put("e", "E");
        bst.put("b", "B");
        bst.put("c", "C");
        bst.put("a", "A");
        bst.put("f", "F");
        bst.put("d", "D");
        System.out.println(bst.keys("c", "f"));


    }
}
