package com.algo;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class Matrices {
    private ArrayList<String> vertices;
    private ArrayList<Edge> edges;
    private int[][] adj;
    private int[][] rep;
    private int[][] inc;
    private Graph<String, String> repGraph = new SparseMultigraph<>();

    public Matrices(ArrayList<String> vertices, ArrayList<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        generateAdjMatrix();
        generateRepMatrix();
        generateIncMatrix();
        setRepGraph();
    }

    private void setRepGraph() {
        int id = 0;
        for (String v : vertices)
            repGraph.addVertex(v);
        for (Edge edge : edges) {
            if (edge.isDirected) {
                repGraph.addEdge(edge.from + "-" + edge.to + "-" + id, edge.from, edge.to, EdgeType.DIRECTED);
            } else {
                repGraph.addEdge(edge.from + "-" + edge.to + "-" + id, edge.from, edge.to);
            }
            id++;
        }
    }

    public Graph<String, String> getRepGraph() {
        return repGraph;
    }

    private void generateRepMatrix() {
        rep = new int[vertices.size()][vertices.size()];
        for (Edge edge : edges) {
            if(edge.isDirected)
                rep[vertices.indexOf(edge.from)][vertices.indexOf(edge.to)] ++;
            else{
                rep[vertices.indexOf(edge.from)][vertices.indexOf(edge.to)] ++;
                rep[vertices.indexOf(edge.to)][vertices.indexOf(edge.from)] ++;
            }
        }
        printRep();
    }
    private void generateAdjMatrix() {
        adj = new int[vertices.size()][vertices.size()];
        for (Edge edge : edges) {
            if(edge.isDirected)
                adj[vertices.indexOf(edge.from)][vertices.indexOf(edge.to)] = 1;
            else{
                adj[vertices.indexOf(edge.from)][vertices.indexOf(edge.to)] = 1;
                adj[vertices.indexOf(edge.to)][vertices.indexOf(edge.from)] = 1;
            }
        }
        //printAdj();
    }
    private void generateIncMatrix() {
        inc = new int[vertices.size()][edges.size()];
        for (int i=0;i<edges.size();i++) {
            Edge edge=edges.get(i);
            inc[vertices.indexOf(edge.from)][i] = 1;
            inc[vertices.indexOf(edge.to)][i] = 1;
        }
        printInc();
    }

    private void printInc() {
        System.out.println("Inc");
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(inc[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setAdjTable(DefaultTableModel adjTableModel) {
        adjTableModel.addColumn("Vertex Name");
        for(String v:vertices)adjTableModel.addColumn(v);
        for (int i = 0; i < vertices.size(); i++) {
            Vector<String>row=new Vector<>();
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printRep() {
        System.out.println("REP");
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(rep[i][j] + " ");
            }
            System.out.println();
        }
    }
}
