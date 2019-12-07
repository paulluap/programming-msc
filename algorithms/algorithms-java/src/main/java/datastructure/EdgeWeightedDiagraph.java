package datastructure;

public class EdgeWeightedDiagraph {
    private int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indgree;
    public EdgeWeightedDiagraph(int V){
        this.V = V;
        adj = new Bag[V];
        indgree = new int[V];
        for(int i=0; i<V; i++){
            adj[i] = new Bag<>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addEdge(DirectedEdge e){
        int v = e.from(), w = e.to();
        adj[v].add(e);
        indgree[w]++;
        E ++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public int outdegree(int v){
        return adj[v].size();
    }

    public int indgree(int v){
        return indgree[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> edges = new Bag<>();
        for(int v=0; v<V; v++){
            for(DirectedEdge e: adj(v)){
                edges.add(e);
            }
        }
        return edges;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
