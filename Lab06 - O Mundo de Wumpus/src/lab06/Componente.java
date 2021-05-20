package lab06;

public class Componente {
    private int i, j;
    private char letra;
    private Caverna C;

    Componente(int i, int j, char letra, Caverna C){
        this.i = i;
        this.j = j;
        this.letra = letra;
        this.C = C;
    }

    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public char getLetra(){
        return letra;
    }
    public Caverna getC(){
        return  C;
    }

    public void setI(int i){
        this.i = i;
    }
    public void setJ(int j){
        this.j = j;
    }
    public void setLetra(char letra){
        this.letra = letra;
    }
    public void setC(Caverna C){
        this.C = C;
    }

    //implementada em Buraco e Wumpus
    public Componente[] inserirSecundarios(){
        Componente [] vetor = new Componente[1];
        return vetor;
    }
}
