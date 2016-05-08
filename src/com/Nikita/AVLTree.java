package com.Nikita;

public class AVLTree extends SearchTree {
    int turnCounter = 0;
    public AVLTree() { }
    public AVLTree(int[] A) {
        for(int a:A) {
            try {
                root = insert(a, root);
            } catch (duplicateValueException d) {
                System.out.println("duplicate value");
            }
        }
    }
    public Vertex insert(int value, Vertex p) throws duplicateValueException{
        if (p == null) {
            p = new Vertex(value);
        }
        else {
            if (p.key > value) {
                p.left = insert(value, p.left);
                if(height(p.left) - height(p.right ) == 2 ) {
                    if (p.left.key > value) {
                        p = LL(p);
                        turnCounter++;
                    } else {
                        p = LR(p);
                        turnCounter += 2;
                    }
                }
            }
            else if(p.key < value) {
                p.right = insert(value, p.right);
                if(height(p.right) - height(p.left) == 2 ) {
                    if (p.right.key < value) {
                        p = RR(p);
                        turnCounter++;
                    }
                    else {
                        p = RL(p);
                        turnCounter += 2;
                    }
                }
            }
            else throw new duplicateValueException();
        }
        p.height = max(height(p.left), height(p.right)) + 1;
        return p;
    }
}
