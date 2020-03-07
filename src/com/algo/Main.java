package com.algo;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import javax.xml.transform.*;
import java.awt.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // write your code here
        /*SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here
// The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout = new CircleLayout(sgv.g);
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the space
// The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer, String> vv =
                new BasicVisualizationServer<Integer, String>(layout);
        Transformer<String,String>transformer=new Transformer<String, String>() {
            @Override
            public String transform(String s) {
                return s;
            }
        };
        vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size
        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);*/



        SimpleGraphView sg=new SimpleGraphView();

        Layout<Integer, String> layout3 = new CircleLayout(sg.g);
        VisualizationViewer<Integer, String> vv3 = new  VisualizationViewer<Integer,String>(layout3);
        layout3.setSize(new Dimension(800, 600));
        vv3.setPreferredSize(new Dimension(350, 350));
        Transformer<String, String> transformer3 = new Transformer<String, String>() {

            @Override
            public String transform(String arg0){
                return arg0;
            }
        };


        vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        final DefaultModalGraphMouse<String,Number> graphMouse3 = new DefaultModalGraphMouse<String,Number>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);


        JFrame frame3 = new JFrame("Pas 3");
        frame3.getContentPane().add(vv3);
        frame3.pack();
        frame3.setSize(800, 600);
        frame3.setVisible(true);



        /*SimpleGraphView sgv = new SimpleGraphView(); // This builds the graph
        // Layout<V, E>, BasicVisualizationServer<V,E>
        Layout<Integer, String> layout = new CircleLayout(sgv.g);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<Integer, String> vv =
                new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(350, 350));
        // Setup up a new vertex to paint transformer...
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            public Paint transform(Integer i) {
                return Color.GREEN;
            }
        };
        // Set up a new stroke Transformer for the edges
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer =
                new Transformer<String, Stroke>() {
                    public Stroke transform(String s) {
                        return edgeStroke;
                    }
                };

        // vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        // vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame frame = new JFrame("Simple Graph View 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
*/    }
}
