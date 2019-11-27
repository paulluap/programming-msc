package datastructure;

public class SeperateChainingHashST<K, V> {

    private SequentialSearchST<K,V>[] chains;
    int n = 0;
    int m = 0;

    public SeperateChainingHashST(int c){
        m = c;
        chains = new SequentialSearchST[m];
    }

    public SeperateChainingHashST(){
        m = 2;
        chains = new SequentialSearchST[m];
    }

    private int hash(K k){
        //remove sign, mod m, returns i in [0, m-1]
        return (k.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int c){
        SeperateChainingHashST<K,V> newST = new SeperateChainingHashST<>();
        for(SequentialSearchST<K,V> chain: chains) {
            if (chain == null) continue;
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
        if (chains[i]==null){
            chains[i] = new SequentialSearchST<>();
        }
        if (!chains[i].contains(k)) n++;
        chains[i].put(k,v);
    }

    public V get(K k){
        int i = hash(k);
        if (chains[i]== null) return null;
        return chains[i].get(k);
    }

    public void delete(K k){
        int i = hash(k);
        if (chains[i] == null) return;

        if (chains[i].contains(k)) n--;
        chains[i].delete(k);
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

        System.out.println(st.get("d"));
        st.delete("d");
        System.out.println(st.get("d"));

    }

}

