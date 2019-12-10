package graph;

import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiagraph;
import datastructure.IndexMinPQ;
import datastructure.Stack;

import java.nio.charset.StandardCharsets;

//shortest path algorithm

/**
 * 1. initialize dist[s] to 0 and all other distTo[] entries to positive infinity
 * 2. relax and add to the tree a non-tree vertex with the lowest distTo value
 *    continue until all verteces are on the tree or no non-tree vertex has a finite distTo[] value.
 */
public class DijkstrasSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstrasSP(EdgeWeightedDiagraph G, int s) {
        pq = new IndexMinPQ<>(G.V());
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for(int i=0; i<distTo.length; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        //key is vertex (to), value is distTo
        pq.insert(s, 0.0);
        while(!pq.isEmpty()){
            relax(G, pq.delMin());
        }
    }

    /**
     * length of shortest path from s to v
     * @param v
     * @return
     */
    public double distTo(int v){
        return distTo[v];
    }

    /**
     * path from s to v
     * @param v
     * @return
     */
    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> result = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
            result.push(e);
        }
        return result;
    }

    private void relax(EdgeWeightedDiagraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))  pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    private void relax(DirectedEdge e){
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public static void main(String[] args) {

    }

}
