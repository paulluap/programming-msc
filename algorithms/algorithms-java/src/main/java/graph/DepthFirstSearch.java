package graph;

import datastructure.Graph;

public class DepthFirstSearch {

    private boolean marked[];
    private int n;


    //after the search, we need to answer queries : marked, count
    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);

    }

    private void dfs(Graph G, int s){
        System.out.println("visit " + s);
        marked[s] = true;
        n++;
        for(int w: G.adj(s)){
            if (!marked[w]) dfs(G, w);
        }
    }

    //is v conneted to s?
    public boolean marked(int v){
        return marked[v];
    }

    public int count(){
        return n;
    }


}
