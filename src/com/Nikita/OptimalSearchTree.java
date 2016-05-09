package com.Nikita;

public class OptimalSearchTree extends SearchTree{
    int arrayLenght;
    Vertex[] Avertex;
    int[][] AR; //key matrix
    double[][] AW; //weight matrix
    double[][] AP; //w-heught matrix
    double[][] createAW() {
        for(int i = 0; i < Avertex.length; i++ ){
            for (int j = i+1; j < AW[i].length; j++) {
                AW[i][j] = AW[i][j-1] + Avertex[j].weight;
            }
        }
        return AW;
    }
    int[][] createAR() {
        for(int i = 0; i < Avertex.length-1; i++ ){
            for (int j = i+1; j < AR[i].length; j++) {
                AR[i][j+1] = i+1;
                System.out.print(AR[i][j+1]);
            }
            System.out.println();
        }
        return AR;
    }
    double min(double ... D){
        double min = D[0];
        for(double d:D){
            if(d < min) {
                min = d;
            }
        }
        return min;
    }
    double[][] createAP() {
        for(int i = 0; i < Avertex.length-1; i++ ){
            for (int j = i+1; j < AR[i].length; j++) {
                AP[i][j+1] = AW[i][j+1];
                System.out.print(AR[i][j+1]);
            }
            System.out.println();
        }
       
        return AP;
    }

}