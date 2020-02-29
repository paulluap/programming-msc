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
        //move flipColors here, so the algorithm is top down 2-3-4 tree.
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
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
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
/**
 * how to delete?
 *
 * 2-3 node,
 * allow 2 node and 3 node,
 * put, make a 4-node -> split 4-node
 *
 *
 * 2-3-4 node:
 * The insertion algorithm is based on doing transformation on the way down the path
 * to maintain the invariant that the current node is not a 4-node (so we are assured
 * that there is room to insert the node at the bottom) and transformation on the way
 * up the up the path to balance any 4-nodes that may have been created.
 *
 * The transformations on the way down are precisely the same transformations that we
 * used for splitting 4-nodes in 2-3 trees.
 * If the root is 4-node, we split it into three 2-nodes,
 * increasing the height of the tree by 1.
 *
 * On the way down the tree,
 * if we encounter a 4-node with a 2-node as parent, we split the 4-node into two 2-nodes
 * and pass the middle key to the parent, making it a 3-node.
 * If we encounter a 4-node with a 3-node as parent, we split the 4-node into two 2-nodes
 * and pass the middle key to the parent, making it a 4-node.
 *
 *
 * Represent 4-nodes: 3 2-node, with both left and right links red.
 * split 4 nodes on the way down the tree with color flips.
 * balance 4-nodes on the way up the tree with rotations, as for insertions
 *
 */

