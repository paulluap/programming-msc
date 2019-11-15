package datastructure;

import java.util.NoSuchElementException;

//balanced binary search tree
//put get
public class BST <K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 0;
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
        if (node == null) return new Node(k, v);

        int cmp = k.compareTo(node.key);

        if (cmp < 0) node.left = put(node.left, k, v);
        else if (cmp > 0) node.right = put(node.right, k, v);
        else node.value = v;

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

    private Node deleteMin(Node node) {
        System.out.println("delete min: " + node + ", "  + node.left + " - " + node.right);
        if (node.left == null) return node.right; //not null, but node.right !
        node.left = deleteMin(node.left);
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

    private Node delete(Node node, K k) {
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
            return n;
        }
        return node;
    }

    public static void main(String[] args) {
        BST<String, String> bst = new BST<>();
        bst.put("e", "E");
        bst.put("b", "B");
        bst.put("c", "C");
        bst.put("a", "A");
        bst.put("f", "F");
        bst.put("d", "D");
//        bst.deleteMin();
//        bst.deleteMax();
//        bst.delete("a");
        bst.delete("e");
        bst.showTree();;


        System.out.println(bst.get("f"));
        System.out.println(bst.get("d"));
        System.out.println(bst.get("c"));
        System.out.println(bst.get("e"));
        System.out.println(bst.get("a"));
        System.out.println(bst.get("b"));

        System.out.println("min: " + bst.min() + ", max: " + bst.max());

    }
}
