package lab06;

public class Buraco extends Componente{


    Buraco(int i, int j, char letra, Caverna C){
        super(i, j, letra, C);
    }


    @Override
    public Componente[] inserirSecundarios( ){
        //gerar vetor com as Brisas
        //cria vetor de componentes

        Componente[] vetorSecundarios = new Brisa[4];
        //adiciona 4 brisas
        int lin, col;
        int i = super.getI();
        int j = super.getJ();
        Caverna C = super.getC();
        int x = 0;

        //de cima
        lin = i-1;
        col = j;
        if(lin >= 0){
            vetorSecundarios[x] = new Brisa(lin, col, 'b', C);
            x++;
        }
        //de baixo
        lin = i+1;
        col = j;
        if(lin < C.getDimensao()){
            vetorSecundarios[x] = new Brisa(lin, col, 'b', C);
            x++;
        }
        //direita
        lin = i;
        col = j+1;
        if(col < C.getDimensao()){
            vetorSecundarios[x] = new Brisa(lin, col, 'b', C);
            x++;
        }
        //esquerda
        lin = i;
        col = j-1;
        if(col >= 0){
            vetorSecundarios[x] = new Brisa(lin, col, 'b', C);
            x++;
        }
        return  vetorSecundarios;
    }



}
