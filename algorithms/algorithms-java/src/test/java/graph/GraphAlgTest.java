package graph;

import datastructure.BreadthFirstDirectedPaths;
import datastructure.Digraph;
import datastructure.Graph;
import org.junit.Assert;
import org.junit.Test;

public class GraphAlgTest {

    public Graph makeGraph(){
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
        return graph;
    }

    @Test
    public void testDfs(){
        Graph graph = makeGraph();
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);
        Assert.assertEquals(7, search.count());
        Assert.assertEquals(true, search.marked(4));
        Assert.assertEquals(true, search.marked(1));
        Assert.assertEquals(false, search.marked(9));
        Assert.assertEquals(false, search.marked(8));
        System.out.println(search.pathTo(3));
    }
    @Test
    public void testBfs(){
        Graph graph = makeGraph();
        BreadthFirstSearch search = new BreadthFirstSearch(graph, 0);
        Assert.assertEquals(7, search.count());
        Assert.assertEquals(true, search.marked(4));
        Assert.assertEquals(true, search.marked(1));
        Assert.assertEquals(false, search.marked(9));
        Assert.assertEquals(false, search.marked(8));
        System.out.println(search.pathTo(3));
    }

    @Test
    public void testDigraphDFS(){
        Digraph g = makeDigraph();
        Assert.assertEquals(2, g.indegree(0));
        DepthFirstDirectedPaths q = new DepthFirstDirectedPaths(g, 0);
        Assert.assertEquals("[0,5,4,3,2]", q.pathTo(2).toString());
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(g, 0);
        Assert.assertEquals("[0,5,4,2]", bfs.pathTo(2).toString());
    }

    @Test
    public void toplogical(){
        Digraph dag =tinyDag();
        System.out.println(dag);
        Topological top2 = new Topological(dag);
        Assert.assertEquals("[8,7,2,3,0,6,9,10,11,12,1,5,4]", top2.topOrder().toString());

    }

    public Digraph tinyDag(){
        Digraph g = new Digraph(13);

        g.addEdge(2,3);
        g.addEdge(0,6);
        g.addEdge(0,1);
        g.addEdge(2,0);
        g.addEdge(11,12);
        g.addEdge(9,12);
        g.addEdge(9,10);
        g.addEdge(9,11);
        g.addEdge(3,5);
        g.addEdge(8,7);
        g.addEdge(5,4);
        g.addEdge(0,5);
        g.addEdge(6,4);
        g.addEdge(6,9);
        g.addEdge(7,6);
        return g;

    }

    public Digraph makeDigraph(){
        Digraph g = new Digraph(13);
        g.addEdge(0, 5);
        g.addEdge(0, 1);
        g.addEdge(2, 0);
        g.addEdge(6, 0);

        g.addEdge(2,3);
        g.addEdge(3, 2);

        g.addEdge(4, 2);
        g.addEdge(4, 3);

        g.addEdge(3,5);

        g.addEdge(5, 4);

        g.addEdge(6, 4);
        g.addEdge(6, 9);
        g.addEdge(7,6);

        g.addEdge(9, 11);
        g.addEdge(9, 10);
        g.addEdge(11, 4);
        g.addEdge(11, 12);
        g.addEdge(12, 9);
        g.addEdge(10, 12);
        g.addEdge(7,8);
        g.addEdge(8,7);
        g.addEdge(8, 9);
        return g;
    }
}
