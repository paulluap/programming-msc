package datastructure;

public class Edge implements Comparable<Edge> {

    private double weight;
    private int v;
    private int w;

    public Edge(int v, int w, double weight){
        this.weight = weight;
        this.v = v;
        this.w = w;
    }

    public double weight(){
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int x){
        return x == v ? w : v;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
