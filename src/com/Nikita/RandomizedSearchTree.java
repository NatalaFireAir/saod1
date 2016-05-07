package com.Nikita;

public class RandomizedSearchTree extends SearchTree{

    public RandomizedSearchTree() {
        root = null;
    }
    public void addVertex(int value) throws duplicateValueException {
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
            else throw new duplicateValueException();
        }
    }
}