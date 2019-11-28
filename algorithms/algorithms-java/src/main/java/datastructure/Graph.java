package datastructure;

public class Graph {

    //vertex-indexed array, edge is implicit
    private Bag<Integer> adj[];
    //number of vertices
    private int V;
    //number of edges
    private int E;

    public Graph(int v){
        V = v;
        adj = new Bag[V];
        for(int i=0; i<V; i++) adj[i] = new Bag<>();
    }

    //returns vertices adjacent to a given vertex
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    //how many adjacent vertices ?
    public int degree(int v){
        return adj[v].size();
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * @return the number of vertex
     */
    public int V(){
        return V;
    }

    /**
     * @return the number of edges
     */
    public int E(){
        return E;
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

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);
        System.out.println(graph);
    }
}
