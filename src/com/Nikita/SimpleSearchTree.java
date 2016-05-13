package com.Nikita;
/**
 * Класс - случайное дерево поиска(СДП), расширяет класс Дерево поиска - SearchTree
 */
public class SimpleSearchTree extends SearchTree{
    public SimpleSearchTree(int A[]) {
        for(int a:A) {
            try {
                insert(a);
            } catch (DuplicateValueException d) {
                System.out.println("duplicate value");
            }
        }
    }

    /**
     * . Если дерево пустое, то создается корневая вершина, в которую записываются данные.
     * В противном случае указатель переходит к левому или правому поддереву
     * в зависимости от результата сравнения с данными в текущей вершине.
     * Эти действия повторяются, пока вершина не займет положенное место,
     * или пока не обнаружится что в дереве уже есть вершина с таким значением.
     * В последнем случае будет брошено исключение DuplicateValueException
     */
    public void insert(int value) throws DuplicateValueException {
        if (root == null) {
            root = new Vertex(value);
            return;
        }
        Vertex p = root;
        while (p != null) {
            if (value < p.key) {
                if (p.left == null) {
                    p.left = new Vertex(value);
                    return;
                }
                else {
                    p = p.left;
                }
            }
            else if (value > p.key) {
                if (p.right == null) {
                    p.right = new Vertex(value);
                    return;
                } else {
                    p = p.right;
                }
            }
            else throw new DuplicateValueException();
        }
    }
}