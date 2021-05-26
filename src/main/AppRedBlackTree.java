package main;

import model.RedBlackTree;

public class AppRedBlackTree {

    public static void main(String[] args) {
       RedBlackTree arn = new RedBlackTree();
        
        
       arn.insercao(41);
       arn.insercao(38);
       arn.insercao(31);
       arn.insercao(12);
       arn.insercao(19);
       arn.insercao(8);
       
       arn.imprimir();
       
       
       
    }
    
}
