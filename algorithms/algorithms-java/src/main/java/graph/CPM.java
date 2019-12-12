package graph;

import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiagraph;

/**
 * critical path method
 */
public class CPM {
    public static void main(String[] args) {
        final int N = 10;
        EdgeWeightedDiagraph G = new EdgeWeightedDiagraph(2*N + 2); //all v + s + t
        final int s = 2*N, t = 2*N+1;
        int[] jobweights = new int[]{41, 51, 50, 36, 38, 45, 21, 32, 32, 29};
        int[][] dependencies = new int[][]{
            {1, 7, 9},
            {2},
            {},
            {},
            {},
            {},
            {3, 8},
            {3, 8},
            {2},
            {4, 6},
        };

        for(int i=0; i<N; i++){
            //self start end
            G.addEdge(new DirectedEdge(i, N + i, jobweights[i]));
            //s to v
            G.addEdge(new DirectedEdge(s, i, 0.0));
            //v to end
            G.addEdge(new DirectedEdge(N+ i, t, 0.0));
            for(int j=0; j<dependencies[i].length; j++){
                G.addEdge(new DirectedEdge(i+N, dependencies[i][j], 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);
        for(int v=0; v<N; v++){
            System.out.println(v + " : " + lp.distTo(v));
        }

//        System.out.println(G);
    }
}
