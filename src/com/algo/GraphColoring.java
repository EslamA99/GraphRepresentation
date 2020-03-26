package com.algo;

import java.util.ArrayList;

public class GraphColoring {
    private ArrayList<String> V;
    private int m ;
    private int[] x;
    private int[][]Graph;

    public GraphColoring(Matrices mat){
        Graph = mat.getAdj();
        V = mat.getVertices();
        x=new int[V.size()];
        m=getMinimumColors();

    }
    public int getMinimumColors(){
        int count = 0, max = 0;
        for(int i = 0 ; i < V.size(); i++){
            for (int j = 0; j < V.size(); j++){
                if(Graph[i][j] == 1){
                    count++;
                }
            }
            if(max < count) {
                max = count;
            }
            count = 0;
        }
        return max;
    }

    public boolean isSafe(int k,int c){
        for(int i = 0;i < V.size(); i++){
            if(Graph[k][i] == 1 && c == x[i]){
                return false;
            }
        }
        return true;
    }

    public void graphColor(int k){

        for(int c = 1; c <= m ; c++){
            if(isSafe(k,c)){
                x[k] = c;
                if( k+1 < V.size()){
                    graphColor(k+1);
                }
                else{
                    return;
                }
            }
        }
    }
}