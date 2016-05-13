package com.Nikita;

/**
 * Класс - Дерево поиска, описывает процедуры, общие для разных деревьев поиска,
 * описывает общую структуру и является родительским классом для классов
 * идеальное дерево поиска и случайное дерево поиска.структуру вершины бинарного дерева.
 */
public class SearchTree {
    Vertex root;

    /**
     * Класс - Вершина дерева, описывает структуру вершины бинарного дерева.
     * Каждую вершину можно рассматривать как отдельное дерево.
     * Содержит целочисленное значение и ссылки на левого и правого потомка.
     */
    class Vertex {
        int key;
        Vertex left;
        Vertex right;

        Vertex() {
            key = 0;
            left = null;
            right = null;
        }

        Vertex(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public SearchTree() {
        root = null;
    }


    /**
     * Метод, осуществляющий проверку является ли данное дерево деревом поиска.
     * Если хотя бы для одной вершины дерева условия не выполняются, то все дерево не является деревом поиска.
     * Проверка вершин дерева осуществляется с помощью рекурсивных вызовов данного метода для левого и правого потомка каждой вершины
     */
    private boolean checkSearchTree(Vertex vertex) {
        if (vertex != null && (vertex.left != null && (vertex.key <= vertex.left.key || (checkSearchTree(vertex.left) != true))
                || (vertex.right != null && (vertex.key >= vertex.right.key || checkSearchTree(vertex.right) != true)))) {
            return false;
        }
        return true;
    }

    /**
     * Чтение делева слева направо
     */
    private void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + " ");
            readLeftToRight(vertex.right);
        }
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
     * Поиск вершины в дереве. Если вершина найдена, возвращает число шагов, потребовавшихся для нахождения вершины.
     * Если в дереве нет искомого значения, то выбрасывает исключение ValueNotFoundException
     */
    public int search(int x) throws ValueNotFoundException {
        int steps = 0;
        Vertex pointer = root;
        while (pointer != null) {
            steps++;
            if (pointer.key < x) pointer = pointer.right;
            else if (pointer.key > x) pointer = pointer.left;
            else return steps;
        }
        throw new ValueNotFoundException();
    }

    /**
     * Публичные формы методов checkSearchTree(Vertex vertex), readLeftToRight(Vertex vertex),
     * findAverageHeight(Vertex vertex)
     */
    public boolean checkSearchTree() {
        return checkSearchTree(root);
    }
    public void readLeftToRight() {
        readLeftToRight(root);
    }
    public float findAverageHeight() {
        return findAverageHeight(root);
    }
}
