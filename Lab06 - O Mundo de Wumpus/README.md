
# Lab06 - O Mundo de Wumpus

Estrutura de pastas:
~~~
.
├── bin
│   └── lab06
├── data
│   └── CavernasCSV
│       ├── caverna1.csv
│       ├── caverna2.csv
│       └── caverna3.csv
├── README.md
├── src
│   └── lab06
└── UML Diagram.jpg

~~~

## Arquivos Java do Jogo

### Intruções para jogar o jogo:

###Arquivos java[caminho]


## Destaques de Arquitetura

### A Estrutura

![A Estrutura](https://github.com/[gigennari]/[projetospoo]/blob/[branch]/image.jpg?raw=true)

### `Movimentação Herói`


Quando é solictado um comando de deslocamento do `Herói`, o `ControleJogo` executa o seguinte comado
``heroi.getC().movimentarHeroi(heroi, lin, col)``. Assim, a `Caverna` é acessada e, por sua vez, chama 
as `Salas` onde o herói deve ser removido e inserido, delegando o máximo possível a cada objeto coisas que lhe dizem 
respeito.

```java
public class Caverna {
    private int dimensao;
    public Salas[][] celulas = new Salas[4][4];

    public void movimentarHeroi(Heroi heroi, int i, int j) {
        //remove herói da sala em que estava
        celulas[heroi.getI()][heroi.getJ()].heroi = null;
        //aletra posição do herói
        heroi.setI(i);
        heroi.setJ(j);
        //insere herói na nova sala
        celulas[heroi.getI()][heroi.getJ()].inserirHeroi(heroi);
    }
}
```

### `Polimorfismo do Componente e Inserção/Remoção na Caverna`

Como `Componente` é uma classe que possui vários herdeiros, caso 
um novo tipo de componente queira ser inserido é possível fazer 
isso sem alterar `Caverna` e `Salas`para a remoção e inserção de 
componente, pois estes são tratados de modo genérico como
é visto nos trechos de código abaixo:

```java
public class Caverna {
    private int dimensao;
    public Salas[][] celulas = new Salas[4][4];
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

    public void removerComponente(Heroi heroi, char letra){
        celulas[heroi.getI()][heroi.getJ()].removerComponente(letra);
    }
}
```

```java
public class Salas {
    private boolean jaVisitado;
    public Componente[] componentes = new Componente[4];
    public int num_elementos;
    public Heroi heroi;

    public void inserirComponente(Componente componente) {
        componentes[num_elementos] = componente;
        num_elementos++;


    }

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
    
```

