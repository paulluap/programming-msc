package graph;

import datastructure.DirectedEdge;
import datastructure.EdgeWeightedDiagraph;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

public class DirectedGraphTest {
    public EdgeWeightedDiagraph createDigraph(){
        EdgeWeightedDiagraph g = new EdgeWeightedDiagraph(8);
        g.addEdge(new DirectedEdge(4, 5, 0.35));
        g.addEdge(new DirectedEdge(5, 4, 0.35));
        g.addEdge(new DirectedEdge(4, 7, 0.37));
        g.addEdge(new DirectedEdge(5, 7, 0.28));
        g.addEdge(new DirectedEdge(7, 5, 0.28));
        g.addEdge(new DirectedEdge(5, 1, 0.32));
        g.addEdge(new DirectedEdge(0, 4, 0.38));
        g.addEdge(new DirectedEdge(0, 2, 0.26));
        g.addEdge(new DirectedEdge(7, 3, 0.39));
        g.addEdge(new DirectedEdge(1, 3, 0.29));
        g.addEdge(new DirectedEdge(2, 7, 0.34));
        g.addEdge(new DirectedEdge(6, 2, 0.40));
        g.addEdge(new DirectedEdge(3, 6, 0.52));
        g.addEdge(new DirectedEdge(6, 0, 0.58));
        g.addEdge(new DirectedEdge(6, 4, 0.93));
        return g;
    }


    @Test
    public void testDirkstrasSP(){
        EdgeWeightedDiagraph g = this.createDigraph();
        /**
         * 0->2 0.26 2->7 0.34 7->3 0.39 3->6 0.52
         */
        DijkstrasSP sp = new DijkstrasSP(g, 0);
        Assert.assertEquals("[0->2(0.26),2->7(0.34),7->3(0.39),3->6(0.52)]", sp.pathTo(6).toString());
    }

}
