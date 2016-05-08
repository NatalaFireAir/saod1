package com.Nikita;

public class SearchTree {
  Vertex root;
    class Vertex {
        int balance;
        int height;
        int key;
        Vertex left;
        Vertex right;
        Vertex(){
            balance = 0;
            key = 0;
            left = null;
            right = null;
            height = 0;
        }
        Vertex(int key){
            balance = 0;
            this.key = key;
            this.left = null;
            this.right = null;
            height = 0;
        }
        Vertex(int key, Vertex left, Vertex right){
            height = 0;
            balance = 0;
            this.key = key;
            this.left = left;
            this.right = right;
        }
        Vertex(Vertex v){
           root = v;
        }
    }
    public SearchTree() {
        root = null;
    }
    private int height(Vertex p) {
        return p == null ? -1 : p.height;
    }
    public Vertex RR(Vertex p) {
        Vertex q = p.right;
  //      q.balance = 0;
 //       p.balance = 0;
        p.right = q.left;
        q.left = p;
        p.height = max(height(p.left), height(p.right)) + 1;
        q.height = max(height(q.right ), p.height) + 1;

        return q;
    }
    public Vertex LL(Vertex p) {
        Vertex q = p.left;
//        q.balance = 0;
//        p.balance = 0;
        p.left = q.right;
        q.right = p;
        p.height = max(height(p.left), height(p.right)) + 1;
        q.height = max(height(q.left), q.height) + 1;
        return q;
    }
    public Vertex LR(Vertex p)  {
        p.left = RR(p.left);
        return LL(p);
        /*Vertex q = p.left;
        Vertex r = q.right;
        if(r.balance < 0) p.balance = 1;
        else p.balance = 0;
        if(r.balance > 0) q.balance = -1;
        else q.balance = 0;
        r.balance = 0;
        p.left = r.right;
        q.right = r.left;
        r.left = q;
        r.right = p;
        p = r;
        return q;*/
    }
    public Vertex RL(Vertex p)  {
        p.right = LL(p.right);
        return RR(p);
        /*Vertex q = p.right;
        Vertex r = q.left;
        if(r.balance > 0) p.balance = -1;
        else p.balance = 0;
        if(r.balance < 0) q.balance = 1;
        else q.balance = 0;
        r.balance = 0;
        p.right = r.left;
        q.left = r.right;
        r.left = p;
        r.right = q;
        p = r;
        return q;*/
    }
    public void LLturn(Vertex v) {
        Vertex b = v;
        b = LL(b);
    }
    public void RRturn(Vertex v) {
        Vertex m = v;
    }
    public Vertex insert(int value, Vertex p) {
        boolean rise = false;
        if (p == null) {
            p = new Vertex(value);
            rise = true;
        }
        else {
            if (p.key > value) {
                p.left = insert(value, p.left);
                if( height(p.left) - height(p.right ) == 2 ) {
                    if (p.left.key > value)
                        p = LL(p);
                    else
                        p = LR(p);
                }
                /*if (rise == true) { //left branch risen
                    if (p.balance > 0) {
                        p.balance = 0;
                        rise = false;

                    } else if (p.balance == 0) p.balance = -1;
                    else {
                        if (p.left.balance < 0) p = LL(p);
                        else p = LR(p);
                    }
                    rise = false;
                }*/
            }
            else if(p.key < value) {
                p.right = insert(value, p.right);
                if( height(p.right) - height(p.left) == 2 ) {
                    if (p.right.key < value)
                        p = RR(p);
                    else
                        p = RL(p);
                }
                /*if (rise == true) { //left branch risen
                    if (p.balance > 0) {
                        p.balance = 0;
                        rise = false;
                    } else if (p.balance == 0) p.balance = -1;
                    else {
                        if (p.right.balance < 0) p = RR(p);
                        else p = RL(p);
                    }
                    rise = false;
                }*/
            }
            else System.out.println("!!!!blyaaaa");
        }
        p.height = max(height(p.left), height(p.right)) + 1;
        return p;
    }
    private static int max(int a, int b){
        if (a >= b) return a;
        return b;
    }
    private boolean checkSearchTree(Vertex vertex) {
        if (vertex != null && (vertex.left != null && (vertex.key <= vertex.left.key || (checkSearchTree(vertex.left) != true))
                || (vertex.right != null && (vertex.key >= vertex.right.key || checkSearchTree(vertex.right) != true)))) {
            return false;
        }
        return true;
    }
    private void readLeftToRight(Vertex vertex) {
        if (vertex != null) {
            readLeftToRight(vertex.left);
            System.out.print(vertex.key + " ");
            readLeftToRight(vertex.right);
        }
    }
    private int findHeight(Vertex vertex) {
        int h;
        if (vertex == null) h = 0;
        else h = 1 + max(findHeight(vertex.left), findHeight(vertex.right));
        return h;
    }
    private int findSize(Vertex vertex) {
        int size = 0;
        if (vertex != null) size = 1 + findSize(vertex.left) + findSize(vertex.right);
        return size;
    }
    private int pathsLengthSum(Vertex vertex, int level) {
        int pathsSum;
        if (vertex == null)pathsSum = 0;
        else pathsSum = level + pathsLengthSum(vertex.left, level + 1) + pathsLengthSum(vertex.right, level + 1);
        return pathsSum;
    }
    private float findAverageHeight(Vertex vertex) {
        return (float)pathsLengthSum(vertex, 1)/findSize(vertex);
    }
    private int findCheckSum(Vertex vertex) {
        int checkSum;
        if (vertex == null) checkSum = 0;
        else checkSum = vertex.key + findCheckSum(vertex.left) + findCheckSum(vertex.right);
        return checkSum;
    }
    public int search(int x) throws valueNotFoundException{
        int steps = 0;
        Vertex pointer = root;
        while(pointer != null) {
            steps ++;
            if (pointer.key < x) pointer = pointer.right;
            else if (pointer.key > x) pointer = pointer.left;
            else return steps;
        }
        throw new valueNotFoundException();
    }
    public boolean checkSearchTree(){
        return checkSearchTree(root);
    }
    public void readLeftToRight() { readLeftToRight(root); }
    public int findHeight() { return findHeight(root); }
    public int findSize() { return findSize(root); }
    public float findAverageHeight() { return findAverageHeight(root); }
    public int findCheckSum() { return findCheckSum(root); }
}
