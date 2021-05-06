package lab05b;

import java.lang.Math;

public class Tabuleiro {

    Peca[][] tab = new Peca[8][8];

    Tabuleiro() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (i) {

                    //peças pretas em colunas ímpares
                    case 0:
                    case 2:
                        if (j % 2 == 1) {
                            tab[i][j] = new PecaPeao('p');
                        } else {
                            tab[i][j] = null;
                        }
                        break;

                    //peças pretas em colunas pares
                    case 1:
                        if (j % 2 == 0) {
                            tab[i][j] = new PecaPeao('p');
                        } else {
                            tab[i][j] = null;
                        }
                        break;

                    //sem peça
                    case 3:
                    case 4:
                        tab[i][j] = null;
                        break;

                    //peças brancas em colunas pares
                    case 5:
                    case 7:
                        if (j % 2 == 0) {
                            tab[i][j] = new PecaPeao('b');
                        } else {
                            tab[i][j] = null;
                        }
                        break;

                    //peças brancas em colunas ímpares
                    case 6:
                        if (j % 2 == 1) {
                            tab[i][j] = new PecaPeao('b');
                        } else {
                            tab[i][j] = null;
                        }
                        break;
                }
            }
        }


    }

    String imprimirTabuleiro() {

        String configuracao = "";

        for (int i = 0; i < 8; i++) {
            configuracao = configuracao + (8 - i);
            for (int j = 0; j < 8; j++) {
                if (tab[i][j] == null) {
                    configuracao = configuracao + "-";
                } else {
                    configuracao = configuracao + Character.toString(tab[i][j].cor);
                }
            }
            configuracao = configuracao + '\n';
        }

        configuracao = configuracao + " abcdefgh";
        return configuracao;
    }

    Peca[] obterCaminho(int lo, int co, int ld, int cd) {
        Peca[] caminho = new Peca[Math.abs(lo-ld)+1];
        int pos = 0, j = co;
        //se pra cima
        if (ld - lo <= -1) {

            //se direita
            if (cd - co >= 1)
                for (int i = lo; i >=  ld; i--) {
                        caminho[pos] = tab[i][j];
                        pos++;
                        j++;
                }
                //se esquerda

            else if (cd - co <= -1) {
                for (int i = lo; i >= ld; i--){
                        caminho[pos] = tab[i][j];
                        pos++;
                        j--;

                }
            }
        }
        //senão, pra baixo
        else if (ld - lo >= 1){

            //se direita
            if (cd - co >= 1){
                for(int i = lo; i <= ld; i++) {
                        caminho[pos] = tab[i][j];
                        pos++;
                        j++;

                }
            }

            //se esquerda
            else if (cd - co <= -1){
                for(int i = lo; i <= ld; i++) {
                        caminho[pos] = tab[i][j];
                        pos++;
                        j --;
                }
            }

        }

        return caminho;
    }

    String solicitaMovimento(String origem, String destino) {

        //pegar linhas e colunas f4:d4
        String letras = "abcdefg";
        int co = letras.indexOf(origem.charAt(0));
        int lo = 8 - (origem.charAt(1) - '0');
        int cd = letras.indexOf(destino.charAt(0));
        int  ld = 8 - (destino.charAt(1) - '0');

        if (tab[lo][co] != null ) {

            //garante que movimentação é em diagonal, não linha reta, e células envolvidas estão contidas no tabuleiro
            if (co != cd && lo != ld && 0<=cd && cd<= 7 && 0<=co && co<= 7 && 0<=ld && ld<= 7 && 0 <= lo && lo <= 7) {

                //obtém o caminho da diagonal andada
                Peca caminho[] = obterCaminho(lo, co, ld, cd);

                if (tab[lo][co].jogadaValida(lo, co, ld, cd, caminho)) {

                    //verifica se houve peça comida e remove-a caso sim
                    int[] tupla = new int[2];
                    tupla = tab[lo][co].removerComida(lo, co, ld, cd);

                    //se na posição houver peça, remover
                    if (tupla != null) {
                        tab[tupla[0]][tupla[1]] = null;
                    }

                    //atualizar posição de origem e destino

                    //branca na linha 0 ou preta na linha 7
                    if ((tab[lo][co].cor == 'b' && ld == 0) || (tab[lo][co].cor == 'p' && ld == 7)) {
                        if (tab[lo][co].cor == 'b') {
                            tab[ld][cd] = new PecaDama('B');
                        } else {
                            tab[ld][cd] = new PecaDama('P');
                        }

                    } else {
                        tab[ld][cd] = tab[lo][co];
                    }

                }
                //remove da origem
                tab[lo][co] = null;

                return imprimirTabuleiro();
            }
        }
            return ("Movimento invalido!");

    }
}

