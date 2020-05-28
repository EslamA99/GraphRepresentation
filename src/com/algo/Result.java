package com.algo;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Result extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel graphPanel;
    private JTable adjResultTable;
    private JTable repResultTable;
    private JTable incResultTable;
    private JTable adjListResultTable;
    private JPanel minGraphPannel;
    private JPanel eulerGraph;
    private JPanel graphColoring;
    private JLabel msg;
    private JPanel hamiltonGraph;
    private JPanel minHamiltonGraph;
    private JPanel DijkstraTab;
    private JPanel Dijkstra;
    private JPanel MaxFlowTab;
    private JPanel maxFlow;
    private JLabel maxFlowValue;
    private JLabel allPathesMaxFlow;
    private JButton nextEdgeInMaxFlow;
    private JButton nextEdgeInDij;
    private JTextField allPaths;
    private JTextField totalFlow;
    private DefaultTableModel adjTableModel;
    private DefaultTableModel repTableModel;
    private DefaultTableModel incTableModel;
    private DefaultTableModel adjListModel;
    private Matrices matrices;
    int count=1;
    Result(Matrices matrices) {
        this.matrices = matrices;
        adjTableModel = new DefaultTableModel();
        repTableModel = new DefaultTableModel();
        incTableModel = new DefaultTableModel();
        adjListModel = new DefaultTableModel();
        adjResultTable.setModel(adjTableModel);
        repResultTable.setModel(repTableModel);
        incResultTable.setModel(incTableModel);
        adjListResultTable.setModel(adjListModel);
        adjResultTable.setEnabled(false);
        repResultTable.setEnabled(false);
        incResultTable.setEnabled(false);
        adjListResultTable.setEnabled(false);
        setTitle("ResultForm");
        setSize(800, 600);
        add(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setMatricesIntoTables();
        setGraph();
        setDijGraph();
        setMaxFlowGraph();
        setMinGraph();
        setEulerGraph();
        setGraphColoring();
        setHamiltonGraph();
        setMinHamiltonGraph();


    }

    private void setMaxFlowGraph() {

        MaxFlow maxFlowObj = new MaxFlow(matrices);
        this.maxFlow.setLayout(new BorderLayout());

        Layout<String, Edge> layout3 = new CircleLayout<>(maxFlowObj.getMinGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);
        allPathesMaxFlow.setText("All Paths: "+maxFlowObj.allPaths);
        maxFlowValue.setText("Total Flow:  "+maxFlowObj.totalFlow);
        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));

        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.flow));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);
        this.maxFlow.add(vv3, BorderLayout.NORTH);

    }

    private void setHamiltonGraph() {

        hamiltonGraph.setLayout(new BorderLayout());
        if (matrices.getEdges().get(0).isDirected) {
            JLabel label2 = new JLabel("<html><h1>cannot be generate because it's directed graph</h1></html>");
            hamiltonGraph.add(label2);
            return;
        }
        Hamilton hamilton = new Hamilton(matrices.getVertices(), matrices.getAdj(), matrices.getEdges());
        JLabel label2 = new JLabel("<html><h3>" + hamilton.getOutput() + "</h1></html>");
        hamiltonGraph.add(label2);

    }

    private void setMinHamiltonGraph() {
        minHamiltonGraph.setLayout(new BorderLayout());
        if (matrices.getEdges().get(0).isDirected) {
            JLabel label2 = new JLabel("<html><h1>cannot be generate because it's directed graph</h1></html>");
            minHamiltonGraph.add(label2);
            return;
        }
        MinHamilton minHamilton = new MinHamilton(matrices.getVertices(), matrices.getAdj(), matrices.getEdges());
        JLabel label2 = new JLabel("<html><h3>" + minHamilton.getOutput() + "</h1></html>");
        minHamiltonGraph.add(label2);
    }

    private void setGraphColoring() {
        GraphColoring graphColoring = new GraphColoring(matrices);
        Layout<String, Edge> layout3 = new CircleLayout<>(matrices.getColoredGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);
        Transformer<String, Paint> vertexPaint1 = new Transformer<String, Paint>() {

            @Override
            public Paint transform(String s) {
                return graphColoring.getResult().get(matrices.getVertices().indexOf(s));
            }
        };
        vv3.getRenderContext().setVertexFillPaintTransformer(vertexPaint1);
        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));

        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);

        this.graphColoring.setLayout(new BorderLayout());
        this.graphColoring.add(vv3, BorderLayout.NORTH);
    }

    private void setEulerGraph() {
        eulerGraph.setLayout(new BorderLayout());
        EulerUndirected eulerUndirected = new EulerUndirected(matrices.getVertices(), matrices.getEdges());
        JLabel label2;
        if(matrices.getEdges().get(0).isDirected){
            label2 = new JLabel("<html><h1>cannot be generate because it's directed graph</h1></html>");
            eulerGraph.add(label2);
            return;
        }

        switch (eulerUndirected.getCurrPath()) {
            case 0:
                label2 = new JLabel("<html><h1>graph is not Eulerian</h1></html>");
                eulerGraph.add(label2);
                return;
            case 1:
                label2 = new JLabel("<html><h2>graph has an Euler path</h2>" +
                        "<h3>"+eulerUndirected.getOutput()+"</h3></html>");
                eulerGraph.add(label2);
                break;
            case 2:
                label2 = new JLabel("<html><h2>graph has an Euler cycle</h2>" +
                        "<h3>"+eulerUndirected.getOutput()+"</h3></html>");
                eulerGraph.add(label2);
                break;
        }
        //JLabel label3=new JLabel("<html><h3>"+eulerUndirected.getOutput()+"</h3></html>");
       // eulerGraph.add(label3);


    }

    private void setMinGraph() {
        /***/
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(matrices);
        minGraphPannel.setLayout(new BorderLayout());
        if (matrices.getEdges().get(0).isDirected) {
            JLabel label2 = new JLabel("<html><h1>cannot be generate because it's directed graph</h1></html>");
            minGraphPannel.add(label2);
            return;
        }


        Layout<String, Edge> layout3 = new CircleLayout<>(minimumSpanningTree.getMinGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);

        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));

        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);


        minGraphPannel.add(vv3, BorderLayout.NORTH);
    }

    private void setDijGraph() {
        Dijkstra dijkstraObj = new Dijkstra(matrices);
        Dijkstra.setLayout(new BorderLayout());
        int verticesCount=dijkstraObj.getMinGraph().getVertexCount();
        String[] V = new String[dijkstraObj.getMinGraph().getVertexCount()];
        Edge[] E = new Edge[dijkstraObj.getMinGraph().getEdgeCount()];
        dijkstraObj.getMinGraph().getVertices().toArray(V);
        dijkstraObj.getMinGraph().getEdges().toArray(E);
        Graph<String, Edge> tempGraph = new SparseMultigraph<>();
        tempGraph.addVertex(V[0]);
        Layout<String, Edge> layout3 = new CircleLayout<>(tempGraph);
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);
        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);
        Dijkstra.add(vv3, BorderLayout.NORTH);
        nextEdgeInDij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dijkstra.removeAll();
                revalidate();
                repaint();
                tempGraph.addVertex(V[count]);
                tempGraph.addEdge(E[count - 1], V[count - 1], V[count]);
                count++;
                Layout<String, Edge> layout3 = new CircleLayout<>(tempGraph);
                VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);
                vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
                vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
                final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
                vv3.setGraphMouse(graphMouse3);
                graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);
                Dijkstra.add(vv3, BorderLayout.NORTH);
                if(count==V.length)
                    nextEdgeInDij.setEnabled(false);
            }
        });


    }

    private void setMatricesIntoTables() {
        matrices.setAdjTable(adjTableModel);
        matrices.setRepTable(repTableModel);
        matrices.setIncTable(incTableModel);
        matrices.setAdjListsTable(adjListModel);
    }

    private void setGraph() {

        Layout<String, Edge> layout3 = new CircleLayout<>(matrices.getRepGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);

        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));

        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);

        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(vv3, BorderLayout.NORTH);

    }
}
