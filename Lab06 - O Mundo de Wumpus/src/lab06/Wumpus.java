package lab06;

public class Wumpus extends Componente {


    Wumpus(int i, int j, char letra, Caverna C) {
        super(i, j, letra, C);
    }

    @Override
    public Componente[] inserirSecundarios() {
        //gerar vetor com os Fedor
        //cria vetor de componentes
        int i = super.getI();
        int j = super.getJ();
        Componente[] vetorSecundarios = new Fedor[4];
        //adiciona 4 brisas
        int lin, col;
        Caverna C = super.getC();
        //de cima
        lin = i - 1;
        col = j;
        int x = 0;
        if (lin >= 0) {
            vetorSecundarios[x] = new Fedor(lin, col, 'f', C);
            x++;
        }
        //de baixo
        lin = i + 1;
        col = j;
        if (lin < C.getDimensao()) {
            vetorSecundarios[x] = new Fedor(lin, col, 'f', C);
            x++;
        }
        //direita
        lin = i;
        col = j + 1;
        if (col < C.getDimensao()) {
            vetorSecundarios[x] = new Fedor(lin, col, 'f', C);
            x++;
        }
        //esquerda
        lin = i;
        col = j - 1;
        if (col >= 0) {
            vetorSecundarios[x] = new Fedor(lin, col, 'f', C);
            x++;
        }

        return vetorSecundarios;
    }
}