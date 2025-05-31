package personagens; // Define que esta classe pertence ao pacote 'itens'

import itens.Arma;

// A classe SobreviventeNato é uma subclasse da classe Personagem
public class SobreviventeNato extends Personagem {

    //Construtor
    public SobreviventeNato(String nome, int vida, int fome, int sede, int energia, int sanidade,
                      Inventario inventario, String localizacao, double temperaturaCorporal,
                      int sedeMaxima, int velocidade, boolean desidratado, boolean infectado, Arma armaEquipada) {
        super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao, temperaturaCorporal, sedeMaxima, velocidade, desidratado, infectado, armaEquipada);

    }

    public boolean recuperarSedeTurno() {
        try {
            this.recuperarSede(1);
            return true;
        } catch (exceptions.SedeCheiaException e) {
            return false;
        }
    }

    public boolean recuperarFomeTurno() {
        try {
            this.recuperarFome(1);
            return true;
        } catch (exceptions.FomeCheiaException e) {
            return false;
        }
    }

}
