package graph;

import datastructure.Digraph;
import datastructure.Stack;

public class DepthFirstDirectedPaths {

    private boolean marked[];
    private int s;

    //which vertex takes us to which
    private int edgeTo[];

    public DepthFirstDirectedPaths(Digraph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        this.dfs(G, s);
    }

    private void dfs(Digraph G, int s){
        //mark as visited
        marked[s] = true;
        for(int v : G.adj(s)){
            //dfs all unmarked children
            if (!marked[v]){
                edgeTo[v] = s;
                dfs(G, v);
            }
        }
    }

    public Iterable<Integer> pathTo(int v){
        Stack stack = new Stack<Integer>();
        if (!marked[v]) return stack;
        for(int x = v; x != s; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }


}
