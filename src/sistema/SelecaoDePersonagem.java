package sistema; // Define que esta classe pertence ao pacote 'sistema'

import itens.Item; // Importa a classe Item, já que será usado para criar o inventário
import personagens.*; // Importa todas as classes do pacote 'personagens', incluindo as subclasses (Rastreador, Medico, etc.)

import java.util.ArrayList; // Importa a classe ArrayList para armazenar o inventário

public class SelecaoDePersonagem { // Classe responsável por criar um personagem baseado na escolha do jogador

    // Atributos privados
    private String nome; // Nome do personagem a ser criado
    private String classeEscolhida; // Classe escolhida (ex: "médico", "rastreador")

    // Construtor que recebe o nome e a classe escolhida pelo jogador
    public SelecaoDePersonagem(String nome, String classeEscolhida) {
        this.nome = nome; // Armazena o nome
        this.classeEscolhida = classeEscolhida.toLowerCase(); // Armazena a classe escolhida em letras minúsculas para facilitar comparação
    }

    // Método que cria e retorna um objeto da classe Personagem (ou uma de suas subclasses)
    public Personagem criarPersonagem() {
        // Define valores padrões iniciais para todos os personagens
        int vida = 100, fome = 100, sede = 100, energia = 100, sanidade = 100;
        ArrayList<Item> inventario = new ArrayList<>(); // Inicializa o inventário vazio
        String localizacao = "Floresta"; // Localização padrão onde o personagem começa

        // Estrutura de decisão que instancia uma subclasse diferente dependendo da classe escolhida
        // Não utilizei Scanner, pois futuramente implementaremos a intercace
        switch (classeEscolhida.toLowerCase()) {
            case "rastreador":
                return new Rastreador(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
            case "mecanico":
                return new Mecanico(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
            case "medico":
                return new Medico(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
            case "sobrevivente nato":
                return new SobreviventeNato(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
            default:
                // Se o jogador digitou uma classe que não existe, exibe uma mensagem de erro
                System.out.println("Classe inválida. Criando personagem genérico.");
                // Cria um personagem base (da classe Personagem, sem habilidades específicas)
                return new Personagem(nome, vida, fome, sede, energia, sanidade, inventario, localizacao);
        }
    }

    // Essa classe é útil para isolar a lógica de criação de personagem e facilitar expansão do jogo futuramente.
}
