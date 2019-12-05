package graph;

import cases.UF;
import datastructure.Edge;
import datastructure.EdgeWeightedGraph;
import datastructure.MinPQ;
import datastructure.Queue;

public class KruskalMST {
    /**
     * consider edges in ascending order of weight
     * add next edge to tree T unless doing so would create a cycle
     */

    MinPQ<Edge> minPQ = new MinPQ<>();
    UF uf;
    Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph G){
        uf = new UF(G.V());
        for(Edge e : G.edges()){
            minPQ.insert(e);
        }
        while(!minPQ.isEmpty() && mst.size()<G.V() - 1){
            Edge minE = minPQ.delMin();
            int v = minE.either(), w = minE.other(v);
            if (!uf.connected(v, w)){
                mst.enqueue(minE);
                uf.union(v, w);
            }
        }
    }

    public Iterable<Edge> mst(){
        return mst;
    }

}
