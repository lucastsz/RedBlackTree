package RedBlackTree;

public class No {
    int valor;
    No pai;
    No esquerda;
    No direita;
    int cor;
    
    public String Cor(){
        if(cor == 0){
            return "PRETO";
        } else if(cor == 1){
        return "VERMELHO";
        }
        return "INVÁLIDO";
    }
}
