package lab05b;

public class PecaDama extends Peca {

    PecaDama(char cor){
        super(cor);
    }

    public boolean jogadaValida(int lo, int co, int ld, int cd, Peca [] caminho){
        int tam = caminho.length;

        //verifica se destino não está vazio
        if(caminho[1] != null){
            return false;
        }
        else{
            for(int i = 1; i < tam - 1; i++){
                if(i == tam-2) {
                    //se existe uma penúltima peça que pode ter sido comida
                    if (caminho[i] != null) {
                        //se for de mesma cor, jogada inválida
                        if (caminho[0].cor == caminho[i].cor) {
                            return false;
                        }
                    }

                }
                //se qualquer casa intermediária contiver peça, jogada inválida
                else if(caminho[i] != null){
                    return false;
                }
            }
        }
        return true;

    }

    public int[] removerComida(int lo, int co, int ld, int cd) {
        int lin = 0, col = 0;


        //se moveu na diagonal

            //se pra cima
            if (ld - lo <= -2)
                lin = ld + 1;
                //senão, pra baixo
            else if (ld - lo >= 2)
                lin = ld - 1;

            //se direita
            if (cd - co >= 2)
                col = cd - 1;
                //se esquerda
            else if (cd - co <= -2)
                col = cd + 1;

        int[] tupla = {lin, col};
        return tupla;
    }


}
