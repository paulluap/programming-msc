package datastructure;

public class LinearProbingHashST<K,V> {

    private int n;
    private int m;

    private Pair<K,V>[] a;

    public LinearProbingHashST(){
        this(2);
    }

    public LinearProbingHashST(int c){
        m = c;
        a = new Pair[m];
    }

    private class Pair<K, V>{
        private K key;
        private V value;
        public Pair(K k, V v){
            this.key = k;
            this.value = v;
        }
    }

    private void resize(int c){
        System.out.println("resizing: " + c);
        LinearProbingHashST<K,V> newST = new LinearProbingHashST<>(c);
        for(K k: keys()){
            newST.put(k, this.get(k));
        }
        this.n = newST.n;
        this.m = newST.m;
        this.a = newST.a;
    }

    private int hash(K k){
        return (k.hashCode() & 0x7fffffff) % m;
    }

    public void put(K k, V v){
        int i = hash(k);

        //half full resize
        if (n>m*0.5) resize(m*2);

        //cannot be full, so must have some space
        while(a[i] != null){
            if (a[i].key.equals(k)) {
                a[i].value = v;
                break;
            }
            i = (i+1)%m;
        }
        //found empty slot
        if (a[i]==null){
            a[i] =  new Pair(k, v);
            n++;
        }
    }

    public V get(K k) {
        int i = hash(k);
        while(a[i] != null){
            if(a[i].key.equals(k)) return (V) a[i].value;
            i = (i+1)%m;
        }
        return null;
    }

    public void delete(K k){
        int i = hash(k);
        while(a[i] != null){
            if (a[i].key.equals(k)) {
                a[i]=null;
                rehashFrom((i+1)%m);
                n--;
                //half the size if it's 12.5% full or less
                if (n>0 && n<m*0.125) resize(m/2);
                return;
            }
            i = (i+1)%m;
        }
    }

    private void rehashFrom(int i){
        while(a[i]!=null){
            Pair<K, V> kv = a[i];
            a[i] = null;
            n --;
            put(kv.key,kv.value);

            i = (i+1) % m;
        }
    }

    public int size(){
        return n;
    }

    public Iterable<K> keys(){
        Queue<K> q = new Queue<>();
        for(int i=0; i<m; i++){
            if (a[i]==null) continue;
            q.enqueue(a[i].key);
        }
        return q;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, String> st = new LinearProbingHashST<>();
        System.out.println(st.get("asdf"));

        st.put("e", "E");
        st.put("b", "B");
        st.put("c", "C");
        st.put("a", "A");
        st.put("f", "F");
        st.put("d", "D");
//        st.delete("d");
//        st.delete("a");
//        st.delete("b");
//        st.delete("c");
//        st.delete("d");
//        st.delete("e");
//        st.delete("f");
//        System.out.println(st.get("c"));

        System.out.println(st.get("f"));

        System.out.println("keys: " + st.keys() + " of size; " + st.size());
    }


}
