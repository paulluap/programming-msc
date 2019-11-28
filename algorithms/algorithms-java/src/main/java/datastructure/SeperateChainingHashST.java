package datastructure;

public class SeperateChainingHashST<K, V> {

    private SequentialSearchST<K,V>[] chains;
    int n = 0;
    int m = 0;

    public SeperateChainingHashST(int c){
        m = c;
        chains = new SequentialSearchST[m];
        for(int i=0; i<m; i++) {
            chains[i] = new SequentialSearchST<>();
        }
    }

    public SeperateChainingHashST(){
        this(2);
    }

    private int hash(K k){
        //remove sign, mod m, returns i in [0, m-1]
        return (k.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int c){
        SeperateChainingHashST<K,V> newST = new SeperateChainingHashST<>(c);
        for(SequentialSearchST<K,V> chain: this.chains) {
            for (K k: chain.keys()) {
                newST.put(k, chain.get(k));
            }
        }
        this.chains = newST.chains;
        this.m = newST.m;
        this.n = newST.n;
    }

    public void put(K k, V v){
        int i = hash(k);
        if (!chains[i].contains(k)) n++;
        chains[i].put(k,v);

        //if average length of list >= 10, double table size
        if(n >= 10*m) resize(m*2);
    }

    public V get(K k){
        int i = hash(k);
        return chains[i].get(k);
    }

    public void delete(K k){
        int i = hash(k);
        if (chains[i].contains(k)) n--;
        chains[i].delete(k);
        //half table sie if avg len of list <= 2
        if(n <= 2*m) resize(m/2);
    }

    public Iterable<K> keys()  {
        Queue<K> q = new Queue<>();
        for(int i=0; i<m; i++){
            System.out.println("keys for chains[" + i+ "]: " + chains[i].keys());
            for(K k : chains[i].keys()){
                q.enqueue(k);
            }
        }
        return q;
    }

    public int size(){
        return n;
    }


    public static void main(String[] args) {
        SeperateChainingHashST<String, String> st = new SeperateChainingHashST<>();
        System.out.println(st.get("asdf"));

        st.put("e", "E");
        st.put("b", "B");
        st.put("c", "C");
        st.put("a", "A");
        st.put("f", "F");
        st.put("d", "D");
        st.delete("d");
        st.delete("c");
        st.delete("e");

        System.out.println("keys: " + st.keys() + " of size; " + st.size());


    }

}

