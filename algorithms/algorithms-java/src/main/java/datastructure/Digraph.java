package datastructure;

public class Digraph {
    //number of vertices
    int V;
    //number of edges
    int E;
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V){
        this.V = V;
        adj = new Bag[V];
        indegree = new int[V];
        for(int i=0; i<V; i++){
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public int outdegree(int v){
        return adj[v].size();
    }

    public int indegree(int v){
        return indegree[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int v=0; v<V; v++){
            sb.append(v).append(": ");
            for(int w : adj[v]){
                sb.append(w).append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}