package com.algo;

import java.util.LinkedList;
import java.util.Scanner;

public class Main2 {

    void divide(int [] []arr,int left,int right){

    }

    private static int[][]adj;
    private static int[][]rep;
    private static int[][]inc;
    private static LinkedList<Integer> adjLists[];
    private static int countriesNumber;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        countriesNumber = sc.nextInt();
        int numOfEdges=sc.nextInt();
        adj = new int[countriesNumber][countriesNumber];
        rep = new int[countriesNumber][countriesNumber];
        inc = new int[countriesNumber][numOfEdges];
        adjLists = new LinkedList[countriesNumber];

        for(int i = 0; i < countriesNumber; i++){
            adjLists[i] = new LinkedList<>();
        }

        int u, v;
        String q="";
        for (int i = 0; i < numOfEdges; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            q=sc.nextLine();
            inc[u-1][i]++;
            inc[v-1][i]++;
            rep[u - 1][v - 1] ++;
            if(adj[u - 1][v - 1] ==0)adj[u - 1][v - 1]++;
            if(u!=v) {
                rep[v - 1][u - 1]++;
                if (adj[v - 1][u - 1] == 0) adj[v - 1][u - 1]++;
            }
            adjLists[u].add(v);
        }

        System.out.println("Adjacency Matrix");
        for (int i = 0; i < countriesNumber ; i++) {
            for (int j = 0; j < countriesNumber ; j++) {
                System.out.print(adj[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Representation Matrix");
        for (int i = 0; i < countriesNumber ; i++) {
            for (int j = 0; j < countriesNumber ; j++) {
                System.out.print(rep[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Incidence Matrix");
        for (int i = 0; i < countriesNumber ; i++) {
            for (int j = 0; j < numOfEdges ; j++) {
                System.out.print(inc[i][j]+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < countriesNumber; i++){
            System.out.print("Node "+ i +" is connected to: ");
            for (int j = 0; j < adjLists[i].size(); j++){
                System.out.print(adjLists[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}
