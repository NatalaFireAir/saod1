package com.Nikita;

/**
 * Created by Natalie on 17.04.2016.
 */
public class BinaryTreeDemo {
    public static void main(String args[]){
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        v1.left = v2;
        v1.right = v3;
        v2.left = v4;
        v2.right = v5;
        v5.addLeft(new Vertex(8));
        Vertex.readLeftToRight(v1);
        System.out.println("Height = " + Vertex.findHeight(v1));
        System.out.println("Average height = " + Vertex.findAverageHeight(v1));
        System.out.println("Check Sum = " + Vertex.findCheckSum(v1));
    }
}
