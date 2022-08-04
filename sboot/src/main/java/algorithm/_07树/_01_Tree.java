package algorithm._07树;

import lombok.Data;

import java.util.Map;

@Data
public class _01_Tree {
    private String root;
    private _01_Tree left;
    private _01_Tree right;

    public  _01_Tree(){};
    public  _01_Tree(String root,_01_Tree left,_01_Tree right){
        this.root=root;
        this.left=left;
        this.right=right;

    };
    private static  _01_Tree  createatNode(){
        _01_Tree H = new _01_Tree("H",null,null);
        _01_Tree G = new _01_Tree("G",null,null);
        _01_Tree F = new _01_Tree("F",H,G);
        _01_Tree E = new _01_Tree("E",null,F);
        _01_Tree D = new _01_Tree("D",null,null);
        _01_Tree C = new _01_Tree("C",D,null);
        _01_Tree B = new _01_Tree("B",null,C);
        _01_Tree A = new _01_Tree("A",B,E);
        return  A;
    }

    public static void toQian(_01_Tree tree){
        System.out.print(tree.getRoot()+",");
        if(tree.left!=null){
            toQian(tree.left);
        }
        if(tree.right!=null){
            toQian(tree.right);
        }
    }

    public static void toleft(_01_Tree tree){
        if(tree.left!=null){
            toQian(tree.left);
        }
        System.out.print(tree.getRoot()+",");
        if(tree.right!=null){
            toQian(tree.right);
        }
    }

    public static void toRight(_01_Tree tree){
        if(tree.left!=null){
            toQian(tree.left);
        }
        if(tree.right!=null){
            toQian(tree.right);
        }
    }

    public static void main(String[] args) {
        _01_Tree tree = createatNode();
        //前序
        toQian(tree);
        System.out.println("");

        //中序
        toleft(tree);
        System.out.println("");
        //后序
        toRight(tree);
        System.out.println("");


    }

}


