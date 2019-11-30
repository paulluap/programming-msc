package graph;

import datastructure.Digraph;
import datastructure.Stack;

public class Topological {

    private boolean marked[];
    //reverse post order
    private Stack<Integer> toporder = new Stack<>();
    //finish me
    public Topological(Digraph G){
        marked = new boolean[G.V()];
        for(int v=0; v<G.V(); v++){
            if (!marked[v]) dfs(G, v);
        }
    }

    //run dfs first
    //return vertices in reverse postorder
    private void dfs(Digraph G, int s){
        marked[s] = true;
        for(int v: G.adj(s)){
            if (!marked[v]){
                dfs(G, v);
            }
        }
        toporder.push(s);
        System.out.println("post order : " + toporder);
    }

    public Iterable<Integer> topOrder(){
        return toporder;
    }
}
