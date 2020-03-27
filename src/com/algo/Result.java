package com.algo;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
    private DefaultTableModel adjTableModel;
    private DefaultTableModel repTableModel;
    private DefaultTableModel incTableModel;
    private DefaultTableModel adjListModel;
    private Matrices matrices;

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
        setMinGraph();
        setEulerGraph();
        setGraphColoring();
    }

    private void setGraphColoring() {
        GraphColoring graphColoring = new GraphColoring(matrices);
        Layout<String, Edge> layout3 = new CircleLayout<>(matrices.getColoredGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);
        Transformer<String, Paint> vertexPaint1 = new Transformer<>() {

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
        if (matrices.getEdges().get(0).isDirected) {
            JLabel label2 = new JLabel("<html><h1>cannot be generate because it's directed graph</h1></html>");
            eulerGraph.add(label2);
            return;
        }

        EulerUndirected eulerUndirected = new EulerUndirected(matrices.getVertices(), matrices.getEdges());
        switch (eulerUndirected.getCurrEulerPath()) {
            case 0:
                JLabel label2 = new JLabel("<html><h1>graph is not Eulerian</h1></html>");
                eulerGraph.add(label2);
                return;
            case 1:
                msg.setText("Euler Path");
                break;
            case 2:
                msg.setText("Euler cycle");
                break;
        }


        Layout<String, Edge> layout3 = new CircleLayout<>(eulerUndirected.getEulerGraph());
        VisualizationViewer<String, Edge> vv3 = new VisualizationViewer<>(layout3);

        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));

        vv3.getRenderContext().setVertexLabelTransformer(String::valueOf);
        vv3.getRenderContext().setEdgeLabelTransformer(s -> String.valueOf(s.weight));
        final DefaultModalGraphMouse<String, Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);

        //eulerGraph.setLayout(new BorderLayout());
        eulerGraph.add(vv3, BorderLayout.NORTH);
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
