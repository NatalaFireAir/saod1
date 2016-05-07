package com.Nikita;

public class PerfectlyBalancedTree extends SearchTree{
    public PerfectlyBalancedTree(int A[], int l, int r) {
        try{
            root = createPBT(A, l, r);
            System.out.println(root.key + " root, " + root.left.key + " left");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Vertex createPBT(int A[], int l, int r) throws myTreeException {
        if (l > r) return null;
        else {
            int m = (A[l] + A[r])/2;
            Vertex p = new Vertex();
            p.key = A[m];
            Vertex newLeft = createPBT(A, l, m-1);
            p.left = newLeft;
            Vertex newRight = createPBT(A, m+1, r);
            p.right = newRight;
            return p;
        }

    }

}
