package lab06;

public class Caverna {
   private int dimensao;
    public Salas[][] celulas = new Salas[4][4];


    Caverna(){
        this.dimensao = 4;
        for(int i = 0; i < 4; i++){
            for(int j = 0;j < 4;j++){
                celulas[i][j] = new Salas();
            }
        }
    }

    public void inserirComponente(Componente componente){
        celulas[componente.getI()][componente.getJ()].inserirComponente(componente);
        //verificar se esse tipo de componente tem secundários
        if (componente instanceof Wumpus || componente instanceof Buraco) {
            inserirSecundarios(componente.inserirSecundarios());
        }
    }

    public void inserirSecundarios(Componente[] vetorSecundarios){
        int tam = vetorSecundarios.length;
        for(int n = 0; n < tam; n++){
            if(vetorSecundarios[n] != null){
                celulas[vetorSecundarios[n].getI()][vetorSecundarios[n].getJ()].inserirComponente(vetorSecundarios[n]);
            }

        }

    }

    public void inserirHeroi(Heroi heroi){
        celulas[heroi.getI()][heroi.getJ()].inserirHeroi(heroi);
    }

    public void movimentarHeroi(Heroi heroi, int i, int j){
        //remove herói da sala em que estava
        celulas[heroi.getI()][heroi.getJ()].heroi = null;
        //aletra posição do herói
        heroi.setI(i);
        heroi.setJ(j);
        //insere herói na nova sala
        celulas[heroi.getI()][heroi.getJ()].inserirHeroi(heroi);
    }

    public boolean haComponente(Heroi heroi, char letra){
        return celulas[heroi.getI()][heroi.getJ()].haComponente(letra);
    }

    public void removerComponente(Heroi heroi, char letra){
        celulas[heroi.getI()][heroi.getJ()].removerComponente(letra);
    }

    public int getDimensao() {
        return dimensao;
    }

    public void marcarVisitada(int lin, int col){
        celulas[lin][col].setjaVisitado();
    }
}
