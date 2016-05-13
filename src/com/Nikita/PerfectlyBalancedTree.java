package com.Nikita;
/**
 * Класс - идеально сбалансированное дерево поиска(ИСДП)
 */
public class PerfectlyBalancedTree extends SearchTree{
    public PerfectlyBalancedTree(int A[]) {
            quickSortFunction(A, 0, A.length - 1);
            root = createPBT(A, 0, A.length - 1);
    }
    /**
     * ИСДП создается на основе упорядоченного массива случайных неповторяющихся чисел.
     * Для упорядочивания используется алгоритм быстрой сортировки
     */
    private void quickSortFunction(int A[], int left, int right) {
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
    
    public Vertex createPBT(int A[], int l, int r) {
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
