package com.algo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Welcome extends JFrame{
    private JTextArea numOfNodes;
    private JPanel panel1;
    private JButton adjecentButton;
    private JTextArea src;
    private JTextArea dest;
    public static ArrayList<String> edges;

    Welcome(){
        setTitle("DataForm");
        setSize(800, 600);
        add(panel1);
        adjecentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s = numOfNodes.getText();
                edges = getEdges();
                System.out.println(edges);
            }
        });
    }
    ArrayList<String> getEdges(){
        ArrayList<String> myArray = new ArrayList<>();
        String from = src.getText();
        String to = dest.getText();
        myArray.add(from);
        myArray.add(to);
        return myArray;
    }
    public void getAdjacentMatrix(){
        int row = Integer.parseInt(numOfNodes.getText());
        int col = Integer.parseInt(numOfNodes.getText());
        int[][] mat = new int[row][col];
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Welcome gui = new Welcome();
                gui.setVisible(true);
            }
        });
    }
}
