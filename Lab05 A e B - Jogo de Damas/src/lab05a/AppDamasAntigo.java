package lab05a;

public class AppDamasAntigo {

    public static void main(String[] args) {
        String[] retorno = executaJogo("");
        System.out.println(retorno);
    }

    static String[] executaJogo(String caminho){
        //ler o CSV
        CSVReader csv = new CSVReader();
        csv.setDataSource(caminho);
        String[] jogadas = csv.requestCommands();	//devolve algo do tipo  {"f4:d4", "c4:e4"}

        //criar o tabuleiro
        Tabuleiro tab = new Tabuleiro();
        String lala = tab.apresentarTabuleiro();
        System.out.println(lala);

        //dividir em jogadas
        int tam = jogadas.length;

        //String de retorno
        String[] estadosTab = new String[tam];

        for(int i = 0; i < tam; i++) {
            //executa jogadas uma a uma

            String[] comandos = jogadas[i].split(":");
            String origem1 = comandos[0];
            String destino1 = comandos[1];

            for(int j = i+1; j < tam - i; j++){
                String[] comandos2 = jogadas[j].split(":");
                String origem2 = comandos2[0];
                String destino2 = comandos2[1];

                if(destino1 == origem2){
                    String primeira_jogada = tab.executarJogada(origem1, destino1);
                    i++;
                    estadosTab[i] = tab.executarJogada(origem2, destino2);

                }
                else{
                    estadosTab[i] = tab.executarJogada(origem1, destino1);
                    break;
                }

            }

        }
        return estadosTab;

    }
}
