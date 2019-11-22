package datastructure;

import javax.swing.tree.AbstractLayoutCache;

public class RedBlackBST<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Node left;
        private Node right;
        private K key;
        private V value;
        private int size;

        //each node is pointed to by one link, encode color in node
        /**
         * true: red
         * false: black
         * null links are black
         */
        private boolean color;

        public Node(K key, V value, boolean color, int size){
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public void put(K k, V v) {
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node node, K k, V v) {
        if (node == null) return new Node(k, v, RED, 1);
        int cmp = k.compareTo(node.key);
        if (cmp < 0)      node.left = put(node.left, k, v);
        else if (cmp > 0) node.right = put(node.right, k, v);
        else              node.value = v;

        //fix RB tree
        if(isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left))  node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right))      flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public V get(K k) {
        return get(root, k);
    }

    private V get(Node node, K k){
        while(node!=null){
            int cmp = k.compareTo(node.key);
            if (cmp < 0)      node = node.left;
            else if (cmp > 0) node = node.right;
            else              return node.value;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        //bring the larger key up to the root
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        //inherit size + color
        x.size = h.size;
        x.color = h.color;
        //maintain size invariant
        h.size = size(h.right) + size(h.left) + 1;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        //bring the smaller key up to the root
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        //inherit size + color
        x.size = h.size;
        x.color = h.color;
        //maintain size invariant
        h.size = size(h.right) + size(h.left) + 1;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private int size(Node node){
        if (node == null) return 0;
        return node.size;
    }

    private boolean isRed(Node x){
        //default black link
        if (x == null) return false;
        return x.color == RED;
    }

    public int size() {
        return size(root);
    }

    public void showTree(){
        this.showTree(root, 0);
    }

    private void showTree(Node node, int space) {
        if (node == null) return;
        showTree(node.right, space+1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<space; i++) {
            sb.append("-");
        }
        System.out.println(sb.append(node.key));
        showTree(node.left, space+1);
    }

}
