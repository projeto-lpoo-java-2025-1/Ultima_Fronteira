package sistema; // Define que esta classe faz parte do pacote 'sistema'

import personagens.Inventario;
import personagens.Personagem; // Importa a classe Personagem para poder declarar variáveis desse tipo

public class Main { // Classe principal onde o programa começa sua execução

    public static void main(String[] args) { // Método main: ponto de entrada da aplicação Java

        Jogo jogo = new Jogo();

        jogo.iniciarAmbientes();
        jogo.ocorrerEventoClimatico();
        jogo.ocorrerEventoCriatura();
        jogo.ocorrerEventoDoencaFerimento();
        jogo.ocorrerEventoDescoberta();
    }
}


