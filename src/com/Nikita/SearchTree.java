package com.Nikita;

public class SearchTree {
  Vertex root;
    class Vertex {
        boolean use;
        double weight;
        int balance;
        int height;
        int key;
        Vertex left;
        Vertex right;

        Vertex(){
            balance = 0;
            key = 0;
            left = null;
            right = null;
            height = 0;
        }
        Vertex(int key){
            balance = 0;
            this.key = key;
            this.left = null;
            this.right = null;
            height = 0;
        }
        Vertex(int key, double weight){
            balance = 0;
            this.key = key;
            this.weight = weight;
            this.left = null;
            this.right = null;
            height = 0;
        }

        Vertex(Vertex v){
           root = v;
        }

    }
    public SearchTree() {
        root = null;
    }


    protected static int max(int a, int b){
        if (a >= b) return a;
        return b;
    }
    private boolean checkSearchTree(Vertex vertex) {
        if (vertex != null && (vertex.left != null && (vertex.key <= vertex.left.key || (checkSearchTree(vertex.left) != true))
                || (vertex.right != null && (vertex.key >= vertex.right.key || checkSearchTree(vertex.right) != true)))) {
            return false;
        }
        return true;
    }
    private void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + ", ");
            readLeftToRight(vertex.right);
        }
    }
    private int findHeight(Vertex vertex) {
        int h;
        if (vertex == null) h = 0;
        else h = 1 + max(findHeight(vertex.left), findHeight(vertex.right));
        return h;
    }
    private int findSize(Vertex vertex) {
        int size = 0;
        if (vertex != null) size = 1 + findSize(vertex.left) + findSize(vertex.right);
        return size;
    }
    private int pathsLengthSum(Vertex vertex, int level) {
        int pathsSum;
        if (vertex == null)pathsSum = 0;
        else pathsSum = level + pathsLengthSum(vertex.left, level + 1) + pathsLengthSum(vertex.right, level + 1);
        return pathsSum;
    }
    private float findAverageHeight(Vertex vertex) {
        return (float)pathsLengthSum(vertex, 1)/findSize(vertex);
    }
    private int findCheckSum(Vertex vertex) {
        int checkSum;
        if (vertex == null) checkSum = 0;
        else checkSum = vertex.key + findCheckSum(vertex.left) + findCheckSum(vertex.right);
        return checkSum;
    }
    public int search(int x) throws valueNotFoundException{
        int steps = 0;
        Vertex pointer = root;
        while(pointer != null) {
            steps ++;
            if (pointer.key < x) pointer = pointer.right;
            else if (pointer.key > x) pointer = pointer.left;
            else return steps;
        }
        throw new valueNotFoundException();
    }
    public boolean checkSearchTree(){
        return checkSearchTree(root);
    }
    public void readLeftToRight() { readLeftToRight(root); }
    public int findHeight() { return findHeight(root); }
    public int findSize() { return findSize(root); }
    public float findAverageHeight() { return findAverageHeight(root); }
    public int findCheckSum() { return findCheckSum(root); }
}
