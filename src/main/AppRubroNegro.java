package main;

import model.RubroNegra;

public class AppRubroNegro {

    public static void main(String[] args) {
       RubroNegra arn = new RubroNegra();
        
        
       arn.insercao(20);
       arn.insercao(15);
       arn.insercao(25);
       arn.insercao(12);
       arn.insercao(17);
       arn.insercao(24);
       arn.insercao(30);
       arn.insercao(10);
       arn.insercao(14);
       arn.insercao(13);
       
       arn.imprimir();
       
        System.out.println(arn.busca(13));
       
      
        
       
       
       
    }
    
}
