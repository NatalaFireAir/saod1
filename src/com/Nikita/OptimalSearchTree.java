package com.Nikita;

/**
 * Класс - херево оптимального поиска(ДОП). Структура дерева подразумевает, что
 * положение вершин определяется не только ключом, но и весом (величиной, характеризующей
 * вероятность обращения к вершине). Вершины с большим весом должны располагаться
 * как можно ближе к корню.
 */
public class OptimalSearchTree extends SearchTree {
    int arrayLength;
    Vertex[] aVertex;

    public OptimalSearchTree(int[] A, double[] B) {
        arrayLength = A.length;
        aVertex = new Vertex[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            aVertex[i] = new Vertex(A[i], B[i]);
        }
    }

    /**
     * Функция сортировки методом Хоара
     */
    private void quickSortFunction(Vertex A[], int left, int right) {
        Vertex mid;
        int i = left;
        int j = right;
        mid = A[(left + right) / 2];
        while (i <= j) {
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
        if (left < j) quickSortFunction(A, left, j);
        if (i < right) quickSortFunction(A, i, right);
    }

    /**
     * Вставка вершины в дерево осуществляет по обычному алгоритму, применяемую в случайном
     * дереве поиска
     */
    private void insert(Vertex v) throws duplicateValueException {
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
                } else {
                    p = p.left;
                }
            } else if (v.key > p.key) {
                if (p.right == null) {
                    p.right = v;
                    return;
                } else {
                    p = p.right;
                }
            } else throw new duplicateValueException();
        }
    }

    /**
     * Первый приближенный алгоритм(А1) построения ДОП. Предлагает в качестве корня использовать вершину с наибольшим весом.
     * Затем среди оставшихся вершин снова выбирается вершина с наибольшим весом
     * и помещается в левое или правое поддерево в зависимости от ее значения, и т.д.
     */
    public void createOST_A1() {
        for (int i = 0; i < arrayLength; i++) {
            aVertex[i].use = false;
        }
        for (int i = 0; i < arrayLength; i++) {
            double max = 0;
            int index = 0;
            for (int j = 1; j < arrayLength; j++) {
                if (aVertex[j].weight > max && aVertex[j].use == false) {
                    max = aVertex[j].weight;
                    index = j;
                }
            }
            aVertex[index].use = true;
            try {
                insert(aVertex[index]);
            } catch (duplicateValueException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Второй алгоритм (А2).
     * В качестве корня выбирается такая вершина, что разность весов левого и правого поддеревьев была минимальна.
     */
    public void createOST_A2(int l, int r) {
        double treeWeight = 0;
        double sum = 0;
        if (l <= r) {
            for (int i = l; i <= r; i++) {
                treeWeight += aVertex[i].weight;
            }
            int index = 0;
            for (int i = l; i <= r; i++) {
                //определение положения, в котором разность весов левой и правой части массива минимальна
                if (sum < (treeWeight / 2) && (sum + aVertex[i].weight) >= treeWeight / 2) {
                    index = i;
                    break;
                } else {
                    sum += aVertex[i].weight;
                }
            }
            try {
                insert(aVertex[index]);
            } catch (duplicateValueException e) {
                e.printStackTrace();
            }
            //рекурсивные вызовы метода для левой и правой части массива относительно index
            createOST_A2(l, index - 1);
            createOST_A2(index + 1, r);
        }
    }

    /**
     * Второй алгоритм (А2) использует предварительно упорядоченный набор вершин.
     */
    public void createOST_A2() {
        quickSortFunction(aVertex, 0, aVertex.length - 1);
        createOST_A2(0, arrayLength - 1);
    }

    private static double max(double a, double b) {
        if (a >= b) return a;
        return b;
    }

    /**
     * Метод поиска средневзвешенной высоты дерева
     */
    public double findAvgWeightHeight(Vertex vertex) {
        double h;
        if (vertex == null) h = 0;
        else h = (1 + max(findAvgWeightHeight(vertex.left), findAvgWeightHeight(vertex.right))) * vertex.weight;
        return h;
    }

    public double findAvgWeightHeight() {
        return findAvgWeightHeight(root);
    }
}