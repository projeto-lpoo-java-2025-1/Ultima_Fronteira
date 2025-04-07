package personagens; // Define que esta classe pertence ao pacote 'itens'

import java.util.ArrayList; // Importa a classe ArrayList, usada como tipo do inventário do personagem

// A classe Mecanico é uma subclasse da classe Personagem
public class Mecanico extends Personagem {

    //Construtor
    public Mecanico(String nome, int vida, int fome, int sede, int energia, int sanidade, ArrayList inventario, String localizacao) {
        super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
    }

    //pretendo criar metodos com as skills dos itens por aqui
}
