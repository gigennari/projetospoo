package lab06;

public class AppMundoWumpus {
    public static void main(String[] args){
        String caminhoCSV = args[0];
        MontadorCaverna mont = new MontadorCaverna(caminhoCSV) ;
        Caverna C = mont.MontarCaverna();

        ControleJogo controlador = new ControleJogo(C.celulas[0][0].heroi);
        controlador.executarJogo();
    }
}
