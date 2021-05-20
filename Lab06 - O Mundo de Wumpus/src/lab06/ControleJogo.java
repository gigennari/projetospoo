package lab06;

import java.util.Scanner;

public class ControleJogo {
    Heroi heroi;
    int score;
    boolean capturouOuro, ganhou, perdeu;

    ControleJogo(Heroi heroi){
        this.heroi = heroi;
        this.score = 0;
        this.capturouOuro = false;
        this.ganhou = false;
        this.perdeu = false;

    }

    public void executarJogo() {

        //ler nome do heroi

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Digite um nome para o seu jogador: ");
        String nome = keyboard.nextLine();
        heroi.setNome(nome);

        boolean jogoContinua = true;

        while (jogoContinua == true) {
            // para ler proxima jogada
            String str = keyboard.nextLine();
            char comando = str.charAt(0);

            //interpretar comando e excecutá-lo

            if(comando == 'w' || comando == 's' || comando == 'd' || comando == 'a'){
                int lin = 0, col = 0;
                switch (comando){
                    //Herói movimenta para a sala acima
                    case 'w':
                        lin = heroi.getI()-1;
                        col = heroi.getJ();
                        break;
                    //Herói movimenta para a sala abaixo
                    case  's':
                        lin = heroi.getI()+1;
                        col = heroi.getJ();
                        break;
                    //Herói movimenta para a sala a direita
                    case 'd':
                        lin = heroi.getI();
                        col = heroi.getJ() + 1;
                        break;

                    //Herói movimenta para a sala a esquerda
                    case 'a':
                        lin = heroi.getI();
                        col = heroi.getJ() - 1;
                        break;

                }
                if(destinoDentroCaverna(lin , col)){
                    if(heroi.getFlechaEquipada()==true){
                        jogarFlecha(lin, col);
                    }
                    //remove da sala anterior, atualiza i e j, coloca na sala nova
                    heroi.getC().movimentarHeroi(heroi, lin, col);
                    atualizaScore(-15);
                    //verificar se caiu em sala com Wumpus ou buraco
                    if(heroi.getC().haComponente(heroi, 'W') || heroi.getC().haComponente(heroi, 'B')){
                        //heroi morre
                        perdeu = true;
                        atualizaScore(-1000);
                    }
                    //verificar se está na sala 1,1 com ouro, ou seja, em salas[0][0]
                    if(lin == 0 && col == 0 && capturouOuro == true){
                        ganhou = true;
                        atualizaScore(1000);
                    }
                }
                //marcar que a sala foi visitada
                Caverna C = heroi.getC();
                if(C == null){
                    System.out.println("caverna tá null");
                }
                C.marcarVisitada(lin, col);



            }

            //Herói equipa
            if(comando == 'k'){
                if(heroi.getQtdeFlechas()>0){
                    heroi.setFlechaEquipada(true);
                }

            }

            //Herói captura o ouro
            if(comando == 'c'){
                //verificar se há ouro na posição do herói
                if(heroi.getC().haComponente(heroi, 'O')){
                    capturouOuro = true;
                    //remover o ouro do vetor de componente na sala em que o ouro está
                    heroi.getC().removerComponente(heroi,'O');
                }

            }

            //O usuário sai do jogo
            if(comando == 'q') {
                jogoContinua = false;
            }


            //imprimir configuração atual
            imprimirTabuleiro();

            //se fim do jogo
            if(ganhou || perdeu || jogoContinua == false){
                //se ganhou
                if(ganhou){
                    System.out.println("Você ganhou =D !!!");
                }

                //se perdeu
                if(perdeu){
                    System.out.println("Você perdeu =( ...");
                }

                //se pediu para sair do jogo
                if(comando == 'q'){
                    System.out.println("Volte sempre!");
                }
                jogoContinua = false;
            }
        }
    }

    public boolean destinoDentroCaverna(int lin, int col){
        if (lin >= 0 && lin <= 3 && col >= 0 && col <= 3){
            return true;
        }
        return false;
    }

    public void jogarFlecha(int lin, int col){

        //verificar se há wumpus na nova sala
        if(heroi.getC().haComponente(heroi, 'W')){
            //como já verificamos que a flecha está equipada, matar wumpus se ele estiver na sala
            heroi.getC().removerComponente(heroi, 'W');
            atualizaScore(500);
        }
        //diminuir score por usar flecha
        atualizaScore(-100);

        //atualizar info flecha herói
        heroi.setQtdeFlechas(0);
        heroi.setFlechaEquipada(false);
    }

    public void atualizaScore(int pontos){
        score += pontos;
    }

    public void imprimirTabuleiro() {
        Caverna C = heroi.getC();
        int tam = C.getDimensao();

        //verificar quem está no vetor componente e imprimir de acordo com a ordem de prioridade
        //ordem de prioridade: Ouro = Buraco = Wumpus > Herói > Fedor > Brisa
        for (int i = 0; i < tam; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < tam; j++) {

                if (C.celulas[i][j].getjaVisitado() == true) {
                    char letra = '#';
                    int qtdeComponentes = C.celulas[i][j].num_elementos;

                    //primeiro vê se tem alguém de alta prioridade (ouro, buraco ou wumpus)
                    for (int x = 0; x < qtdeComponentes; x++) {
                        Componente comp = C.celulas[i][j].componentes[x];
                        if (comp instanceof Ouro || comp instanceof Buraco || comp instanceof Wumpus) {
                            letra = comp.getLetra();
                            break;
                        }
                    }

                    //caso não tenha algo de alta prioridade, verificar se tem heroi
                    if (letra == '#') {
                        if (C.celulas[i][j].heroi != null) {
                            letra = heroi.getLetra();
                        }
                    }
                    //se não tiver heroi, verificar no vetor se há fedor ou brisa
                    if (letra == '#') {
                        for (int x = 0; x < qtdeComponentes; x++) {
                            Componente comp = C.celulas[i][j].componentes[x];
                            if (comp instanceof Fedor || comp instanceof Brisa) {
                                letra = comp.getLetra();
                                break;
                            }
                        }
                    }
                    System.out.print(letra);
                }
                else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }

        System.out.println(" 1234");
        System.out.println("Player: " + heroi.getNome());
        System.out.println("Score: " + score);
    }

}
