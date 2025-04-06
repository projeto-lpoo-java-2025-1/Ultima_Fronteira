package personagens; // Define que esta classe pertence ao pacote 'itens'


import java.util.ArrayList; // Importa a classe ArrayList, usada como tipo do inventário do personagem

// A classe SobreviventeNato é uma subclasse da classe Personagem
public class SobreviventeNato extends Personagem {

    //Construtor
    public SobreviventeNato(String nome, int vida, int fome, int sede, int energia, int sanidade, ArrayList inventario, String localizacao) {
        super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
    }

    //pretendo criar metodos com as skills dos itens por aqui
}
