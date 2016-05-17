package com.Nikita;

/**
 * Класс - АВЛ-Дерево.
 * Дерево поиска называется сбалансированным по высоте, или АВЛ – деревом,
 * если для каждой его вершины высоты левого и правого поддеревьев отличаются не более чем на 1.
 */
public class AVLTree extends SearchTree {
    static int turnCounter = 0;
    boolean rise = false;
    public AVLTree(int[] A) {
        for (int a : A) {
            try {
                root = insert(a, root);
            } catch (DuplicateValueException d) {
                System.out.println("duplicate value");
            }
        }
    }

    /**
     * RR-Поворот
     */
    public Vertex RR(Vertex p) {
        turnCounter++;
        Vertex q = p.right;
        q.balance = 0;
        p.balance = 0;
        p.right = q.left;
        q.left = p;
        return q;
    }

    /**
     * LL-Поворот
     */
    public Vertex LL(Vertex p) {
        turnCounter++;
        Vertex q = p.left;
        q.balance = 0;
        p.balance = 0;
        p.left = q.right;
        q.right = p;
        return q;
    }

    /**
     * LR-Поворот
     */
    public Vertex LR(Vertex p) {
        turnCounter += 2;
        Vertex q = p.left;
        Vertex r = q.right;
        if (r.balance < 0) p.balance = 1;
        else p.balance = 0;
        if (r.balance > 0) q.balance = -1;
        else q.balance = 0;
        r.balance = 0;
        p.left = r.right;
        q.right = r.left;
        r.left = q;
        r.right = p;
        p = r;
        return p;
    }

    /**
     * RL-Поворот
     */
    public Vertex RL(Vertex p) {
        turnCounter += 2;
        Vertex q = p.right;
        Vertex r = q.left;
        if (r.balance > 0) p.balance = -1;
        else p.balance = 0;
        if (r.balance < 0) q.balance = 1;
        else q.balance = 0;
        r.balance = 0;
        p.right = r.left;
        q.left = r.right;
        r.left = p;
        r.right = q;
        p = r;
        return p;
    }

    /**
     * Процедура вставки вершины в АВЛ-дерево
     */
    public Vertex insert(int value, Vertex p) throws DuplicateValueException {
        if (p == null) {
            p = new Vertex(value);
            rise = true;
        } else {
            if (p.key > value) {
                p.left = insert(value, p.left);
                if (rise == true) { //рост левой ветви
                    if (p.balance > 0) {
                        p.balance = 0;
                        rise = false;
                    } else if (p.balance == 0) p.balance = -1;
                    else {
                        if (p.left.balance < 0) p = LL(p);
                        else p = LR(p);
                        rise = false;
                    }
                }
            } else if (p.key < value) {
                p.right = insert(value, p.right);
                if (rise == true) { //росто правой ветви
                    if (p.balance < 0) {
                        p.balance = 0;
                        rise = false;
                    } else if (p.balance == 0) p.balance += 1;
                    else {
                        if (p.right.balance > 0) p = RR(p);
                        else p = RL(p);
                        rise = false;
                    }
                }
            } else throw new DuplicateValueException(); // Вершина с таким значением уже есть
        }
        return p;
    }
}