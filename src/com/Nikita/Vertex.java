package com.Nikita;
/**
 * Класс - Вершина дерева, описывает структуру вершины бинарного дерева.
 * Каждую вершину можно рассматривать как отдельное дерево.
 * Содержит целочисленное значение и ссылки на левого и правого потомка.
 * */
public class Vertex {
    int key;
    Vertex left;
    Vertex right;

    private static int max(int a, int b){
        if (a >= b) return a;
        return b;
    }
    
    public Vertex(int data){
        this.key = data;
    }
    /**
     * Чтение делева слева направо
     */
    public static void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + " ");
            readLeftToRight(vertex.right);
        }
    }
    /**
     * Вычисление высоты дерева
     */
    public static int findHeight(Vertex vertex) {
        int h;
        if (vertex == null) h = 0;
        else h = 1 + max(findHeight(vertex.left), findHeight(vertex.right));
        return h;
    }
    /**
     * Вычисление количества вершин дерева
     */
    public static int findSize(Vertex vertex) {
        int size = 0;
        if (vertex != null) size = 1 + findSize(vertex.left) + findSize(vertex.right);
        return size;
    }
    /**
     * Вычисление суммы длин путей до вершины
     */
    public static int pathsLengthSum(Vertex vertex, int level) {
        int pathsSum;
        if (vertex == null)pathsSum = 0;
        else pathsSum = level + pathsLengthSum(vertex.left, level + 1) + pathsLengthSum(vertex.right, level + 1);
        return pathsSum;
    }
    /**
     * Вычисление средней высоты дерева
     */
    public static float findAverageHeight(Vertex vertex) {
        return (float)pathsLengthSum(vertex, 1)/findSize(vertex);
    }
    /**
     * Вычисление контрольной суммы дерева(суммы ключей всех вершин)
     */
    public static int findCheckSum(Vertex vertex) {
        int checkSum;
        if (vertex == null) checkSum = 0;
        else checkSum = vertex.key + findCheckSum(vertex.left) + findCheckSum(vertex.right);
        return checkSum;
    }
}
