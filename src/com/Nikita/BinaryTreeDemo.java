package com.Nikita;

import sun.security.provider.certpath.Vertex;

import java.util.Random;

public class BinaryTreeDemo {
    int []nValues = {10, 50, 100, 200, 400};
    Random rnd = new Random(System.currentTimeMillis());
    int[] createRandArray(int n) {
        int[] array = new int[n];
        boolean isDuplicate;
        for (int i = 0; i < n; i++) {
            while(true) {
                isDuplicate = false;
                array[i] = rnd.nextInt(10000);
                for (int j = 0; j < i; j++) {
                    if (array[i] == array[j]){
                        isDuplicate = true;
                    }
                }
                if (isDuplicate == false) break;
            }
        }
        return array;
    }
    public static void main(String args[]){
        BinaryTreeDemo demo = new BinaryTreeDemo();
        SimpleSearchTree rst = new SimpleSearchTree();
        AVLTree avlt = new AVLTree();
        avlt.root = avlt.insert(10, avlt.root);
        avlt.readLeftToRight();
        avlt.root = avlt.insert(1, avlt.root);
        System.out.println("1");

        avlt.root = avlt.insert(20, avlt.root);
        System.out.println("20");
        avlt.root = avlt.insert(15, avlt.root);
        System.out.println("20");
        avlt.root = avlt.insert(16, avlt.root);
        System.out.println("20");
        avlt.readLeftToRight();
        System.out.println("root" + avlt.root.key + "   left: " + avlt.root.left.key + "    right: " + avlt.root.right.key + "    rightleft " + avlt.root.right.left.key);



        /*try {
            rst.addVertex(10);
            rst.addVertex(4);
            rst.addVertex(15);
            rst.addVertex(13);
            rst.addVertex(14);
            rst.addVertex(20);
            rst.addVertex(2);
            rst.addVertex(9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("_______________________________");
        rst.readLeftToRight();
        System.out.println("root" + rst.root.key + "   left: " + rst.root.left.key + "    right: " + rst.root.right.key);


        rst.LLturn(rst.root);
        System.out.println();
        rst.readLeftToRight();
        System.out.println("root" + rst.root.key + "   left: " + rst.root.left.key + "    right: " + rst.root.right.key);
        *//*for (int n:demo.nValues) {
            int[] A = demo.createRandArray(n);
            System.out.println();
            for (int a:A){
                System.out.print(a + ", ");
            }
            System.out.println();
            int[] B = A.clone();
            try {
                System.out.println(n + " elements");
                System.out.println();
                System.out.println("Perfectly Balanced Tree");
                PerfectlyBalancedTree pbt = new PerfectlyBalancedTree(A);
                System.out.println("Check = " + pbt.checkSearchTree());
                pbt.readLeftToRight();
                System.out.println();
                System.out.println("root: " + pbt.root.key );
                System.out.println("average height: " + pbt.findAverageHeight());
                int val = A[demo.rnd.nextInt(A.length)];
                try {
                    int steps = pbt.search(val);
                    System.out.println("founded in " + steps + " steps");
                } catch (valueNotFoundException v) {
                    System.out.println("not found");
                }
                System.out.println();
                System.out.println("Simple Search Tree");
                SimpleSearchTree sst = new SimpleSearchTree(B);
                System.out.println("Check = " + sst.checkSearchTree());
                sst.readLeftToRight();
                System.out.println();
                System.out.println("root: " + sst.root.key );
                System.out.println("average height: " + sst.findAverageHeight());
                val = B[demo.rnd.nextInt(B.length)];
                try {
                    int steps = sst.search(val);
                    System.out.println("founded in " + steps + " steps");
                } catch (valueNotFoundException v) {
                    System.out.println("not found");
                }
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
