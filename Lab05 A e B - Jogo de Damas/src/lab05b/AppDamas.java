package lab05b;

public class AppDamas {

    static String executaJogo(String caminho){
        //ler o CSV
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(caminho);
        String[] jogadas = csv.requestCommands();	//devolve algo do tipo  {"f4:d4", "c4:e4"}

        //criar o tabuleiro
        Tabuleiro tabJogo = new Tabuleiro();
        System.out.println("Tabuleiro Inicial:");
        String configuracao = tabJogo.imprimirTabuleiro();
        System.out.println(configuracao);


        //dividir em jogadas
        int num_jogadas = jogadas.length;

        //String de retorno
        String estadoAtual = "";


        for(int i = 0; i < num_jogadas; i++) {
            //executa jogadas uma a uma

            String[] comandos = jogadas[i].split(":");
            String origem = comandos[0];
            String destino = comandos[1];

            System.out.println("Source:" + origem);
            System.out.println("Target: " + destino);

            estadoAtual = tabJogo.solicitaMovimento(origem, destino);


            System.out.println(estadoAtual);

        }
        return estadoAtual;

    }

    static void exportarArquivo(String configuracao, String caminho){
        CSVHandling destino = new CSVHandling();
        destino.setDataExport(caminho);
        if (configuracao == "Movimento invalido!"){
            String[] estadoFinal = {"Erro"};
        }
        else{

            int pos = 0, pos_config = 0;
            String letras = " abcdefgh";
            String[] estadoFinal = new String[64];
            for (int col = 0; col < 10; col++){
                for (int lin = 7; lin >= 0; lin--){

                    pos_config  = (lin) * 10 + col;


                    if(col != 0 && col != 9){
                        estadoFinal[pos] = "" + letras.charAt(col);
                        estadoFinal[pos] = estadoFinal[pos] + (8-lin);

                        if (configuracao.charAt(pos_config) == '-'){
                            estadoFinal[pos] = estadoFinal[pos] + "_";
                        }
                        else{
                            estadoFinal[pos] = estadoFinal[pos] + configuracao.charAt(pos_config);

                        }
                        pos++;
                    }

                }
            }
            for(int i = 0; i < estadoFinal.length; i++){
                System.out.println(estadoFinal[i]);
            }
            destino.exportState(estadoFinal);
        }
    }
    //
    public static void main(String[] args) {
        String ultimo_retorno = executaJogo(args[0]);
        //passar última configuração p CSV
        exportarArquivo(ultimo_retorno, args[1]);

        //printar todas as jogadas na tela
        System.out.println(ultimo_retorno);
    }
}
