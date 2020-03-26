package com.algo;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class EulerUndirected {
    private int nVertices;
    private LinkedList<Integer> adj[];
    private ArrayList<String> vertices;
    private ArrayList<Edge> edges;
    private HashMap<String, Integer> VtoI;
    private  ArrayList<Edge>eulerEdges;
    private Graph<String, Edge> eulerGraph = new SparseMultigraph<>();
    public EulerUndirected(ArrayList<String> vertices, ArrayList<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.nVertices = vertices.size();
        this.eulerEdges=new ArrayList<>();
        VtoI = new HashMap<>();
        for (int i = 0; i < nVertices; i++)
            VtoI.put(vertices.get(i), i);

        adj = new LinkedList[nVertices];
        for (int i=0; i<nVertices; ++i)
            adj[i] = new LinkedList();

        addEdges();
        test();

        for(Edge e:eulerEdges)
            System.out.print(e.from+" "+e.to+" ");
        System.out.println();
        setEulerGraphGraph();
    }
    public Graph<String, Edge> getEulerGraph() {
        return eulerGraph;
    }

    private void setEulerGraphGraph() {
        for (String v : vertices)
            eulerGraph.addVertex(v);
        for (Edge edge : eulerEdges) {
            if (edge.isDirected) {
                eulerGraph.addEdge(edge, edge.from, edge.to, EdgeType.DIRECTED);
            } else {
                eulerGraph.addEdge(edge, edge.from, edge.to);
            }
        }
    }

    private void addEdges() {
        for (int i = 0; i < edges.size(); i++) {
            adj[VtoI.get(edges.get(i).from)].add(VtoI.get(edges.get(i).to));
            if(!edges.get(i).isDirected)
                adj[VtoI.get(edges.get(i).to)].add(VtoI.get(edges.get(i).from));
        }
    }

    void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    }

    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    boolean isConnected()
    {
        boolean visited[] = new boolean[nVertices];
        int i;
        for (i = 0; i < nVertices; i++)
            visited[i] = false;

        for (i = 0; i < nVertices; i++)
            if (adj[i].size() != 0)
                break;

        if (i == nVertices)
            return true;

        DFSUtil(i, visited);

        for (i = 0; i < nVertices; i++)
            if (visited[i] == false && adj[i].size() > 0)
                return false;

        return true;
    }

    int isEulerian()
    {
        if (isConnected() == false)
            return 0;

        int odd = 0;
        for (int i = 0; i < nVertices; i++)
            if (adj[i].size()%2!=0)
                odd++;

        if (odd > 2)
            return 0;

        return (odd==2)? 1 : 2;
    }

    void test()
    {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1) {
            System.out.println("graph has an Euler path");
            printEulerTour();
        }
        else {
            System.out.println("graph has an Euler cycle");
            printEulerTour();
        }
    }

    private void removeEdge(Integer u, Integer v)
    {
        adj[u].remove(v);
        adj[v].remove(u);
    }

    private void printEulerTour()
    {
        Integer u = 0;
        for (int i = 0; i < nVertices; i++)
        {
            if (adj[i].size() % 2 == 1)
            {
                u = i;
                break;
            }
        }
        printEulerUtil(u);
        System.out.println();
    }

    private void printEulerUtil(Integer u) {
        for (int i = 0; i < adj[u].size(); i++) {
            Integer v = adj[u].get(i);
            if (isValidNextEdge(u, v)) {
                String uu="", vv="";
                for (String s:vertices)
                    if(VtoI.get(s) == u) uu = s;
                for (String s:vertices)
                    if(VtoI.get(s) == v) vv = s;
                for(Edge e:edges)
                    if(e.from.equals(uu) && e.to.equals(vv))
                        eulerEdges.add(e);
                removeEdge(u, v);
                printEulerUtil(v);
            }
        }
    }

    private boolean isValidNextEdge(Integer u, Integer v)
    {
        if (adj[u].size() == 1) {
            return true;
        }

        boolean[] isVisited = new boolean[this.nVertices];
        int count1 = dfsCount(u, isVisited);

        removeEdge(u, v);
        isVisited = new boolean[this.nVertices];
        int count2 = dfsCount(u, isVisited);

        addEdge(u, v);
        return (count1 > count2) ? false : true;
    }

    private int dfsCount(Integer v, boolean[] isVisited)
    {
        isVisited[v] = true;
        int count = 1;
        for (int adj : adj[v])
        {
            if (!isVisited[adj])
            {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
}