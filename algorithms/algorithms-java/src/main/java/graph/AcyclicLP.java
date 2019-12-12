package graph;

import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiagraph;

public class AcyclicLP {
    private double distTo[];
    private DirectedEdge edgeTo[];

    public AcyclicLP(EdgeWeightedDiagraph G, int s){
        distTo = new double[G.V()];
        for(int i=0; i<G.V(); i++){
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;
        edgeTo = new DirectedEdge[G.V()];

        Iterable<Integer> topOrder = new Topological(G).topOrder();
        for(int v: topOrder){
            reverseRelax(G, v);
        }
    }

   public double distTo(int v){
        return distTo[v];
   }

    private void reverseRelax(EdgeWeightedDiagraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

}
