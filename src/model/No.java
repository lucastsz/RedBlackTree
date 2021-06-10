package model;

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

    @Override
    public String toString() {
        if (valor == 0 && cor == 0){
            return "NÃO EXISTE ESSE NÓ";
        }
        return "No " + valor + " (" + Cor() + ')';
    }
    
    
}
