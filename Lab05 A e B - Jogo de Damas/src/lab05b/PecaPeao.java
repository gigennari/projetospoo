package lab05b;

public class PecaPeao extends Peca {

    PecaPeao(char cor) {
        super(cor);
    }

    public boolean jogadaValida(int lo, int co, int ld, int cd, Peca[] caminho) {


        //validacao do sentido da movimentação
        if((caminho[0].cor == 'b' && lo > ld) || (caminho[0].cor == 'p' && lo < ld)){
            //andou 1 na diagonal (vetor tem origem e destino apenas)
            if(caminho.length == 2){
                //se o destino não estiver nulo
                if(caminho[1]!= null) {
                     return false;
                }
            }
            //andou 2 na diagonal (vetor tem origem, peça comida e destino)
            else if(caminho.length == 3){
                //posição de destino não vazia ou ausência de peça do advérsário para cmer invalida jogada
                if(caminho[2] != null || caminho[1] == null || caminho[1].cor == caminho[0].cor){
                    return false;
                }
            }
            else if (caminho.length > 3){
                return false;
            }
        }
        return true;
    }

    public int[] removerComida(int lo, int co, int ld, int cd) {
        int lin, col;
        if (Math.abs(lo - ld) == 2) {
            lin = (lo + ld) / 2;
            col = (lo + ld) / 2;

            int[] tupla = {lin, col};
            return tupla;
        }

        return null;
    }
}