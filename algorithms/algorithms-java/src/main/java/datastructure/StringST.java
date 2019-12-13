package datastructure;

public class StringST<T> {
    private Node root =  new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[256];
    }

    public boolean contains(String key){
        return get(key)!=null;
    }

    public T get(String key){
        Node n = get(root, key, 0);
        if (n==null) return null;
        return (T)n.value;
    }

    private Node get(Node x, String key, int d){
        if (x==null) return null;
        if (d == key.length())  return x;
        Node next = x.next[key.charAt(d)];
        return get(next, key, d+1);
    }

    public void put(String key, T value){
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Object value, int d) {
        if (x == null) x = new Node();
        //unlike tinery tries, this has d levels instead of d - 1
        if (key.length() == d) {
            x.value = value;
            return x;
        }
        char c= key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d+1);
        return x;
    }

    public static void main(String[] args) {
        StringST<Integer> st = new StringST<>();
        st.put("Paul", 2);
        st.put("Shiny", 3);
        System.out.println(st.get("Paul"));
        System.out.println(st.get("Shiny"));
    }
}
