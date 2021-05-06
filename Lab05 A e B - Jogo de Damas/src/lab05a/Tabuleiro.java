package lab05a;

import java.lang.Math;

public class Tabuleiro {

    PecaPeao[][] tabPeao = new PecaPeao[8][8];
    PecaDama[][] tabDama = new PecaDama[8][8];

    Tabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabDama[i][j] = null;
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (i) {

                    //peças pretas em colunas ímpares
                    case 0:
                    case 2:
                        if (j % 2 == 1) {
                            tabPeao[i][j] = new PecaPeao('p');
                        } else {
                            tabPeao[i][j] = null;
                        }
                        break;

                    //peças pretas em colunas pares
                    case 1:
                        if (j % 2 == 0) {
                            tabPeao[i][j] = new PecaPeao('p');
                        } else {
                            tabPeao[i][j] = null;
                        }
                        break;

                    //sem peça
                    case 3:
                    case 4:
                        tabPeao[i][j] = null;
                        break;

                    //peças brancas em colunas pares
                    case 5:
                    case 7:
                        if (j % 2 == 0) {
                            tabPeao[i][j] = new PecaPeao('b');
                        } else {
                            tabPeao[i][j] = null;
                        }
                        break;

                    //peças brancas em colunas ímpares
                    case 6:
                        if (j % 2 == 1) {
                            tabPeao[i][j] = new PecaPeao('b');
                        } else {
                            tabPeao[i][j] = null;
                        }
                        break;
                }
            }
        }


    }

    String apresentarTabuleiro() {

        String configuracao = "";

        for (int i = 0; i < 8; i++) {
            configuracao = configuracao + (8 - i);
            for (int j = 0; j < 8; j++) {
                if (tabPeao[i][j] == null && tabDama[i][j] == null) {
                    configuracao = configuracao + "-";
                } else if (tabPeao[i][j] != null) {
                    configuracao = configuracao + Character.toString(tabPeao[i][j].cor);
                } else {
                    configuracao = configuracao + Character.toString(tabDama[i][j].cor);
                }
            }
            configuracao = configuracao + '\n';
        }

        configuracao = configuracao + " abcdefgh";
        return configuracao;
    }

    void removerComidaPeao(int lo, int co, int ld, int cd){

        int linhaComida, colunaComida;
        if(Math.abs(lo - ld) == 2){
            linhaComida = (lo + ld)/2;
            colunaComida = (lo + ld)/2;

            //comida é peão ou dama
            if(tabPeao[linhaComida][colunaComida] != null){
                tabPeao[linhaComida][colunaComida] = null;
            }

            else{
                tabDama[linhaComida][colunaComida] = null;
            }
        }
    }

    void removerComidaDama(int lo, int co, int ld, int cd) {

        int lin = 0, col = 0;

        //se moveu pra direita ou esquerda
        if (lo == ld) {
            lin = ld;
            //se moveu para direita
            if (cd - co >= 2) {
                col = cd - 1;
            }
            //se moveu para a esquerda
            else if (cd - co <= -2) {
                col = cd + 1;
            }
        }
        //se moveu pra frente ou pra trás
        else if (co == cd) {
            col = cd;
            //se moveu para baixo
            if (ld - lo >= 2) {
                lin = ld - 1;
            }
            //se moveu para cima
            else if (ld - lo <= -2) {
                lin = ld + 1;
            }
        }
        //se moveu na diagonal
        else {
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

            //se na posição houver peça, remover
            if (tabPeao[lin][col] != null) {
                tabPeao[lin][col] = null;
            } else if (tabDama[lin][col] != null) {
                tabDama[lin][col] = null;
            }
        }
    }

    String executarJogada(String origem, String destino) {

        //pegar linhas e colunas f4:d4
        String letras = "abcdefg";
        int co = letras.indexOf(origem.charAt(0));
        int lo = (origem.charAt(1) - '0') - 1;
        int cd = letras.indexOf(destino.charAt(0));
        int ld = (destino.charAt(1) - '0') - 1;

        if (tabPeao[lo][co] != null) {
            if (tabPeao[lo][co].jogadaValidaPeao(lo, co, ld, cd)) {

                //verifica se houve peça comida e remove-a caso sim
                removerComidaPeao(lo, co, ld, cd);

                //atualizar posição destino
                //branca na linha 0 ou preta na linha 7
                if ((tabPeao[ld][cd].cor == 'b' && lo == 0) || (tabPeao[ld][cd].cor == 'p' && lo == 7)) {
                    if (tabPeao[lo][co].cor == 'b') {
                        tabDama[ld][cd] = new PecaDama('B');
                    } else {
                        tabDama[ld][cd] = new PecaDama('P');
                    }

                } else {
                    tabPeao[ld][cd] = new PecaPeao(tabPeao[lo][co].cor);
                }
                tabPeao[lo][co] = null;
            } else {
                return ("Jogada inválida\n");
            }
        }
        else if (tabDama[lo][co] != null) {
            if (tabDama[lo][co].jogadaValidaDama(lo, co, ld, cd)) {

                //verifica se houve peça comida e remove-a caso sim

                removerComidaDama(lo, co, ld, cd);

                tabDama[ld][cd] = new PecaDama(tabDama[lo][co].cor);
                tabDama[lo][co] = null;

            }
            else{
                return ("Jogada inválida\n");
            }
        }
        else {
                return ("Jogada inválida\n");
        }

            return apresentarTabuleiro();
        }

}
