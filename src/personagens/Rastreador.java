package personagens;

import itens.Arma;

public class Rastreador extends Personagem {

    // Construtor que chama o construtor da superclasse Personagem
    public Rastreador(String nome, int vida, int fome, int sede, int energia, int sanidade,
                      Inventario inventario, String localizacao, double temperaturaCorporal,
                      int sedeMaxima, int velocidade, boolean desidratado, boolean infectado, boolean delirio, Arma armaEquipada) {
        super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao, temperaturaCorporal, sedeMaxima, velocidade, desidratado, infectado, delirio, armaEquipada);

    }

    //Mais chances de encontrar agua e comida(Skill em desenvolvimento)

}
