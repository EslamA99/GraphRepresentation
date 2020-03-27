package com.algo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Welcome extends JFrame {
    private JPanel panel1;
    private JButton ADDButton;
    private JButton removeButton;
    private JButton generateButton;
    private JTable edgeTable;
    private JButton ADDVetrexButton;
    private JButton REMOVEVertexButton;
    private JTable vertexTable;
    private JComboBox isDirected;
    private DefaultTableModel edgeTableModel;
    private DefaultTableModel vertexTableModel;

    Welcome() {
        edgeTableModel = new DefaultTableModel();
        vertexTableModel = new DefaultTableModel();
        setTitle("DataForm");
        setSize(800, 600);
        add(panel1);
        edgeTable.setModel(edgeTableModel);
        edgeTableModel.addColumn("From");
        edgeTableModel.addColumn("To");
        edgeTableModel.addColumn("Weight");
        String s1[]={"Un Directed Graph","Directed Graph"};
        isDirected.addItem("UnDirectedGraph");
        isDirected.addItem("DirectedGraph");
        isDirected.setEnabled(true);
        isDirected.setSelectedItem(0);
        vertexTable.setModel(vertexTableModel);
        vertexTableModel.addColumn("Vertex Name");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnActions();


    }

    private void btnActions() {
        ADDVetrexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vertexTableModel.insertRow(vertexTableModel.getRowCount(), new Object[]{""});
            }
        });
        REMOVEVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int[] rows = vertexTable.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    vertexTableModel.removeRow(rows[i] - i);
                }
            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                edgeTableModel.insertRow(edgeTableModel.getRowCount(), new Object[]{ "", "",""});
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int[] rows = edgeTable.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    edgeTableModel.removeRow(rows[i] - i);
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<String> vertices = new ArrayList<>();
                for (int i = 0; i < vertexTable.getRowCount(); i++) {
                    vertices.add(vertexTableModel.getValueAt(i, 0).toString());
                }
                ArrayList<Edge> edges = new ArrayList<>();
                for (int i = 0; i < edgeTable.getRowCount(); i++) {
                    int weight;
                    if(edgeTableModel.getValueAt(i,2).toString().isEmpty())weight=0;
                    else weight=Integer.parseInt(edgeTableModel.getValueAt(i,2).toString());
                    if(isDirected.getSelectedIndex()==0){
                        edges.add(new Edge(
                                edgeTableModel.getValueAt(i, 0).toString(),
                                edgeTableModel.getValueAt(i, 1).toString(),
                                false,
                                weight
                        ));
                    }else{
                        edges.add(new Edge(
                                edgeTableModel.getValueAt(i, 0).toString(),
                                edgeTableModel.getValueAt(i, 1).toString(),
                                true,
                                weight
                        ));
                    }
                }
                Matrices matrices = null;
                try{
                    matrices=new Matrices(vertices,edges);
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Matrices finalMatrices = matrices;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Result result = new Result(finalMatrices);
                            result.setVisible(true);
                        }
                    });
                }catch (Exception E){
                    JOptionPane.showMessageDialog(new JFrame(), "u may forgot to press enter at last element in vertex or edge table\n" +
                                    "or edge connect vertex not entered in vertex table", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                /*assert matrices != null;
                Hamilton ham = new Hamilton(vertices, matrices.getAdj());
                EulerUndirected euler = new EulerUndirected(vertices, edges);*/
            }
        });

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Welcome welcome = new Welcome();
                welcome.setVisible(true);
            }
        });
    }
}
