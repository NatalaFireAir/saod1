package com.Nikita;

public class OptimalSearchTree extends SearchTree {
    int arrayLenght;
    Vertex[] Avertex;
    public OptimalSearchTree(int[] A, double[] B) {
        arrayLenght = A.length;
        root = null;
        Avertex = new Vertex[arrayLenght];
        for (int i = 0; i < arrayLenght; i++) {
            Avertex[i] = new Vertex(A[i],B[i]);
        }
    }
    private void quickSortFunction(Vertex A[], int left, int right) {
        Vertex mid;
        int i=left;
        int j=right;
        mid = A[(left + right) / 2];
        while (i <= j)  {
            while (mid.key > A[i].key) i++;
            while (A[j].key > mid.key) j--;
            if (i <= j) {
                Vertex temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j)  quickSortFunction(A, left, j);
        if (i < right) quickSortFunction(A, i, right);
    }
    private void insert(Vertex v) throws DuplicateValueException {
        if (root == null) {
            root = v;
            return;
        }
        Vertex p = root;
        while (p != null) {
            if (v.key < p.key) {
                if (p.left == null) {
                    p.left = v;
                    return;
                }
                else {
                    p = p.left;
                }
            }
            else if (v.key > p.key) {
                if (p.right == null) {
                    p.right = v;
                    return;
                } else {
                    p = p.right;
                }
            }
            else throw new DuplicateValueException();
        }
    }
    public void createOST_A1() {
        for (int i = 0; i < arrayLenght; i++) {
            Avertex[i].use = false;
        }
        for (int i = 0; i < arrayLenght; i++) {
            double max = 0;
            int index = 0;
            for (int j = 1; j < arrayLenght; j++) {
                if (Avertex[j].weight > max && Avertex[j].use == false) {
                    max = Avertex[j].weight;
                    index = j;
                }
            }
            Avertex[index].use = true;
            try {
                insert(Avertex[index]);
            } catch (DuplicateValueException e) {
                e.printStackTrace();
            }
        }
    }
    public void createOST_A2() {
        quickSortFunction(Avertex, 0, Avertex.length - 1);
        createOST_A2(0, arrayLenght - 1);
    }
    public void createOST_A2(int l, int r) {
        double treeWeight = 0;
        double sum = 0;
        if (l <= r) {
            for (int i = l; i <= r; i++) {
                treeWeight += Avertex[i].weight;
            }
            int index = 0;
            for (int i = l; i <= r; i++) {
                if (sum < (treeWeight/2) && (sum + Avertex[i].weight) >= treeWeight/2) {
                    index = i;
                    break;
                } else {
                    sum += Avertex[i].weight;
                }
            }
            try {
                insert(Avertex[index]);
            } catch (DuplicateValueException e) {
                e.printStackTrace();
            }
            createOST_A2( l,index-1);
            createOST_A2(index+1,r);
        }
    }
    private static double max(double a, double b){
        if (a >= b) return a;
        return b;
    }
    public double findAvgWeightHeight(Vertex vertex) {
            double h;
            if (vertex == null) h = 0;
            else h = (1 + max(findAvgWeightHeight(vertex.left), findAvgWeightHeight(vertex.right)))*vertex.weight;
            return h;
    }
    public double findAvgWeightHeight() {
        return findAvgWeightHeight(root);
    }
}