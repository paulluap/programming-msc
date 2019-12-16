package string;

import datastructure.Bag;
import datastructure.Digraph;
import datastructure.Stack;
import graph.DirectedDFS;

public class RegularExpressionNFA {

    //alphabet transitions
    private char[] re;
    private int M;
    private Digraph G;

    //convention: pat is enclosed in parathesis  (....)
    public RegularExpressionNFA(String pat){

        Stack<Integer> ops = new Stack<>();
        M = pat.length();
        re = pat.toCharArray();
        G = new Digraph(M+1);
        for(int i=0; i<M; i++){
            int lp = i;
            if (re[i] == '(' || re[i] == '|') ops.push(i);
            if (re[i] ==')'){
                int or = ops.pop();
                if (re[or] == '|'){
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                }else{ //must be '('
                    lp = or;
                }
            }

            if (i < M-1  && re[i+1] == '*'  ){
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')')
                G.addEdge(i, i+1);
        }
    }

    public boolean recognize(String txt){

        /** possible states */
        Bag<Integer> states = new Bag<>();
        //init: find all reachable states from 0 via e-transition
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for(int v = 0; v<G.V();v++){
            if (dfs.marked(v)) states.add(v);
        }

        for(int i=0; i<txt.length(); i++){
            //take match transition
            Bag<Integer> match = new Bag<Integer>();
            for(int s : states){
                //why s < M ? if s==M, return true directly ?
                if (s<M)
                    if (re[s] == txt.charAt(i) || re[i] == '.') {
                        match.add(s+1);
                    }
            }
            //take e-transition
            dfs = new DirectedDFS(G, match);
            states = new Bag<>();
            //now we have a new set of all match transitioned (dfs.marked) + e-transitioned (dfs.marked) states
            for(int v=0; v<G.V(); v++){
                if (dfs.marked(v)) states.add(v);
            }
        }

        //in the end see if we have accept state :
//        System.out.println("end states: " + states);
        for(int v: states) if (v==M) return true;
        return false;
    }

}
