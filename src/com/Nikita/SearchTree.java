package com.Nikita;

/**
 * Класс - Дерево поиска, описывает процедуры, общие для разных деревьев поиска,
 * описывает общую структуру и является родительским классом для класса АВЛ-дерева (а также для классов
 * идеальное дерево поиска и случайное дерево поиска).
 */
public class SearchTree {
    Vertex root;

    /**
     * Класс - Вершина дерева, описывает структуру вершины бинарного дерева.
     * Каждую вершину можно рассматривать как отдельное дерево.
     * Содержит целочисленное значение и ссылки на левого и правого потомка.
     * Содержит переменную баланса, необходимую в АВЛ-дереве
     */
    class Vertex {
        int balance;
        int key;
        Vertex left;
        Vertex right;

        Vertex() {
            balance = 0;
            key = 0;
            left = null;
            right = null;
        }

        Vertex(int key) {
            balance = 0;
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public SearchTree() {
        root = null;
    }

    private static int max(int a, int b) {
        if (a >= b) return a;
        return b;
    }

    /**
     * Метод, осуществляющий проверку является ли данное дерево деревом поиска.
     */
    private boolean checkSearchTree(Vertex vertex) {
        if (vertex != null && (vertex.left != null && (vertex.key <= vertex.left.key || (checkSearchTree(vertex.left) != true))
                || (vertex.right != null && (vertex.key >= vertex.right.key || checkSearchTree(vertex.right) != true)))) {
            return false;
        }
        return true;
    }

    /**
     * Чтение дерева слева направо
     */
    private void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + "(" + vertex.balance + "), ");
            readLeftToRight(vertex.right);
        }
    }

    /**
     * Вычисление высоты дерева
     */
    private int findHeight(Vertex vertex) {
        int h;
        if (vertex == null) h = 0;
        else h = 1 + max(findHeight(vertex.left), findHeight(vertex.right));
        return h;
    }

    /**
     * Вычисление количества вершин дерева
     */
    private int findSize(Vertex vertex) {
        int size = 0;
        if (vertex != null) size = 1 + findSize(vertex.left) + findSize(vertex.right);
        return size;
    }

    /**
     * Вычисление суммы длин путей до вершины
     */
    private int pathsLengthSum(Vertex vertex, int level) {
        int pathsSum;
        if (vertex == null) pathsSum = 0;
        else pathsSum = level + pathsLengthSum(vertex.left, level + 1) + pathsLengthSum(vertex.right, level + 1);
        return pathsSum;
    }

    /**
     * Вычисление средней высоты дерева
     */
    private float findAverageHeight(Vertex vertex) {
        return (float) pathsLengthSum(vertex, 1) / findSize(vertex);
    }

    /**
     * Вычисление контрольной суммы дерева
     */
    private int findCheckSum(Vertex vertex) {
        int checkSum;
        if (vertex == null) checkSum = 0;
        else checkSum = vertex.key + findCheckSum(vertex.left) + findCheckSum(vertex.right);
        return checkSum;
    }

    /**
     * Публичные формы методов получения различных характеристик деерева
     */
    public boolean checkSearchTree() {
        return checkSearchTree(root);
    }

    public void readLeftToRight() {
        readLeftToRight(root);
    }

    public int findHeight() {
        return findHeight(root);
    }

    public int findSize() {
        return findSize(root);
    }

    public float findAverageHeight() {
        return findAverageHeight(root);
    }

    public int findCheckSum() {
        return findCheckSum(root);
    }
}