package com.algo;

public class Edge implements Comparable<Edge>{
    //String id;
    String from;
    String to;
    boolean isDirected;
    int weight;
    public Edge(String from, String to, boolean isDirected,int weight) {
        //this.id = id;
        this.from = from;
        this.to = to;
        this.isDirected = isDirected;
        this.weight=weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

}
