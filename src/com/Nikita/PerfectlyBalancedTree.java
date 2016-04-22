package com.Nikita;

public class PerfectlyBalancedTree {
    Vertex root;
    public int search(int x) {
        Vertex pointer = root;
        while(pointer != null) {
            if (pointer.key < x) pointer = pointer.right;
            else if (pointer.key > x) pointer = pointer.left;
            else return pointer.data;
        }
return -1;
    }
}
