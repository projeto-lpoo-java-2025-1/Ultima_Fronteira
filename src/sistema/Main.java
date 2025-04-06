package sistema; // Define que esta classe faz parte do pacote 'sistema'

import personagens.Personagem; // Importa a classe Personagem para poder declarar variáveis desse tipo

public class Main { // Classe principal onde o programa começa sua execução

    public static void main(String[] args) { // Método main: ponto de entrada da aplicação Java

        // Cria uma instância da classe SelecaoDePersonagem passando o nome "Diego" e a classe escolhida "mecanico"
        // Isso simula o processo de seleção de personagem no início do jogo
        SelecaoDePersonagem selecao = new SelecaoDePersonagem("Adam", "mecanico");

        // Usa o método criarPersonagem() para gerar um objeto da subclasse correspondente (no caso, Mecanico)
        Personagem personagem = selecao.criarPersonagem();

        System.out.println();

        // Exibe no console o nome do personagem criado
        System.out.println("Personagem criado: " + personagem.getNome());

        // Exibe no console o valor inicial de vida do personagem
        System.out.println("Vida inicial: " + personagem.getVida());

        new Jogo().iniciarAmbientes();
    }
}


