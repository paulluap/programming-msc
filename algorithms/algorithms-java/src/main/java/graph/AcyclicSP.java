package graph;

import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiagraph;
import datastructure.Stack;

public class AcyclicSP {

    private double distTo[];
    private DirectedEdge edgeTo[];

    public AcyclicSP(EdgeWeightedDiagraph G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int i=0; i<G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        for(int v : new Topological(G).topOrder()){
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDiagraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v]+ e.weight()){
                distTo[w] = distTo[v]+ e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> result = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
            result.push(e);
        }
        return result;
    }
}
