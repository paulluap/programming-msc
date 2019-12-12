package datastructure;

public class DirectedEdge implements Comparable<DirectedEdge>{
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }
    public double weight(){
        return weight;
    }

    @Override
    public String toString() {
        return v + "->" + w + "(" + weight + ")";
    }

    @Override
    public int compareTo(DirectedEdge o) {
        if (this.weight() < o.weight()) return -1;
        if (this.weight() > o.weight()) return 1;
        return 0;
    }
}
