package datastructure;

public class TST<T> {
    private Node root;
    private class Node{
        private char c;
        private T value;
        private Node mid;
        private Node left;
        private Node right;
    }

    public T get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.value ;
    }

    private Node get(Node x, String key, int d){
        if (x == null) return null;
        char c = key.charAt(d);
        if (c<x.c)                  return get(x.left, key, d);
        else if(c>x.c)              return get(x.right, key, d);
        else if(d<key.length() - 1) return get(x.mid, key, d+1);
        else                        return x;
    }

    public void put(String key, T value){
        this.root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, T value , int d){
        char c = key.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }

        if (c < x.c)                x.left = put(x.left, key, value, d);
        else if (c > x.c)           x.right = put(x.right, key, value, d);
        //eq
        else if(d < key.length()-1) x.mid = put(x.mid, key, value, d+1);
        //eq and last c
        else                        x.value = value;
        return x;

    }

    public static void main(String[] args) {
        TST<Integer> st = new TST<>();
        System.out.println(st.get("Paul"));
        st.put("Paux", 3);
        st.put("Paul", 2);
        System.out.println(st.get("Paux"));
        System.out.println(st.get("Paul"));
        st.put("Shiny", 3);
        System.out.println(st.get("Shiny"));

    }
}
