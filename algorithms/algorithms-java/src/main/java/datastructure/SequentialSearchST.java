package datastructure;

public class SequentialSearchST<K, V> {

    private Node root;

    private int n = 0;

    private class Node<K, V>{
        K key;
        V value;
        Node next;
        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public boolean contains(K k){
        return get(k) != null;
    }

    public void put(K k, V v){
        for(Node node = root; node!=null; node=node.next ){
            if (node.key.equals(k))  { node.value = v; return; }
        }
        root = new Node(k,v,root);
        n++;
    }

    public V get(K k){
        for(Node node = root; node!=null; node=node.next ){
            if (node.key.equals(k)) return (V)node.value;
        }
        return null;
    }

    public void delete(K k){
        root = delete(root,k);
    }

    private Node delete(Node node, K k) {
        if (node == null) return null;

        if (node.key.equals(k)) {
            n--;
            return node.next;
        }
        node.next = delete(node.next, k);
        return node;
    }

    public Iterable<K> keys(){
        Queue<K> q = new Queue<>();
        for(Node x = root; x!=null; x=x.next){
            q.enqueue((K)x.key);
        }
        return q;
    }

    public int size(){
        return n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(K k : keys()) {
            sb.append(k+ "->" + get(k));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SequentialSearchST<String, String> st = new SequentialSearchST<>();
        System.out.println(st.get("asdf"));

        st.put("e", "E");
        st.put("b", "B");
        st.put("c", "C");
        st.put("a", "A");
        st.put("f", "F");
        st.put("d", "D");

        System.out.println(st);

    }

}
