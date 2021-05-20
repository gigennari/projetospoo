package lab06;

public class Heroi extends Componente{
    private String nome;
    private boolean flechaEquipada;
    private int qtdeFlechas;


    Heroi(int i, int j, char letra, Caverna C){
        super(i, j, letra, C);
        this.flechaEquipada = false;
        this.qtdeFlechas = 1;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }


    public void setFlechaEquipada(boolean bool){
        this.flechaEquipada = bool;
    }

    public boolean getFlechaEquipada(){
        return flechaEquipada;
    }

    public void setQtdeFlechas(int qtdeFlechas) {
        this.qtdeFlechas = qtdeFlechas;
    }
    public int getQtdeFlechas() {
        return qtdeFlechas;
    }

    public Caverna getC(){
        return super.getC();
    }

    @Override
    public char getLetra() {
        return super.getLetra();
    }
}
