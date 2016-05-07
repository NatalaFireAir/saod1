package com.Nikita;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Natalie on 17.04.2016.
 */
public class BinaryTreeDemo {

    public static void main(String args[]){
        Random rnd = new Random(System.currentTimeMillis());


        int[]A = new int[10];
        for (int i = 0; i < 10; i++) {
            A[i] = i;
            System.out.println(A[i] + "--" + i);
        }
        System.out.println(A.toString());

        try{
            PerfectlyBalancedTree pbt = new PerfectlyBalancedTree(A,0,9);
 //           pbt.root = pbt.createPBT(A,0,9);
            System.out.println("_______________________________");
            System.out.println("Check = " + pbt.checkSearchTree());
            pbt.readLeftToRight();
            System.out.println();
            System.out.println(pbt.root.key + " " + pbt.root.right.key);
            int steps = pbt.search(1);
            System.out.println(steps);
            RandomizedSearchTree rst = new RandomizedSearchTree();
            rst.addVertex(10);
            rst.addVertex(4);
            rst.addVertex(15);
            rst.addVertex(13);
            rst.addVertex(14);
            rst.addVertex(20);
            System.out.println("_______________________________");
            System.out.println("Check = " + rst.checkSearchTree());
            rst.readLeftToRight();
            steps = rst.search(15);
            System.out.println(steps);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
