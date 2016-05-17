package com.Nikita;


public class OptimalSearchTree extends SearchTree {
    int arrayLenght;
    Vertex[] Avertex;
    int[][] AR; //key matrix
    double[][] AW; //weight matrix
    double[][] AP; //w-height matrix
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
    public OptimalSearchTree() {
        arrayLenght = 7;
        Vertex v1 = new Vertex(1, 60);
        Vertex v2 = new Vertex(2, 30);
        Vertex v3 = new Vertex(3, 10);
        Avertex = new Vertex[7];
        Avertex[0] = new Vertex();
        Avertex[1] = v1;
        Avertex[2] = v2;
        Avertex[3] = v3;
        Avertex[4] = new Vertex(4,70);
        Avertex[5] = new Vertex(5,20);
        Avertex[6] = new Vertex(6,120);
/*        AW = new double[arrayLenght][arrayLenght];
        AP = new double[arrayLenght][arrayLenght];
        AR = new int[arrayLenght][arrayLenght];
        for (int i = 0; i < arrayLenght; i++) {
            for (int j = i; j < arrayLenght; j++) {
                AW[i][j] = 0;
            }
        }
        createAW();
        for (int i = 0; i < arrayLenght; i++) {
            for (int j = i; j < arrayLenght; j++) {
                System.out.print(AW[i][j] + " _ ");
            }
            System.out.println();
        }
        createAPAR();
        for (int i = 0; i < arrayLenght; i++) {
            for (int j = i; j < arrayLenght; j++) {
                System.out.print(AP[i][j] + "*" + AR[i][j] + " _ ");
            }
            System.out.println();
        }*/
        createOST_A2(0, arrayLenght - 1);
        readLeftToRight();
//        System.out.println(root.key+ "  " + root.right.key + "  " + root.right.right.key);
        System.out.println(findAvgWeightHeight(root));
    }
/*
    void createAW() {
        for (int i = 0; i < arrayLenght; i++) {
            for (int j = i + 1; j < arrayLenght; j++) {
                AW[i][j] = AW[i][j - 1] + Avertex[j].weight;
                // System.out.print(AW[i][j] + " _ ");
            }
            // System.out.println();
        }
        //   return AW;
    }

    void createAPAR() {
        for (int i = 0; i < arrayLenght - 1; i++) {
            for (int j = i + 1; j < arrayLenght; j++) {
                AP[i][j] = AW[i][j];
                AR[i][j] = i + 1;
                // System.out.print(AR[i][j] + "/ "+ AP[i][j] + "___");
            }
            // System.out.println();
        }
        for (int h = 2; h < arrayLenght; h++) {
            for (int i = 0; i < arrayLenght - h; i++) {
                int j = i + h;
                int m = AR[i][j - 1];
                double min = AP[i][m - 1] + AP[m][j];
                for (int k = m + 1; k < AR[i + 1][j]; k++) {
                    double temp = AP[i][k - 1] + AP[k][j];
                    if (temp < min) {
                        m = k;
                        min = temp;
                    }
                }
                AP[i][j] = min + AW[i][j];
                AR[i][j] = m;
                System.out.print(AP[i][j] + "*" + AR[i][j] + " _ ");
            }
            System.out.println();
        }
    }*/
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
            } catch (duplicateValueException e) {
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
            } catch (duplicateValueException e) {
                e.printStackTrace();
            }
            createOST_A2( l,index-1);
            createOST_A2(index+1,r);
        }
    }
    public void insert(Vertex v) throws duplicateValueException {
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
            else throw new duplicateValueException();
        }
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