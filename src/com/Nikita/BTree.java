package com.Nikita;

public class BTree  extends SearchTree{
    boolean VR, HR;
    public BTree() { root = null; }
    public BTree(int[] A) {
        for(int a:A) {
            try {
                root = insert(a, root);
            } catch (duplicateValueException d) {
                System.out.println("duplicate value");
            }
        }
    }
    public Vertex insert(int value, Vertex p) throws duplicateValueException {
        VR = HR = true;
        if (p == null) {
            p = new Vertex(value);
            return  p;
        } else {
            if (p.key > value) {
                p.left = insert(value, p.left);
                if (VR == true) {
                    if(p.balance == 0) {
                        Vertex q = p.left;
                        p.left = q.right;
                        q.right = p;
                        p = q;
                        q.balance = 1;
                        VR = false;
                        HR = true;
                    } else {
                        p.balance = 0;
                        HR = true;
                    }
                }else {
                    HR = false;
                }
            } else if(p.key < value) {
                p.right = insert(value, p.right);
                if (VR == true) {
                    p.balance = 1;
                    VR = false;
                    HR = true;
                } else if (HR == true) {
                    if(p.balance > 0) {
                        Vertex q = p.right;
                        p.right = q.left;
                        p.balance = 0;
                        q.balance = 0;
                        q.left = p;
                        p = q;
                        VR = true;
                        HR = false;

                    } else {
                        HR = false;
                    }
                }
            } else throw new duplicateValueException();
        }
        return p;
    }
}
