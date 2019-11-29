package graph;

import datastructure.Graph;

//connected components
public class CC {

    private boolean marked[];

    //vertex indexed array of which component (id) it belongs to
    private int v2c[];
    private int cid;
    //size of components
    private int size[];

    public CC(Graph graph){
        marked = new boolean[graph.V()];
        v2c = new int[graph.V()];
        size = new int[graph.V()];
        for(int v=0; v< graph.V(); v++){
            if (!marked[v]) {
                dfs(graph, v);
                cid ++;
            }
        }
    }

    private void dfs(Graph graph, int v){
        marked[v] = true;
        v2c[v] = cid;
        size[cid]++;
        for(int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph, w);
            }
        }
    }

    public boolean isConnected(int v, int w){
        //if belongs to the same component
        return v2c[v] == v2c[w];
    }

}