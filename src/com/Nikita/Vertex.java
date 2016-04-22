package com.Nikita;

public class Vertex {
    int data;
    int key;
    Vertex left;
    Vertex right;
    private static int max(int a, int b){
        if (a >= b) return a;
        return b;
    }
    Vertex(){
        key = 0;
    }
    Vertex(int data){
        this.key = data;
    }
    public void addLeft(Vertex vertex) {
        this.left = vertex;
    }
    public void addRight(Vertex vertex) {
        this.right = vertex;
    }

    public static void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + " ");
            readLeftToRight(vertex.right);
        }
    }

    public static int findHeight(Vertex vertex) {
        int h;
        if (vertex == null) h = 0;
        else h = 1 + max(findHeight(vertex.left), findHeight(vertex.right));
        return h;
    }

    public static int findSize(Vertex vertex) {
        int size = 0;
        if (vertex != null) size = 1 + findSize(vertex.left) + findSize(vertex.right);
        return size;
    }

    public static int pathsLengthSum(Vertex vertex, int level) {
        int pathsSum;
        if (vertex == null)pathsSum = 0;
        else pathsSum = level + pathsLengthSum(vertex.left, level + 1) + pathsLengthSum(vertex.right, level + 1);
        return pathsSum;
    }

    public static float findAverageHeight(Vertex vertex) {
    //    System.out.println(pathsLengthSum(vertex, 1));
        return (float)pathsLengthSum(vertex, 1)/findSize(vertex);
    }

    public static int findCheckSum(Vertex vertex) {
        int checkSum;
        if (vertex == null) checkSum = 0;
        else checkSum = vertex.key + findCheckSum(vertex.left) + findCheckSum(vertex.right);
        return checkSum;
    }

}
