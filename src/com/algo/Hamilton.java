package com.algo;

import java.util.ArrayList;

class Hamilton {
    private int V;
    private ArrayList<String> vertices;

    Hamilton(ArrayList<String> vertices, int[][] adj) {
        this.vertices = vertices;
        V = vertices.size();
        int[][] graph;
        graph = adj;
        test(graph);
    }

    private void test(int[][] graph) {
        hamCycle(graph);
    }

    private boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        if (graph[path[pos - 1]][v] == 0)
            return false;

        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    private boolean hamCycleUtil(int[][] graph, int[] path, int pos) {
        if (pos == V) {
            return graph[path[pos - 1]][path[0]] == 1;
        }
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamCycleUtil(graph, path, pos + 1))
                    return true;
                path[pos] = -1;
            }
        }
        return false;
    }

    private void hamCycle(int[][] graph) {
        int[] path = new int[V];
        for (int i = 0; i < V; i++)
            path[i] = -1;

        path[0] = 0;
        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("\nSolution does not exist");
            return;
        }
        printSolution(path);
    }

    private void printSolution(int[] path) {
        System.out.println("Hamiltonian Cycle::");
        for (int i = 0; i < V; i++)
            System.out.print(" " + vertices.get(path[i]) + " ");
        System.out.println(" " + vertices.get(path[0]) + " ");

        System.out.println("Hamiltonian Path::");
        for (int i = 0; i < V; i++)
            System.out.print(" " + vertices.get(path[i]) + " ");
        System.out.println();
    }
}
