package lab06;

public class MontadorCaverna {
    String caminhoCSV;

    MontadorCaverna(String caminhoCSV){
        this.caminhoCSV = caminhoCSV;
    }

    public boolean entradaValida(String[][] posicoes){
        //verificar se há 2-3 buracos, 1 heroi, 1 wumpus, 1 ouro
        int qtdeWumpus = 0, qtdeBuraco = 0, qtdeHeroi = 0, qtdeOuro = 0;

        int tam = posicoes.length;
        //percorrer posicoes

        //garante que herói está na posição 1:1 da caverna [1:1, P]

        if(posicoes[0][1].charAt(0) != 'P'){
            return false;
        }

        for(int i = 0; i < tam; i++){
            if(posicoes[i][1].charAt(0) == 'P'){
                qtdeHeroi++;
            }
            if(posicoes[i][1].charAt(0) == 'O'){
                qtdeOuro++;
            }
            if(posicoes[i][1].charAt(0) == 'B'){
                qtdeBuraco++;
            }
            if(posicoes[i][1].charAt(0) == 'W'){
                qtdeWumpus++;
            }
        }

        if(qtdeWumpus != 1 || qtdeBuraco < 2 || qtdeBuraco > 3 || qtdeHeroi != 1 || qtdeOuro != 1){
            return false;
        }
        return true;
    }

    public Caverna MontarCaverna(){
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(caminhoCSV);
        String[][] posicoes = csv.requestCommands();	//devolve algo do tipo  [[1:1, P], [1:2, _], ...,[4:4, _]]

        if(entradaValida(posicoes)){

            Caverna C = new Caverna();

            int tam = posicoes.length;

            for(int n = 0; n < tam;n++){
                //le pos
                String[] ij = posicoes[n][0].split(":");
                int i = Integer.valueOf(ij[0]) - 1;
                int j = Integer.valueOf(ij[1]) - 1;
                char letra = posicoes[n][1].charAt(0);

                //instancia e passa C como parametro -> objeto chama caverna -> chama sala
                Componente novo = null;
                if(letra == 'P'){
                    Heroi player = new Heroi(i, j, letra, C);
                    C.inserirHeroi(player);
                    C.marcarVisitada(0,0);
                }
                else if(letra == 'W'){
                    novo = new Wumpus(i,j,letra, C);
                }
                else if(letra == 'B'){
                    novo = new Buraco(i,j,letra, C);
                }
                else if(letra == 'O'){
                    novo = new Ouro(i,j,letra, C);
                }

                //se a posição não estiver vazia, ou seja, for diferente de '_', colocar componente na Caverna
                if(novo != null){
                    C.inserirComponente(novo);
                }

            }


            return C;
        }

        System.out.println("A entrada CSV é inválida");
        return null;

    }
}
