package personagens; // Define que esta classe pertence ao pacote 'itens'

import itens.Arma;
import itens.CatalogoDeItens;
import itens.Item;

// A classe Mecanico é uma subclasse da classe Personagem
public class Mecanico extends Personagem {

    //Construtor
    public Mecanico(String nome, int vida, int fome, int sede, int energia, int sanidade,
                      Inventario inventario, String localizacao, double temperaturaCorporal,
                      int sedeMaxima, int velocidade, boolean desidratado, boolean infectado, Arma armaEquipada) {
        super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao, temperaturaCorporal, sedeMaxima, velocidade, desidratado, infectado, armaEquipada);
    }

    //Skill em desenvolvimento

}


