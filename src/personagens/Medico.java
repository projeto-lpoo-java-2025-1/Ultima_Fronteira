package personagens; // Define que esta classe pertence ao pacote 'itens'

import itens.Arma;

public class Medico extends Personagem {

    public Medico(String nome, int vida, int fome, int sede, int energia, int sanidade,
                      Inventario inventario, String localizacao, double temperaturaCorporal,
                      int sedeMaxima, int velocidade, boolean desidratado, boolean infectado, Arma armaEquipada) {
            super(nome, vida, fome, sede, energia, sanidade, inventario, localizacao, temperaturaCorporal, sedeMaxima, velocidade, desidratado, infectado, armaEquipada);
        }

        public boolean recuperarVidaTurno() {
        try {
            this.recuperarVida(5);
            return true;
        } catch (exceptions.VidaCheiaException e) {
            return false;
        }
    }
}

