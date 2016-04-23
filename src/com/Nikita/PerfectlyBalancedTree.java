package com.Nikita;

public class PerfectlyBalancedTree {
    Vertex root;
    public PerfectlyBalancedTree(Vertex root) {
        this.root = root;
    }
    public PerfectlyBalancedTree(int key, int data) {
        root = new Vertex(key, data);
    }
    public void addVertex(int key, int data) throws myTreeException {

    }
    public static Vertex createPBT(int A[], int l, int r) throws myTreeException {
        if (l > r) return null;
        else {
            int m = (A[l] + A[r])/2;
            Vertex p = new Vertex();
            p.key = A[m];
            p.left = createPBT(A, l, m-1);
            p.right = createPBT(A, m+1, r);
            return p;
        }
    }
    public Vertex search(int x) throws valueNotFoundException{
        Vertex pointer = root;
        while(pointer != null) {
            if (pointer.key < x) pointer = pointer.right;
            else if (pointer.key > x) pointer = pointer.left;
            else return pointer;
        }
        throw new valueNotFoundException();
    }



}
