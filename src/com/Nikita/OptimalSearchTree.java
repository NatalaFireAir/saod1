package com.Nikita;

public class OptimalSearchTree extends SearchTree{
    int arrayLenght;
    Vertex[] Avertex;
    int[][] AR; //key matrix
    double[][] AW; //weight matrix
    double[][] AP; //w-heught matrix
    public  OptimalSearchTree() {
        arrayLenght = 4;
        Vertex v1= new Vertex(1,60);
        Vertex v2= new Vertex(2,30);
        Vertex v3= new Vertex(3,10);
        Avertex = new Vertex[4];
        Avertex[0]= new Vertex();
        Avertex[1] = v1;
        Avertex[2] = v2;
        Avertex[3] = v3;
        AW = new double[arrayLenght][arrayLenght];
        AP = new double[arrayLenght][arrayLenght];
        AR = new int[arrayLenght][arrayLenght];
        for(int i = 0; i < arrayLenght; i++ ){
            for (int j = i; j < arrayLenght; j++) {
                AW[i][j] = 0;
            }
        }
         createAW();
        for(int i = 0; i < arrayLenght; i++ ){
            for (int j = i; j < arrayLenght; j++) {
                System.out.print(AW[i][j] +" _ ");
            }
            System.out.println();
        }
        createAPAR();
        for(int i = 0; i < arrayLenght; i++ ){
            for (int j = i; j < arrayLenght; j++) {
                System.out.print(AP[i][j] + "*" + AR[i][j] + " _ ");
            }
            System.out.println();
        }

    }
    void createAW() {
        for(int i = 0; i < arrayLenght; i++ ){
            for (int j = i+1; j < arrayLenght; j++) {
                AW[i][j] = AW[i][j-1] + Avertex[j].weight;
               // System.out.print(AW[i][j] + " _ ");
            }
           // System.out.println();
        }
     //   return AW;
    }
    void createAPAR() {
        for(int i = 0; i < arrayLenght-1; i++ ){
            for (int j = i+1; j < arrayLenght; j++) {
                AP[i][j] = AW[i][j];
                AR[i][j] = i+1;
               // System.out.print(AR[i][j] + "/ "+ AP[i][j] + "___");
            }
           // System.out.println();
        }
        for (int h = 2; h < arrayLenght; h++) {
            for (int i = 0; i < arrayLenght - h; i++) {
                int j = i + h;
                int m = AR[i][j-1];
                double min = AP[i][m-1] + AP[m][j];
                for (int k = m+1; k < AR[i+1][j]; k++) {
                    double temp = AP[i][k-1] + AP[k][j];
                    if (temp < min) {
                        m = k;
                        min = temp;
                    }
                }
                AP[i][j] = min + AW[i][j];
                AR[i][j] = m;
                System.out.print(AP[i][j] +"*" +AR[i][j] + " _ ");
            }
            System.out.println();
        }

    }


}