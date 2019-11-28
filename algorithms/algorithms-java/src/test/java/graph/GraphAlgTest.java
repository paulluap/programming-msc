package graph;

import datastructure.Graph;
import org.junit.Assert;
import org.junit.Test;

public class GraphAlgTest {
    @Test
    public void testDfs(){
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
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);
        Assert.assertEquals(7, search.count());
        Assert.assertEquals(true, search.marked(4));
        Assert.assertEquals(true, search.marked(1));
        Assert.assertEquals(false, search.marked(9));
        Assert.assertEquals(false, search.marked(8));


//        System.out.println(dfs);

    }
}
