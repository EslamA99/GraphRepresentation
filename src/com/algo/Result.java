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
    private DefaultTableModel adjTableModel;
    private DefaultTableModel repTableModel;
    private Matrices matrices;

    Result(Matrices matrices){
        this.matrices=matrices;
        adjTableModel=new DefaultTableModel();
        repTableModel=new DefaultTableModel();
        setTitle("ResultForm");
        setSize(800, 600);
        add(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setGraph();
        setMatricesIntoTables();

    }

    private void setMatricesIntoTables() {
        matrices.setAdjTable(adjTableModel);
    }

    private void setGraph() {

        Layout<Integer, String> layout3 = new CircleLayout(matrices.getRepGraph());
        VisualizationViewer<Integer, String> vv3 = new  VisualizationViewer<Integer,String>(layout3);

        //layout3.setSize(new Dimension(770, 570));
        //vv3.setPreferredSize(new Dimension(350, 350));
        Transformer<String, String> transformer3 = new Transformer<String, String>() {

            @Override
            public String transform(String arg0){
                return arg0;
            }
        };
        vv3.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        final DefaultModalGraphMouse<String,Number> graphMouse3 = new DefaultModalGraphMouse<>();
        vv3.setGraphMouse(graphMouse3);
        graphMouse3.setMode(ModalGraphMouse.Mode.PICKING);

        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(vv3,BorderLayout.NORTH);

    }
}
