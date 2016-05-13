package com.Nikita;

import java.util.Random;

/**
 * �����, ��������������� ��������� ������ - �������� ������, � �������� �������� � ������ ����������
 */
public class BinaryTreeDemo {
    public static void main(String args[]){
        Random rnd = new Random(System.currentTimeMillis());
        Vertex[] vertex = new Vertex[6];
        /**
         * ������������� ������ ���������� �������
         */
        for (int i = 0; i < 6; i++) {
            vertex[i] = new Vertex(rnd.nextInt(20));
            System.out.print(vertex[i].key + " ");
        }
        System.out.println();
        /**
         * ���������� ������
         */
        Vertex root = vertex[0];
        vertex[0].right = vertex[1];
        vertex[1].left = vertex[2];
        vertex[1].right = vertex[3];
        vertex[3].left = vertex[4];
        vertex[3].right = vertex[5];
        /**
         * ������ ������ ����� ������� � ���������� ��� �������������: ������, ������� ������, ����������� �����
         */
        Vertex.readLeftToRight(root);
        System.out.println();
        System.out.println("Height = " + Vertex.findHeight(root));
        System.out.println("Average height = " + Vertex.findAverageHeight(root));
        System.out.println("Check Sum = " + Vertex.findCheckSum(root));
    }
}
