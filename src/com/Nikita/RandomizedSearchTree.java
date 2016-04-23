package com.Nikita;

public class RandomizedSearchTree {
    Vertex root;
    public void addVertex(int value) throws duplicateValueException {
        Vertex p = root;
        while (p != null) {
            if (value < p.key) p = p.left;
            else if (value > p.key) p = p.right;
            else throw new duplicateValueException();
        }
        if (p == null) {
            p = new Vertex(value);
            p.left = null;
            p.right = null;
        }
    }
}
