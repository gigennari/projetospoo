package lab06;

public class Salas {
    private boolean jaVisitado;
    public Componente[] componentes = new Componente[4];
    public int num_elementos;
    public Heroi heroi;

    Salas() {
        this.jaVisitado = false;
        for(int i = 0; i < 4;i++){
            componentes[i] = null;
        }
        this.num_elementos = 0;
        this.heroi = null;
    }

    public void inserirComponente(Componente componente) {
        componentes[num_elementos] = componente;
        num_elementos++;


    }

    public void inserirHeroi(Heroi heroi) {
        this.heroi = heroi;
    }


    //funciona para remover ouro ou wumpus
    public boolean haComponente(char letra) {
        for(int i = 0; i < num_elementos; i++)
        {
            if(componentes[i].getLetra()== letra){
                return true;
            }
        }
        return false;
    }

    //funciona para remover ouro ou wumpus
    public void removerComponente(char letra) {
        for(int i = 0; i < num_elementos; i++)
        {
            if(componentes[i].getLetra() == letra){
                //remover o componente
                componentes[i] = null;
                //mover restante do vetor
                while(i < num_elementos-1){
                    componentes[i] = componentes[i+1];
                    i++;
                }
            }
        }
        num_elementos--;
        componentes[num_elementos] = null;

    }

    public void setjaVisitado(){
        jaVisitado = true;
    }

    public boolean getjaVisitado(){
        return jaVisitado;
    }
    public void setNum_elementos(int num_elementos){
        this.num_elementos = num_elementos;
    }


}