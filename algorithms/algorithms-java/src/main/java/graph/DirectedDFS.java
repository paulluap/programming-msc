package graph;

import datastructure.Digraph;

public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph G, Iterable<Integer> s){
        this.marked = new boolean[G.V()];
        for(int v: s){
            if (!marked[v]) dfs(G, v);
        }

    }

    public DirectedDFS(Digraph G, int s){
        this.marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int s) {
        marked[s] = true;
        for(int v : G.adj(s)){
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

}
