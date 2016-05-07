package com.Nikita;

public class PerfectlyBalancedTree extends SearchTree{
    public PerfectlyBalancedTree(int A[]) {
        try{
            quickSortFunction(A, 0, A.length - 1);
            root = createPBT(A, 0, A.length - 1);
        } catch (duplicateValueException d) {
            System.out.println("DUPLICATE VALUE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void quickSortFunction(int A[], int left, int right) throws duplicateValueException{
        int mid;
        int i=left;
        int j=right;
        mid = A[(left + right) / 2];
        while (i <= j)  {
            while (mid > A[i]) i++;
            while (A[j] > mid) j--;
            if (i <= j) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j)  quickSortFunction(A, left, j);
        if (i < right) quickSortFunction(A, i, right);
    }
    public Vertex createPBT(int A[], int l, int r) throws myTreeException {
        if (l > r) return null;
        else {
            int m = (l + r)/2;
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
