package com.algo;

import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class SimpleGraphView {
    // Graph<V, E> where V is the type of the vertices
// and E is the type of the edges
    Graph<String, String> g = new SparseMultigraph<>();
    Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();

    SimpleGraphView() {
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex(("aaaaa"));
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("C");
        g.addVertex("D");
        g.addEdge("a-b","aaaaa","B");
        g.addEdge("a-C","aaaaa","C");

        //g.addEdge("7", 1, 5,EdgeType.DIRECTED);
// Let's see what we have. Note the nice output from the
// SparseMultigraph<V,E> toString() method
        System.out.println("The graph g = " + g.toString());
        // Note that we can use the same nodes and edges in two different graphs.

        g2.addVertex((Integer) 1);
        g2.addVertex((Integer) 2);
        g2.addVertex((Integer) 3);
        g2.addEdge("Edge-A", 1, 3);
        g2.addEdge("Edge-B", 2, 3, EdgeType.DIRECTED);
        g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
        g2.addEdge("Edge-P", 2, 3); // A parallel edge
        System.out.println("The graph g2 = " + g2.toString());
    }
}
