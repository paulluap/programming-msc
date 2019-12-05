package datastructure;

public class EdgeWeightedGraph {
    private int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        adj = new Bag[V];
        for(int i=0; i<V; i++){
            adj[i] = new Bag();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public int degree(int v){
        return adj[v].size();
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public Iterable<Edge> edges(){
        Bag<Edge> list = new Bag<Edge>();
        for(int v=0; v<V; v++){
            for(Edge e : adj(v)){
                int selfLoop = 0;
                //ensures only add one
                if (e.other(v) > v) {
                    list.add(e);
                }else if(e.other(v)==v) {
                    //self loop are consecutive
                    if (selfLoop%2 == 0) list.add(e);
                    selfLoop++;
                }
            }
        }
        return list;
    }

}