package graph;

import datastructure.Graph;
import datastructure.Queue;
import datastructure.Stack;

public class BreadthFirstSearch {

    private final int s;
    private boolean marked[];
    private int n;
    private int edgeTo[];
    //TODO: distTO and why ?

    public BreadthFirstSearch(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s){

        Queue<Integer> q = new Queue<>();
        marked[s]=true;
        n++;
        q.enqueue(s);

        while(!q.isEmpty()){
            int v = q.dequeue();

            for(int w : G.adj(v)){
                if (!marked[w]){
                    //mark children

                    edgeTo[w] = v;
                    marked[w]=true;
                    n++;
                    q.enqueue(w);
                }
            }
        }

    }

    public int count(){
        return n;
    }

    public boolean marked(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> stack = new Stack<>();
        if (!marked[v]) return stack;
        for(int x = v; x!=s; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

}
