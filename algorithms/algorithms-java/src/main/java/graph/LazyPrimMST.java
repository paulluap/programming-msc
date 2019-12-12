package graph;

import datastructure.Edge;
import datastructure.EdgeWeightedGraph;
import datastructure.MinPQ;
import datastructure.Queue;

public class LazyPrimMST {

    private MinPQ<Edge> minPQ;
    private Queue<Edge> mst;
    private boolean marked[];

    public LazyPrimMST(EdgeWeightedGraph G){
        this.minPQ = new MinPQ<>();
        mst = new Queue<>();
        marked = new boolean[G.V()];
        prim(G, 0);
    }

    public void prim(EdgeWeightedGraph G, int s){
        //add all incident edges to the minPQ
        //find a edge from tree vertex to non-tree vertex
        scan(G, s);
        while(!minPQ.isEmpty()){
            Edge minE = minPQ.delMin();
            int v = minE.either(), w = minE.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(minE);
            if (!marked[v]) scan(G, v); //mark and scan
            if (!marked[w]) scan(G, w); //mark and scan
        }
    }

    private void scan(EdgeWeightedGraph G, int v){
        marked[v]=true;
        for(Edge e : G.adj(v)){
            if (!marked[e.other(v)]) minPQ.insert(e);
        }
    }

    public Iterable<Edge> mst() {
        return mst;
    }

}
