package graph;

import datastructure.Graph;
import datastructure.Stack;
import sun.tools.jstat.Literal;

public class DepthFirstSearch {

    private boolean[] marked;
    private int n;
    private int s;
    private int[] edgeTo;

    //after the search, we need to answer queries : marked, count
    public DepthFirstSearch(Graph G, int s){
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s){
        marked[s] = true;
        n++;
        for(int w: G.adj(s)){
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G, w);
            }
        }
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> path = new Stack<>();
        if (!marked[v]) return path;
        for(int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    //is v conneted to s?
    public boolean marked(int v){
        return marked[v];
    }

    public int count(){
        return n;
    }

}
