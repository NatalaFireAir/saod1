package com.Nikita;

/**
 * Класс - бинарное В-Дерево.
 */
public class BTree extends SearchTree {
    // логичесике переменные показывающие выросло ли дерево вертикально и горизонтально
    boolean VR, HR;

    public BTree() {
        root = null;
    }

    public BTree(int[] A) {
        for (int a : A) {
            try {
                root = insert(a, root);
            } catch (duplicateValueException d) {
                System.out.println("duplicate value");
            }
        }
    }

    /**
     * Вставка вершины в бинарное В-Дерево.
     */
    public Vertex insert(int value, Vertex p) throws duplicateValueException {
        VR = HR = true;
        if (p == null) {
            p = new Vertex(value);
            return p;
        } else {
            if (p.key > value) {
                p.left = insert(value, p.left);
                if (VR) {
                    if (p.balance == 0) {
                        //образование "страницы", горизонтальный рост
                        //3-й случай(по методичке)
                        Vertex q = p.left;
                        p.left = q.right;
                        q.right = p;
                        p = q;
                        q.balance = 1;
                        VR = false;
                        HR = true;
                    } else {
                        //4-й случай(по методичке)
                        p.balance = 0;
                        HR = true;
                    }
                } else {
                    //вертикальный рост
                    HR = false;
                }
            } else if (p.key < value) {
                p.right = insert(value, p.right);
                if (VR) {
                    //образование "страницы", горизонтальный рост
                    //1-й случай(по методичке)
                    p.balance = 1;
                    VR = false;
                    HR = true;
                } else if (HR) {
                    if (p.balance > 0) {
                        //2-й случай(по методичке)
                        //вертикальный рост
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
