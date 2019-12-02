package graph;

import datastructure.Digraph;
import datastructure.Graph;
import datastructure.Stack;

import java.util.Iterator;

public class DepthFirstNonRecursive {

    private boolean marked[];
    private int count;
    private int edgeTo[];

    private Stack<Integer> topOrder = new Stack<>();

    public DepthFirstNonRecursive(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);

        Iterator<Integer>[] adjs = new Iterator[G.V()];
        for(int i=0; i<G.V(); i++){
            adjs[i] = G.adj(i).iterator();
        }

        while(!stack.isEmpty()){
            //the last one pushed on to the stack
            int v = stack.peek();
            //has adj vertex, visit adj vertex
            if(adjs[v].hasNext()){
                int w = adjs[v].next();
                if (!marked[w]) {
                    marked[w] = true;
                    stack.push(w);
                }
            }else{
                //no adj vertices, done
                int x = stack.pop();
                count ++ ;
            }

        }
    }

    public int count(){
        return this.count;
    }
    public boolean marked(int v){
        return marked[v];
    }
}
